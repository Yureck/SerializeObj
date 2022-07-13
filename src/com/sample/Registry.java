package com.sample;

import java.util.Objects;

public class Registry {

    @Save
    long amount;
    @Save
    Student student;
    @Save
    long numberId;

    String name;

    public Registry(long amount, Student student, long numberId, String name) {
        super();
        this.amount = amount;
        this.student = student;
        this.numberId = numberId;
        this.name = name;
    }

    public Registry() {
        super();
    }
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getNumberId() {
        return numberId;
    }

    public void setNumberId(long numberId) {
        this.numberId = numberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registry registry = (Registry) o;
        return numberId == registry.numberId && Objects.equals(student, registry.student) && Objects.equals(name, registry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, numberId, name);
    }

    @Override
    public String toString() {
        return "Registry [amount=" + amount + ", " + "student=" + student+", " + "numberId="
                + numberId + ", " + "name=" + name + "]";
    }

}
