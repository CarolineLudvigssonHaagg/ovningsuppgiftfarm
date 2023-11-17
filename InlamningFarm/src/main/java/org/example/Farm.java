package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Farm {

    private AnimalManager animalManager;

    private CropManager cropManager;

    public Farm() {
        animalManager = new AnimalManager(new ArrayList<Animal>());
        cropManager = new CropManager(new ArrayList<Crop>());
    }

        File animalManagerFolder = new File("animalManagerFolder");
        File animalManagerFile = new File("animalManagerFolder/animalManagerFile.txt");
        File cropManagerFolder = new File("cropManagerFolder");
        File cropManagerFile = new File("cropManagerFolder/cropManagerFile.txt");

        Scanner scanner = new Scanner(System.in);

        public void save() {
            saveCrops();
            saveAnimals();
        }

    private void saveCrops() {
        cropManagerFolder.mkdir();
        try {
            ArrayList<Crop> crops = cropManager.getCrops();
            FileWriter fw = new FileWriter(cropManagerFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Crop crop : crops) {
                System.out.println(crop.getCSV());
                bw.write(crop.getCSV());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Something unexpected happened");
            e.printStackTrace();
        }
    }

    private void saveAnimals() {
        animalManagerFolder.mkdir();
        try {
            ArrayList<Animal> animals = animalManager.getAnimals();
            FileWriter fw = new FileWriter(animalManagerFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Animal animal : animals) {
                System.out.println(animal.getCSV());
                bw.write(animal.getCSV());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Something unexpected happened");
            e.printStackTrace();
        }
    }
    public void load() {
            loadCrops();
            loadAnimals();
    }

    private void loadAnimals() {
        try {
            ArrayList<Animal> animals = animalManager.getAnimals();
            FileReader fr = new FileReader(animalManagerFile);
            BufferedReader bf = new BufferedReader(fr);

            String line = bf.readLine();
            while (line != null) {
                String[] split = line.split(":");
                String animalString = split[0];
                String acceptableCropTypes = split[1];
                String[] splitAcceptableCropTypes = acceptableCropTypes.split("@");
                ArrayList<Crop> acceptableCropTypeList = new ArrayList<>();
                for(int i = 0; i < splitAcceptableCropTypes.length; i++) {
                    String splitAcceptableCropType = splitAcceptableCropTypes[i];
                    String[] split1 = splitAcceptableCropType.split(",");
                    String cropType = split1[0];
                    int quantity = Integer.parseInt(split1[1]);
                    int id = Integer.parseInt(split1[2]);
                    String name = split1[3];
                    Crop crop = new Crop(cropType, quantity, id, name);
                    acceptableCropTypeList.add(crop);
                }
                String[] animalValues = animalString.split(",");
                String species = animalValues[0];
                int id = Integer.parseInt(animalValues[1]);
                String name = animalValues[2];
                Animal animal = new Animal(species, acceptableCropTypeList, id, name);
                animals.add(animal);
                line = bf.readLine();
            }

            bf.close();
            animalManager.setAnimals(animals);
        } catch (IOException e) {
            System.out.println("Something unexpected happened");
        }
    }

    private void loadCrops() {
        try {
            ArrayList<Crop> crops = cropManager.getCrops();
            FileReader fr = new FileReader(cropManagerFile);
            BufferedReader bf = new BufferedReader(fr);

            String line = bf.readLine();
            while (line != null) {
                String[] split = line.split(",");
                String cropType = split[0];
                int quantity = Integer.parseInt(split[1]);
                int id = Integer.parseInt(split[2]);
                String name = split[3];
                Crop crop = new Crop(cropType, quantity, id, name);
                crops.add(crop);
                line = bf.readLine();
            }

            bf.close();
            cropManager.setCrops(crops);
        } catch (IOException e) {
            System.out.println("Something unexpected happened");
        }
    }

    public AnimalManager getAnimalManager() {
        return animalManager;
    }

    public CropManager getCropManager() {
        return cropManager;
    }
}
