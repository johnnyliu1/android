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

        setTitle("Add Recipe");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getIntents() {
        Log.d(TAG,"checking for intents");
        String Name = getIntent().getStringExtra("Name");
        String Kcal = getIntent().getStringExtra("Kcal");
        String Sugar = getIntent().getStringExtra("Sugar");
        String Sodium = getIntent().getStringExtra("Sodium");
        String Carbs = getIntent().getStringExtra("Carbs");
        String Protein = getIntent().getStringExtra("Protein");
        String Instruction = getIntent().getStringExtra("Instruction");
        String Ingredients = getIntent().getStringExtra("Ingredients");

        setIntents(Name, Kcal, Sugar, Sodium, Protein, Carbs, Ingredients, Instruction);
    }

    private void setIntents(String Name, String Kcal, String Sugar, String Sodium, String Protein, String Carbs, String Ingredients, String Instruction) {
        TextView name = findViewById(R.id.txtName);
        TextView kcal = findViewById(R.id.txtKcal);
        TextView sugar = findViewById(R.id.txtSugar);
        TextView sodium = findViewById(R.id.txtSodium);
        TextView protein = findViewById(R.id.txtProtein);
        TextView carbs = findViewById(R.id.txtCarbs);
        TextView ingredients = findViewById(R.id.txtIngredients);
        TextView instruction = findViewById(R.id.txtInstruction);

        kcal.setText(Kcal);
        name.setText(Name);
        sugar.setText(Sugar);
        protein.setText(Protein);
        sodium.setText(Sodium);
        carbs.setText(Carbs);
        ingredients.setText(Ingredients);
        instruction.setText(Instruction);

    }
}
