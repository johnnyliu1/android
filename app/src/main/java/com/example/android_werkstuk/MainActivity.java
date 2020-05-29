package com.example.android_werkstuk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
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

        //Button buttonAddRecipe = findViewById(R.id.btn_add_recipe);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_search);
        setTitle("Home");
/*
        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }

        });
*/

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

    //end

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);



        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "search is expandfed", Toast.LENGTH_SHORT);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "search is collapse", Toast.LENGTH_SHORT);
                return true;
            }
        };
        menu.findItem(R.id.app_bar_search).setOnActionExpandListener(onActionExpandListener);

        SearchView searchView =(SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setQueryHint("Search data");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch(menu.getItemId()) {
            case R.id.add_recipe:
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(menu);
        }
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
            String ingredients  = data.getStringExtra(Admin.EXTRA_INGREDIENTS);

            Recipe recipe = new Recipe(0,name,kcal,protein,carbs,sugar,sodium,instruction, ingredients);
            recipeViewModel.insert(recipe);
            Toast.makeText(this,"Recipe saved",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Recipe not saved",Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeButtons() {
        Button button1 = findViewById(R.id.btn_recipes);
        Button button2 = findViewById(R.id.btn_saved);
        Button button3 = findViewById(R.id.btn_about);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked(view);
            }
        };

        button1.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);
        button3.setOnClickListener(buttonListener);

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
            case R.id.btn_about:
                intent = new Intent(this, About.class);
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
