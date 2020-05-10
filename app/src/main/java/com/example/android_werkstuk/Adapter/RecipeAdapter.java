package com.example.android_werkstuk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_werkstuk.Model.Recipe;
import com.example.android_werkstuk.R;
import com.example.android_werkstuk.RecyclerViewClickInterface;
import com.example.android_werkstuk.recipeDetail;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private static final String TAG = "MyActivity";
    private List<Recipe> recipes = new ArrayList<>();


    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);
        holder.textviewNaam.setText(currentRecipe.getName());
        holder.textviewId.setText(String.valueOf(currentRecipe.getId()));
        holder.textviewKcal.setText(currentRecipe.getKcal());

        holder.itemView.setOnClickListener((view)-> {
            Log.v(TAG, "clicked" + position);

            Intent intent = new Intent(view.getContext(), recipeDetail.class);
            intent.putExtra("Name", currentRecipe.getName());
            view.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public Recipe getRecipeAt(int position) {
        return recipes.get(position);
    }

    class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView textviewNaam;
        private TextView textviewKcal;
        private TextView textviewId;


        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            textviewId = itemView.findViewById(R.id.receptId);
            textviewKcal = itemView.findViewById(R.id.receptKcal);
            textviewNaam = itemView.findViewById(R.id.receptNaam);

        }

    }


}
