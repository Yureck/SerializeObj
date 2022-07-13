package com.sample;

import java.lang.reflect.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Serializer {

    private static final String OBJ_NULL = "null";
    private static final String FIELD_SEP = ";";
    private static final String REGEX_OBJ = "\\{.*;.*}";
    private static final String OBJ_START = "{";
    private static final String OBJ_END = "}";
    private static final String NAME_VAL_SEP = "=";


    private static StringBuilder getFieldVal(Object obj, Field field) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(field.getName()).append(NAME_VAL_SEP);

        if (field.getType() == int.class) {
            sb.append(field.getInt(obj)).append(FIELD_SEP);
        } else if (field.getType() == long.class) {
            sb.append(field.getLong(obj)).append(FIELD_SEP);
        } else if (field.getType() == double.class) {
            sb.append(field.getDouble(obj)).append(FIELD_SEP);
        } else if (field.getType() == short.class) {
            sb.append(field.getShort(obj)).append(FIELD_SEP);
        } else if (field.getType() == float.class) {
            sb.append(field.getFloat(obj)).append(FIELD_SEP);
        } else if (field.getType() == byte.class) {
            sb.append(field.getByte(obj)).append(FIELD_SEP);
        } else if (field.getType() == boolean.class) {
            sb.append(field.getBoolean(obj)).append(FIELD_SEP);
        } else if  (field.getType() == String.class || field.getType() == char.class) {
            sb.append(field.get(obj)).append(FIELD_SEP);
        }else {

            sb.append(serialize((field.get(obj) != null) ? field.get(obj) : OBJ_NULL)).append(FIELD_SEP);

        }
        return sb;
    }

    public static String serialize(Object obj) throws IllegalAccessException {
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder(OBJ_START);

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Save.class)) {
                continue;
            }
            if(Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
            }

            sb.append(getFieldVal(obj, field));

        }

        sb.append(OBJ_END);
        return sb.toString();
    }

    private static void setFieldVal(Object obj, Field field, String val) throws IllegalAccessException,
            NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        if (val.startsWith(OBJ_START) && val.endsWith(OBJ_END)) {

            field.set(obj, ((val.equals("{}")) ? null : deserialize(val, field.getType())));

        } else if (field.getType() == int.class) {
            field.setInt(obj, Integer.parseInt(val));
        } else if (field.getType() == long.class) {
            field.setLong(obj, Long.parseLong(val));
        } else if (field.getType() == double.class) {
            field.setDouble(obj, Double.parseDouble(val));
        } else if (field.getType() == short.class) {
            field.setShort(obj, Short.parseShort(val));
        } else if (field.getType() == float.class) {
            field.setFloat(obj, Float.parseFloat(val));
        } else if (field.getType() == byte.class) {
            field.setByte(obj, Byte.parseByte(val));
        } else if (field.getType() == boolean.class) {
            field.setBoolean(obj, Boolean.parseBoolean(val));
        } else if (field.getType() == char.class) {
            field.set(obj, val.charAt(0));
        } else {
            field.set(obj, val);
        }
    }

    private static Queue<String> getInnerObjs (String inp){
        Pattern pat = Pattern.compile(REGEX_OBJ);
        Matcher match = pat.matcher(inp);
        Queue<String> objQ= new ArrayDeque<String>();
        for (;match.find();){
            String tempS = inp.substring(match.start(), match.end());
            objQ.add(tempS);
        }
        return objQ;
    }

    public static <T> T deserialize(String inp, Class<T> cls) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Constructor<T> constr = cls.getConstructor();
        T obj = constr.newInstance();

        String tInp = inp.substring(inp.indexOf(OBJ_START) + 1, inp.lastIndexOf(OBJ_END) - 1);

        Queue<String> objQ= getInnerObjs(tInp);
        String tempS = tInp.replaceAll(REGEX_OBJ, "obj");

        String[] properties = tempS.split(FIELD_SEP);
        for (String prop : properties) {

            String[] tProp = prop.split(NAME_VAL_SEP);
            if (tProp.length != 2) {
                throw new IllegalArgumentException();
            }

            String name = tProp[0];
            String val = (tProp[1].equals("obj") ? objQ.poll() : tProp[1]);

            Field field = cls.getDeclaredField(name);
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            if (field.isAnnotationPresent(Save.class)) {

                setFieldVal(obj, field, val);

            }

        }
        return obj;

    }

}
