package com.sample;

import java.util.Objects;

public class Student   {

    @Save
    private int id;
    @Save
    private String name;

    private String lastName;

    @Save
    private char gender;

    @Save
    private double avRating;

    @Save
    Animal cat;

    public Student(int id, String name, String lastName, char gender, double avRating) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.avRating = avRating;
    }


    public Student() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getAvRating() {
        return avRating;
    }

    public void setAvRating(double avRating) {
        this.avRating = avRating;
    }

    public Animal getCat() {
        return cat;
    }

    public void setCat(Animal cat) {
        this.cat = cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && gender == student.gender && Double.compare(student.avRating, avRating) == 0 &&
                name.equals(student.name) && lastName.equals(student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, gender, avRating);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", avRating=" + avRating +
                ", cat=" + cat +
                '}';
    }
}
