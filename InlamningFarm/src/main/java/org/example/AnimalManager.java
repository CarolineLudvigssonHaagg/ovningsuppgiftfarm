package org.example;

import java.util.ArrayList;

public class AnimalManager {

    ArrayList<Animal> animals;

    int animalId = 0;

    public AnimalManager(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal newAnimal) {
        animals.add(newAnimal);
    }

    public void removeAnimal(Animal animalToBeRemoved) {
        for (Animal oldAnimal : animals) {
            if(oldAnimal.getId() == animalToBeRemoved.getId()) {
                animals.remove(oldAnimal);
            }
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public int getNextAnimalId() {
        animalId = animalId + 1;
        return animalId;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
}
