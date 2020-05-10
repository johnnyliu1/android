package com.example.android_werkstuk.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String kcal;
    private String protein;
    private String carbs;
    private String sugar;
    private String sodium;
    private String instruction;



    public String getInstruction() {
        return instruction;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKcal() {
        return kcal;
    }

    public String getProtein() {
        return protein;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getSugar() {
        return sugar;
    }

    public String getSodium() {
        return sodium;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }


    public Recipe(int id, String name, String kcal, String protein, String carbs, String sugar, String sodium, String instruction) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbs = carbs;
        this.sugar = sugar;
        this.sodium = sodium;
        this.instruction = instruction;
    }
}
