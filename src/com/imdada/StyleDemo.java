package com.imdada;

import com.imdada.util.StringTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lambda代码风格
 */
public class StyleDemo {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        for (int i=0;i<20;i++){
            String str = StringTools.genRandomChars(4);
            list1.add(str);
            list2.add(str);
            list3.add(str);
        }
        //1.java7编程风格
        Collections.sort(list1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list1);

        //2.java8编程风格
        Collections.sort(list2, String::compareTo);
        System.out.println(list2);

        //3.java8默认方法
        list3.sort(String::compareTo);
        System.out.println(list3);
    }
}
