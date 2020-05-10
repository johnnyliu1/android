package com.example.android_werkstuk.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.android_werkstuk.Database.mijnDatabase;
import com.example.android_werkstuk.Model.Recipe;
import com.example.android_werkstuk.DAO.recipeDAO;

import java.util.List;

public class RecipeRepository {
    private recipeDAO recipeDao;
    private LiveData<List<Recipe>> allRecipes;

    public RecipeRepository(Application application) {
        mijnDatabase database = mijnDatabase.getInstance(application);
        recipeDao = database.recipeDAO();
        allRecipes = recipeDao.getAllRecipes();
    }

    public void insert(Recipe recipe) {
        new insertRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public void update(Recipe recipe) {
        new UpdateRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public void delete(Recipe recipe) {
        new DeleteRecipeAsyncTask(recipeDao).execute(recipe);
    }


    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    private static class insertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private recipeDAO recipeDAO;

        private insertRecipeAsyncTask(recipeDAO recipeDAO) {
            this.recipeDAO = recipeDAO;
        }
        @Override
//        maakt aparte thread aan
        protected Void doInBackground(Recipe... recipes) {
            recipeDAO.insert(recipes[0]);
            return null;
        }
    }

    private static class UpdateRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private recipeDAO recipeDAO;

        private UpdateRecipeAsyncTask(recipeDAO recipeDAO) {
            this.recipeDAO = recipeDAO;
        }
        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDAO.update(recipes[0]);
            return null;
        }
    }

    private static class DeleteRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private recipeDAO recipeDAO;

        private DeleteRecipeAsyncTask(recipeDAO recipeDAO) {
            this.recipeDAO = recipeDAO;
        }
        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDAO.deleteRecipe(recipes[0]);
            return null;
        }
    }



}
