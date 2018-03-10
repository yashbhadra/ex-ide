package com.creeps.ex_ide.core.networking.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rohan on 10/3/18.
 * A wrapper around a list of exhibits
 */

public class ExhibitWrapper {
    @SerializedName("exhibits")
    private ArrayList<Exhibit> exhibits;
    public ExhibitWrapper(ArrayList<Exhibit> exhibits){
        this.exhibits=exhibits;
    }
    public ArrayList<Exhibit> getExhibits(){return this.exhibits;}
}
