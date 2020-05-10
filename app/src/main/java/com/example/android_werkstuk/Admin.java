package com.example.android_werkstuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_werkstuk.Database.mijnDatabase;
import com.example.android_werkstuk.Model.Recipe;
import com.example.android_werkstuk.ViewModel.RecipeViewModel;

public class Admin extends AppCompatActivity {
    public static final String EXTRA_NAME =
            "com.example.android_werkstuk.EXTRA_NAME";
    public static final String EXTRA_KCAL =
            "com.example.android_werkstuk.EXTRA_KCAL";
    public static final String EXTRA_PROTEIN =
            "com.example.android_werkstuk.EXTRA_PROTEIN";

    public static final String EXTRA_CARBS =
            "com.example.android_werkstuk.EXTRA_CARBS";

    public static final String EXTRA_SUGAR=
            "com.example.android_werkstuk.EXTRA_SUGAR";

    public static final String EXTRA_SODIUM =
            "com.example.android_werkstuk.EXTRA_SODIUM";

    public static final String EXTRA_INSTRUCTION =
            "com.example.android_werkstuk.EXTRA_INSTRUCTION";
    private EditText txtName;
    private EditText txtKcal;
    private EditText txtProtein;
    private EditText txtCarbs;
    private EditText txtSugar;
    private EditText txtSodium;
    private EditText txtInstruction;
    private Button btn_insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txtName = (EditText) findViewById(R.id.txtName);
        txtKcal = (EditText) findViewById(R.id.txtKcal);
        txtProtein = (EditText) findViewById(R.id.txtProtein);
        txtCarbs = (EditText) findViewById(R.id.txtCarbs);
        txtSugar = (EditText) findViewById(R.id.txtSugar);
        txtSodium = (EditText) findViewById(R.id.txtSodium);
        txtInstruction = (EditText) findViewById(R.id.txtInstruction);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add recipe");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_recipe_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_recipe:
                saveRecipe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void saveRecipe() {
        String Name = txtName.getText().toString();
        String Kcal = txtKcal.getText().toString();
        String Protein = txtProtein.getText().toString();
        String Carbs = txtCarbs.getText().toString();
        String Sugar = txtSugar.getText().toString();
        String Sodium = txtSodium.getText().toString();
        String Instruction = txtInstruction.getText().toString();

        if (Name.trim().isEmpty()) {
            Toast.makeText(this,"please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, Name);
        data.putExtra(EXTRA_KCAL, Kcal);
        data.putExtra(EXTRA_PROTEIN, Protein);
        data.putExtra(EXTRA_CARBS, Carbs);
        data.putExtra(EXTRA_SUGAR, Sugar);
        data.putExtra(EXTRA_SODIUM, Sodium);
        data.putExtra(EXTRA_INSTRUCTION, Instruction);

        setResult(RESULT_OK, data);
        finish();


    }


}

