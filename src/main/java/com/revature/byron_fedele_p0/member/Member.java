package com.revature.byron_fedele_p0.member;

public class Member {
    private String email;
    private String password;

    public Member() {
        super(); //calling the Object class to inherit from, since we will override toString method below
    }
    public Member(String email) {
        this.email = email;
    }
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // the following methods are referred to as setters and getters. They assign information to the objects parameters with set methods, and return the information store in those attributes with getters
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String writeToFile() {
        return email + "," + password + "\n";
    }


    @Override
    public String toString() {
        return "Member{" +"email='" + email + '}';
    }
}