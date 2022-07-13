package com.sample;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {

        Animal cat = new Animal("food", "green", 2);

        //Student st = new Student(12, "Peter","Jackson", 'm', 20.5);
        Student st = new Student();
        st.setId(12);
        st.setName(null);
        st.setLastName("Jackson");
        st.setGender('m');
        st.setAvRating(20.5);
        st.setCat(cat);

        //Registry reg = new Registry(346545645L, st, 56, "Java");
        Registry reg = new Registry();
        reg.setAmount(346545645L);
        reg.setStudent(st);
        reg.setNumberId(56);
        reg.setName("Java");

        try (var fos = new FileOutputStream("save.txt");
             var oos = new ObjectOutputStream(fos);
             var fis = new FileInputStream("save.txt");
             var ois = new ObjectInputStream(fis)) {
            System.out.println(reg);

            String tmp = Serializer.serialize(reg);
            oos.writeObject(tmp);
            System.out.println(tmp);

            tmp = ois.readObject().toString();
            Registry regRez = Serializer.deserialize(tmp, Registry.class);
            System.out.println(regRez);



        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 NoSuchFieldException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}