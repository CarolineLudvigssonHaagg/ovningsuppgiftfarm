package org.example;

public class Crop extends Entity {
    private String cropType;
    private int quantity;

    public Crop(String cropType, int quantity, int id, String name) {
        super(id, name);
        this.cropType = cropType;
        this.quantity = quantity;
    }

    public String getCSV() {
        return cropType + "," + quantity + "," + getId() + "," + getName();
    }

    @Override
    public String getDescription() {
        return null;
    }

    public void addCrop(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public boolean takeCrop(int quantityToBeEaten) {
        if((quantity - quantityToBeEaten) < 0) {
            return false;
        }
        quantity = quantity - quantityToBeEaten;
        return true;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
