package com.example.android_werkstuk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_werkstuk.Adapter.RecipeAdapter;
import com.example.android_werkstuk.Model.Recipe;
import com.example.android_werkstuk.ViewModel.RecipeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeButtons();

        Button buttonAddRecipe = findViewById(R.id.btn_add_recipe);
        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RecipeAdapter adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        recipeViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(RecipeViewModel.class);
        recipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                //update recyclerview
                adapter.setRecipes(recipes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recipeViewModel.delete(adapter.getRecipeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Recipe deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(Admin.EXTRA_NAME);
            String kcal = data.getStringExtra(Admin.EXTRA_KCAL);
            String protein = data.getStringExtra(Admin.EXTRA_PROTEIN);
            String carbs = data.getStringExtra(Admin.EXTRA_CARBS);
            String sugar = data.getStringExtra(Admin.EXTRA_SUGAR);
            String sodium = data.getStringExtra(Admin.EXTRA_SODIUM);
            String instruction = data.getStringExtra(Admin.EXTRA_INSTRUCTION);

            Recipe recipe = new Recipe(0,name,kcal,protein,carbs,sugar,sodium,instruction);
            recipeViewModel.insert(recipe);
            Toast.makeText(this,"Recipe saved",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Recipe not saved",Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeButtons() {
        Button button1 = findViewById(R.id.btn_recipes);
        Button button2 = findViewById(R.id.btn_saved);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked(view);
            }
        };

        button1.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);

    }


    private void buttonClicked(View button) {
        Intent intent;
        switch (button.getId()) {
            case R.id.btn_recipes:
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.btn_saved:
                intent = new Intent(this, test.class);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + button.getId());
        }
        startActivity(intent);
    }


    public void goList(View view) {
        Intent intent = new Intent(this, test.class);
        startActivity(intent);
    }


}