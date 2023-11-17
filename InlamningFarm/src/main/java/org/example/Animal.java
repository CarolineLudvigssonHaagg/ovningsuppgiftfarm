package org.example;

import java.util.ArrayList;
import java.util.List;

public class Animal extends Entity {

    private String species;
    private ArrayList<Crop> acceptableCropTypes;

    public Animal(String species, ArrayList<Crop> acceptableCropTypes, int id, String name) {
        super(id, name);
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }

    public String getCSV() {
        String animalString = species + "," + getId() + "," + getName();
        animalString = animalString + ":";
        for(Crop crop : acceptableCropTypes) {
            if(acceptableCropTypes.indexOf(crop) != 0) {
                animalString = animalString + "@";
            }
            animalString = animalString + crop.getCSV();
        }
        return animalString;
    }

    @Override
    public String getDescription() {
        return null;
    }

    public String feed() {
        return null;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public ArrayList<Crop> getAcceptableCropTypes() {
        return acceptableCropTypes;
    }

    public void setAcceptableCropTypes(ArrayList<Crop> acceptableCropTypes) {
        this.acceptableCropTypes = acceptableCropTypes;
    }
}
