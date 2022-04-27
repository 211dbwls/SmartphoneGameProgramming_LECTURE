package com.example.dragonflight.framework;

import java.util.ArrayList;
import java.util.HashMap;

public class RecycleBin {
    private static HashMap<Class, ArrayList<Recyclable>> recycleBin = new HashMap<>();

    public static void init() {
        recycleBin.clear();
    }

    public static Recyclable get(Class clazz) {
        ArrayList<Recyclable> bin = recycleBin.get(clazz);  // clazz의 ArrayList<Recyclable>를 구함.
        if(bin == null) {  // 한번도 재활용된 적이 없는 경우
            return null;
        }

        if (bin.size() == 0) {  // 재활용된 적이 있지만 지금 쓸 수 있는 게 없는 경우
             return null;
        }

        return bin.remove(0);  // 재활용 가능한 경우 맨 앞에 있는 거 return.
    }

    public static void add(Recyclable object) {
        Class clazz = object.getClass();  // object class 얻어옴.
        ArrayList<Recyclable> bin = recycleBin.get(clazz);  // object class의 ArrayList<Recyclable>를 구함.

        if (bin == null) {  // ArrayList가 없는 경우
            bin = new ArrayList<>();  // 새로 만듦.
            recycleBin.put(clazz, bin);
        }
        bin.add(object);  // object를 넣음
    }
}
