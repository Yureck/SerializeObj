package com.sample;

import java.util.Objects;

public class Animal {

    private String ration;
    @Save
    private String color;
    @Save
    private int weight;

    public Animal(String ration, String color, int weight) {
        super();
        this.ration = ration;
        this.color = color;
        this.weight = weight;
    }

    public Animal() {
        super();
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return weight == animal.weight && Objects.equals(ration, animal.ration) && Objects.equals(color, animal.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ration, color, weight);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "ration='" + ration + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

}
