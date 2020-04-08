package com.example.recyclerviewtest;

public class Fruit {
    private String fruit_name;
    private int fruit_ima_id;
    public Fruit(String fruit_name,int fruit_ima_id){
        this.fruit_ima_id=fruit_ima_id;
        this.fruit_name=fruit_name;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public int getFruit_ima_id() {
        return fruit_ima_id;
    }
}
