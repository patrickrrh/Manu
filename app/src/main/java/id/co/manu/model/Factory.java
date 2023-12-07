package id.co.manu.model;

import java.io.Serializable;

public class Factory implements Serializable {
    private String factoryId;
    private String name;
    private String price;
    private String category;
    private String address;
    private String description;
    private String imageUrl;

    public Factory(){}

    public Factory(String factoryId, String name, String price, String category, String address, String description, String imageUrl) {
        this.factoryId = factoryId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.address = address;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getFactoryId(){return factoryId;}
    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }
    public String getImageUrl(){return imageUrl;}
}
