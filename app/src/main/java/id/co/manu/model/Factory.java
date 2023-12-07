package id.co.manu.model;

public class Factory {
    private String factoryId;
    private String name;
    private String price;
    private String category;
    private String address;
    private String description;

    public Factory(){}

    public Factory(String factoryId, String name, String price, String category, String address, String description) {
        this.factoryId = factoryId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.address = address;
        this.description = description;
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
}
