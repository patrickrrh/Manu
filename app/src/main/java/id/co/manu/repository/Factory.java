package id.co.manu.repository;

public class Factory {

    private int image;
    private String name;
    private String description;
    private String location;

    public Factory(int image, String name, String description, String location) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
