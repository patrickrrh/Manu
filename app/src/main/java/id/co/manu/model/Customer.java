package id.co.manu.model;

public class Customer {
    private String uid;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(){}
    public Customer(String uid, String name, String email, String phoneNumber) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUid(){return uid;}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
