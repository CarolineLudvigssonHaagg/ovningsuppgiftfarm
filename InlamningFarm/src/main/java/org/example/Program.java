package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    Farm farm = new Farm();
    public Program() {
    }

        public void startMenu () {
            System.out.println("Hi and welcome to my Farm simulator!");
            System.out.println("What would you like to do?");
            System.out.println("1: Go to animal menu");
            System.out.println("2: Go to crop menu");
            System.out.println("3: Save");
            System.out.println("4: Load saved file");
            Scanner startMenuScanner = new Scanner(System.in);

            try {
                int choice = Integer.parseInt(startMenuScanner.nextLine());
                switch (choice) {
                    case 1:
                        animalMenu();
                        break;
                    case 2:
                        cropMenu();
                        break;
                    case 3:
                        save();
                        break;
                    case 4:
                        load();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        startMenu();
                }

            } catch (Exception e) {
                System.out.println("Invalid choice try again.");
                startMenu();
            }
        }

        public void load() {
            Scanner saveScanner = new Scanner(System.in);
            System.out.println("Write Yes if you would like to load a saved file");
            String inputAnimalMenu = saveScanner.nextLine().toLowerCase();
            if(inputAnimalMenu.equals("yes")) {
                farm.load();
            }
            animalMenu();
        }

        public void save() {
            Scanner saveScanner = new Scanner(System.in);
            System.out.println("Write Yes if you would like to save");
            String inputAnimalMenu = saveScanner.nextLine().toLowerCase();
            if(inputAnimalMenu.equals("yes")) {
                farm.save();
            }
            animalMenu();
        }

        public void animalMenu () {
            System.out.println("What would you like to do in the animal menu?");
            System.out.println("1: View animals");
            System.out.println("2: Add new animal");
            System.out.println("3: Remove animal");
            System.out.println("4: Feed animals");
            System.out.println("5: Go back to start menu");
            Scanner animalMenuScanner = new Scanner(System.in);

            try {
                int choice = Integer.parseInt(animalMenuScanner.nextLine());
                switch (choice) {
                    case 1:
                        listAllAnimals();
                        animalMenu();
                        break;
                    case 2:
                        addNewAnimal();
                        animalMenu();
                        break;
                    case 3:
                        removeAnimal();
                        animalMenu();
                        break;
                    case 4:
                        feedAnimals();
                        animalMenu();
                        break;
                    case 5:
                        startMenu();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        animalMenu();
                }
            } catch (Exception e) {
                System.out.println("Invalid choice");
                animalMenu();
            }
        }

        public void listAllAnimals () {
            AnimalManager animalManager = farm.getAnimalManager();
            ArrayList<Animal> animals = animalManager.getAnimals();
            if(animals.size() == 0) {
                System.out.println("You have no animals in your farm");
            }
            for (Animal animal : animals) {
                System.out.println("Species: " + animal.getSpecies() + ", Name: " + animal.getName());
                ArrayList<Crop> acceptableCropTypes = animal.getAcceptableCropTypes();
                System.out.println("Acceptable crop types for " + animal.getName());
                for(Crop crop : acceptableCropTypes) {
                    System.out.println("Crop name: " + crop.getName() + ", " + "Crop type: " + crop.getCropType());
                }
                System.out.println("#############################");
            }
        }

        public void addNewAnimal() {
            AnimalManager animalManager = farm.getAnimalManager();
            CropManager cropManager = farm.getCropManager();
            Scanner addNewAnimalScanner = new Scanner(System.in);
            System.out.println("What species is your new animal?");
            String animalSpecies = addNewAnimalScanner.nextLine();

            System.out.println("What is the name of this animal?");
            String animalName = addNewAnimalScanner.nextLine();

            System.out.println("What does your animal eat, choose from the list below");
            ArrayList<Crop> availableCrops = cropManager.getCrops();
            ArrayList<Crop> acceptableCropTypes = new ArrayList<>();
            int input = -999;
            while(input != 0) {
                for(int i = 0; i < availableCrops.size(); i++) {
                    int index = i+1;
                    Crop crop = availableCrops.get(i);
                    System.out.println(index + ": " + crop.getName());
                }
                System.out.println("0: no more crop");
                System.out.println("exit: The food i need is not here i need to restock first");
                String stringInput = addNewAnimalScanner.nextLine();
                if(stringInput.equals("exit")) {
                    animalMenu();
                }

                try {
                    input = Integer.parseInt(stringInput);
                    if(input > availableCrops.size() || input < 0) {
                        System.out.println("That is not a valid choice please try again");
                        return;
                    }

                    if(input != 0) {
                        Crop crop = availableCrops.get(input - 1);
                        acceptableCropTypes.add(crop);
                    }
                } catch(Exception e) {
                    System.out.println("That is not a valid choice please try again");
                    return;
                }
            }

            Animal newAnimal = new Animal(animalSpecies, acceptableCropTypes, animalManager.getNextAnimalId(), animalName);
            animalManager.addAnimal(newAnimal);
        }

        public void removeAnimal() {
            Scanner removeAnimalScanner = new Scanner(System.in);
            AnimalManager animalManager = farm.getAnimalManager();
            ArrayList<Animal> animals = animalManager.getAnimals();
            System.out.println("This is the list of animals that you can remove, please choose one");
            for(int i = 0; i < animals.size(); i++) {
                int index = i + 1;
                Animal animal = animals.get(i);
                System.out.println(index + ": " + animal.getName() + ", " + animal.getSpecies());
            }
            System.out.println("0: Go back");
            int input = Integer.parseInt(removeAnimalScanner.nextLine());
            if(input > animals.size() || input < 0) {
                System.out.println("Invalid choice please try again");
            }

            if(input != 0) {
                Animal animalToBeRemoved = animals.get(input - 1);
                animalManager.removeAnimal(animalToBeRemoved);
            }

        }

        public void feedAnimals() {
            AnimalManager animalManager = farm.getAnimalManager();
            ArrayList<Animal> animals = animalManager.getAnimals();
            CropManager cropManager = farm.getCropManager();
            Scanner feedAnimalsScanner = new Scanner(System.in);
            System.out.println("Here is a list of animals, which one would you like to feed?");
            for(int i = 0; i < animals.size(); i++) {
                Animal animal = animals.get(i);
                System.out.println(i + 1 +": " + "Name, " +  animal.getName() + ": Species, " + animal.getSpecies());
            }
            try {
                int input = Integer.parseInt(feedAnimalsScanner.nextLine());
                Animal animal = animals.get(input - 1);
                ArrayList<Crop> acceptableCropTypes = animal.getAcceptableCropTypes();
                System.out.println("Ok you choose " + animal.getName());
                System.out.println("This animal can eat, please choose an option");
                for(int i = 0; i < acceptableCropTypes.size(); i++) {
                    Crop crop = acceptableCropTypes.get(i);
                    System.out.println(i + 1 +": " + "Name, "+  crop.getName() + ": Crop type, " + crop.getCropType());
                }
                input = Integer.parseInt(feedAnimalsScanner.nextLine());
                Crop cropToBeEaten = acceptableCropTypes.get(input - 1);
                System.out.println("How many " + cropToBeEaten.getName() + " should be given to " + animal.getName() + "?");
                input = Integer.parseInt(feedAnimalsScanner.nextLine());
                boolean animalIsFed = cropManager.takeCrop(cropToBeEaten, input);
                if(animalIsFed) {
                    System.out.println(animal.getName() + " ate " + cropToBeEaten.getName());
                } else {
                    System.out.println("There is not enough quantity of the chosen crop, "+  cropToBeEaten.getName() + ", " + cropToBeEaten.getCropType() + " please restock");
                }
            } catch(Exception e) {
                System.out.println("That is not a valid choice, try again");
                feedAnimals();
            }
        }

        public void cropMenu() {
            Scanner cropMenuScanner = new Scanner(System.in);
            System.out.println("What would you like to do in the crop menu?");
            System.out.println("1: View crops");
            System.out.println("2: Add new crop");
            System.out.println("3: Remove crop");
            System.out.println("4: Go back to start menu");
            try {
                int choice = Integer.parseInt(cropMenuScanner.nextLine());
                switch (choice) {
                    case 1:
                        listAllCrops();
                        cropMenu();
                        break;
                    case 2:
                        addNewCrop();
                        cropMenu();
                        break;
                    case 3:
                        removeCrop();
                        cropMenu();
                        break;
                    case 4:
                        startMenu();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        cropMenu();
                }
            } catch (Exception e) {
                System.out.println("Invalid choice");
                cropMenu();
            }
        }


    public void listAllCrops() {
        CropManager cropManager = farm.getCropManager();
        ArrayList<Crop> crops = cropManager.getCrops();
        for (Crop crop : crops) {
            System.out.println(crop.getName() + ": " + crop.getCropType() + ": quantity: " + crop.getQuantity());
        }
    }

    private void addNewCrop() {
        Scanner addNewCropScanner = new Scanner(System.in);
        System.out.println("Do you want to restock a crop or create a new one?");
        System.out.println("1: Create new crop");
        System.out.println("2: Add quantity to existing crop");
        System.out.println("3: Go back");
        try {
            int choice = Integer.parseInt(addNewCropScanner.nextLine());
            switch (choice) {
                case 1:
                    createNewCrop();
                    addNewCrop();
                    break;
                case 2:
                    addQuantityToCrop();
                    addNewCrop();
                    break;
                case 3:
                    cropMenu();
                    break;
                default:
                    System.out.println("Invalid choice");
                    addNewCrop();
            }
        } catch (Exception e) {
            System.out.println("Invalid choice");
            addNewCrop();
        }

    }

    private void createNewCrop() {
        CropManager cropManager = farm.getCropManager();
        Scanner createNewCropScanner = new Scanner(System.in);
        System.out.println("What is the name for your new crop?");
        String cropName = createNewCropScanner.nextLine();
        System.out.println("What is the crops type?");
        String cropType = createNewCropScanner.nextLine();
        System.out.println("What is the starting quantity for this crop?");
        int cropQuantity = Integer.parseInt(createNewCropScanner.nextLine());

        Crop newCrop = new Crop(cropType, cropQuantity, cropManager.getNextCropId(), cropName);
        cropManager.addCrop(newCrop, cropQuantity);
    }

    private void addQuantityToCrop() {
        CropManager cropManager = farm.getCropManager();
        ArrayList<Crop> availableCrops = cropManager.getCrops();
        Scanner addQuantityToCropScanner = new Scanner(System.in);
        System.out.println("Which crop would you like to increase?");
        for(int i = 0; i < availableCrops.size(); i++) {
            int index = i + 1;
            Crop crop = availableCrops.get(i);
            System.out.println(index + ": " + crop.getName() + ", quantity " + crop.getQuantity());
        }
        int cropId = Integer.parseInt(addQuantityToCropScanner.nextLine());

        System.out.println("How many would you like to add?");
        try {
           int addInput = Integer.parseInt(addQuantityToCropScanner.nextLine());
                if(addInput >= 0) {
                    Crop increasedCrop = availableCrops.get(cropId - 1);
                    cropManager.addCrop(increasedCrop, addInput);
                    System.out.println("You added " + addInput + " to your "+ increasedCrop.getName() +" you now have " + increasedCrop.getQuantity());
                }
                if(addInput < 0) {
                    System.out.println("That is not a valid choice please try again");
                    cropMenu();
                }
            } catch(Exception e) {
                System.out.println("That is not a valid choice please try again");
                 cropMenu();
            }
        cropMenu();
    }

    private void removeCrop() {
        Scanner removeCropScanner = new Scanner(System.in);
        CropManager cropManager = farm.getCropManager();
        ArrayList<Crop> crops = cropManager.getCrops();
        System.out.println("This is the list of crops that you can remove, please choose one");
        for(int i = 0; i < crops.size(); i++) {
            int index = i + 1;
            Crop crop = crops.get(i);
            System.out.println(index + ": " + crop.getName());
        }
        System.out.println("0: Go back");
        int input = Integer.parseInt(removeCropScanner.nextLine());
        if(input > crops.size() || input < 0) {
            System.out.println("Invalid choice please try again");
        }

        if(input != 0) {
            Crop cropToBeRemoved = crops.get(input - 1);
            cropManager.removeCrop(cropToBeRemoved);
        }
    }
}