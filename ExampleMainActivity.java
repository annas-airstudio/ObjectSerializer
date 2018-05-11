package com.annas.permanentstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.annas.note.permanentstorage", Context.MODE_PRIVATE);
        
        ArrayList<String> friends = new ArrayList<>();

        friends.add("Jale");
        friends.add("Cucur");

        // converting object to string
        try {

            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();

        } catch (IOException e) {

            e.printStackTrace();

        }
        
        // restore converting or bring data back in line 2 /deserializer
        ArrayList<String> newFriends = new ArrayList<>();

        try {

            newFriends = (ArrayList<String>) ObjectSerializer.deserialize((sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>()))));

        } catch (IOException e) {

            e.printStackTrace();

        }

        Log.i("newFriends", newFriends.toString());


        // sharedPreferences.edit().putString("username", "annas").apply();

        // String username = sharedPreferences.getString("username", "");

        // Log.i("username", username);

    }
}
