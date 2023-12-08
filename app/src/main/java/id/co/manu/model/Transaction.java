package id.co.manu.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String id, userId, name, category, address, quantity, totalPrice, transactionDate, imageUrl;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getId() {
        return id;
    }
    public String getUserId(){
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
