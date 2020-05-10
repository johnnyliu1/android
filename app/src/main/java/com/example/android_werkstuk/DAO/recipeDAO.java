package com.example.android_werkstuk.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_werkstuk.Model.Recipe;

import java.util.List;

@Dao
public interface recipeDAO {

    @Query("SELECT * FROM Recipe")
    LiveData<List<Recipe>> getAllRecipes();
    //public List<Recipe> getAllRecipe();

    @Query("SELECT * FROM Recipe WHERE id=:id")
    public Recipe getRecipeId(int id);

    @Delete
    void deleteRecipe(Recipe recipe);

    @Insert
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);


}
