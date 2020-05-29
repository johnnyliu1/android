package com.example.android_werkstuk.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.room.Database;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android_werkstuk.DAO.recipeDAO;
import com.example.android_werkstuk.Model.Recipe;


@androidx.room.Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class mijnDatabase extends RoomDatabase {
    private static mijnDatabase instance;

    public abstract recipeDAO recipeDAO();

    public static synchronized mijnDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    mijnDatabase.class,"mijnDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private recipeDAO recipeDAO;

        private PopulateDbAsyncTask(mijnDatabase db) {
            recipeDAO = db.recipeDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recipeDAO.insert(new Recipe(0,"Het eerste recept","kcal","protein", "carbs", "sugar", "sodium", "instruction", "500g swag"));
            recipeDAO.insert(new Recipe(0,"Het tweede recept","12","24", "56", "34", "45", "zo doe je dat!", "500g cool"));
            recipeDAO.insert(new Recipe(0,"Het derde recept","67","456", "456", "34", "45", "ahhh zo doet je dat", " 520g vlees"));

            return null;
        }
    }
}
