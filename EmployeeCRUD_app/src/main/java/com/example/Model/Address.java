package com.example.Model;

public class Address {
//    private int id;
    private String Street;
    private String State;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    private int pinCode;

    public Address(String street, String state, int pinCode){
        this.Street=street;
        this.State=state;
        this.pinCode=pinCode;
    }
    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "" + Street  +
                ", " + State  +
                ", " + pinCode;
    }

    public static Address fromString(String str) {
        String[] parts = str.split(",");
        String Street = parts[0];
        String state = parts[1];
        int pinCode = parts.length > 2 && !parts[2].equals("null") ? Integer.parseInt(parts[2]) : null;
        return new Address(Street,state,pinCode);
    }


}
