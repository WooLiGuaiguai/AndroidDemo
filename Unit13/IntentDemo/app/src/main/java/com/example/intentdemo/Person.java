package com.example.intentdemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Person implements  Parcelable {
    private String name;
    private int age;
    private String sex;

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            Person person=new Person();
            person.name=source.readString();
            person.age=source.readInt();
            person.sex=source.readString();
            return person;
        }
        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {//将Person 类中的字段一一写出
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(sex);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}
