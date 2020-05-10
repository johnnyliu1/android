package com.example.android_werkstuk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class recipeDetail extends AppCompatActivity {

    private static final String TAG = "myIntent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        getIntents();
    }

    private void getIntents() {
        Log.d(TAG,"checking for intents");
        String Name = getIntent().getStringExtra("Name");

        setIntents(Name);
    }

    private void setIntents(String Name) {
        TextView name = findViewById(R.id.txtName);
        name.setText(Name);
    }
}
