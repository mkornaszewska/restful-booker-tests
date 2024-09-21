package com.mkornaszewska.helpers;

public class CustomerHelper {

    private String firstName;
    private String lastName;

    public CustomerHelper(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

