package com.edualchem.tenderbay;

/**
 * Created by Explore on 11/4/2016.
 */


public class Person {

    String name;

    String email;

    String mobile;

    String org;

    String position;

    String uid;

    public Person() {
    }

    public Person(String name,String email,String mobile,String org,String position){
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.org = org;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String value) {
        this.mobile = value;
    }

    public String getOrg() {
        return this.org;
    }

    public void setOrg(String value) {
        this.org = value;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String value) {
        this.position = value;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}
