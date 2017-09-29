package com.imdada;

import com.imdada.util.StringTools;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caixinning on 2017/9/29.
 */
public class EffectiveTestDemo {

    @Test
    public void time(){
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        for (int i=0;i<100;i++){
            list1.add(StringTools.genRandomChars(4));
            list2.add(StringTools.genRandomChars(4));
            list3.add(StringTools.genRandomChars(4));
        }
        StringBuilder sb = new StringBuilder();


        long start1 = System.nanoTime();
        list1.stream().map(e->sb.append(e.length()).append(","));
        long end1= System.nanoTime();
        System.out.println(">> forEach:"+ (end1-start1)/1000000F);


        long start2 = System.nanoTime();
        list2.forEach(e->sb.append(e.length()).append(","));
        long end2 = System.nanoTime();
        System.out.println(">> forEach:"+ (end2-start2)/1000000F);


        long start3 = System.nanoTime();
        for (String e:list3){
            sb.append(e.length()).append(",");
        }
        long end3 = System.nanoTime();
        System.out.println(">> forEach:"+ (end3-start3)/1000000F);



    }

}
