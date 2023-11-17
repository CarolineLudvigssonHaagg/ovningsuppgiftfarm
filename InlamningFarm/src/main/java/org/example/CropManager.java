package org.example;

import java.util.ArrayList;

public class CropManager {

    ArrayList<Crop> crops;

    int cropId = 0;

    public CropManager(ArrayList<Crop> crops) {
        this.crops = crops;
    }

    public void addCrop(Crop newCrop, int quantityToAdd) {
        boolean found = false;
        for(Crop oldCrop : crops) {
            if(oldCrop.getId() == newCrop.getId()) {
                oldCrop.addCrop(quantityToAdd);
                found = true;
            }
        }
        if(!found) {
            crops.add(newCrop);
        }
    }

    public void removeCrop(Crop cropToBeRemoved) {
        for(Crop oldCrop : crops) {
            if(oldCrop.getId() == cropToBeRemoved.getId()) {
                oldCrop.setQuantity(0);
            }
        }
    }

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public int getNextCropId() {
        this.cropId = cropId + 1;
        return cropId;
    }

    public boolean takeCrop(Crop cropToBeEaten, int quantityToBeEaten) {
        boolean cropHasBeenTaken = false;
        for(Crop oldCrop : crops) {
            if(oldCrop.getId() == cropToBeEaten.getId()) {
                cropHasBeenTaken = oldCrop.takeCrop(quantityToBeEaten);
            }
        }
        return cropHasBeenTaken;
    }

    public void setCrops(ArrayList<Crop> crops) {
        this.crops = crops;
    }
}
