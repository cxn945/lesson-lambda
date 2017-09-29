package com.imdada;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 和 Collection
 * Created by caixinning on 2017/9/29.
 */
public class StreamDemo {

    List<String> list = Arrays.asList("weisheng", "wuwenkai", "tanwangwei", "alan");
    List<Customer> customers = Arrays.asList(new Customer(1,27, "weisheng"), new Customer(2,25, "wuwenkai"),
            new Customer(3,23, "tanwangwei"), new Customer(4,29, "alan"));
    /**
     * 1.内部迭代、外部迭代
     */
    @Test
    public void inner(){
        //外部迭代：串行的、指定次序的（what+how）
        for (String item:list){
            System.out.println(item.length());
        }
        //内部迭代（what，how交给类库）
        list.forEach(e-> System.out.println(e.length()));

    }

    /**
     * 2.stream
     *  无存储、惰性、并行
     *  天然的函数式风格
     */
    @Test
    public void stream(){
        //流处理
        int totalAge = customers.stream().filter(e->e.getName().contains("a")).mapToInt(Customer::getAge).sum();
        System.out.println(totalAge);
        //集合处理
        //...

        /**
         * 集合主要用来对其元素进行有效（effective）的管理和访问（access），而流并不支持对其元素进行直接操作或直接访问，而只支持通过声明式操作在其上进行运算然后得到结果
         */
        /*
         * 中间操作：
         * 1.filter: Returns a stream consisting of the elements of this stream that match the given predicate.
         * 2.map: Returns a stream consisting of the results of applying the given function to the elements of this stream.
         * 3.sorted、distinct
         * 终止操作：
         * 1.forEach: Performs an action for each element of this stream.
         * 2.reduce: Performs a reduction on the elements of this stream.
         * 3.collect: Performs a reduction operation on the elements of this stream using a Collector.
         */


        Optional<String> rs = customers.stream().map(Customer::getName).reduce((s1, s2)->s1+" "+s2);
        String rs2 = customers.stream().map(Customer::getName).reduce("names:", (s1, s2) -> s1 + " " + s2);
        System.out.println(rs);
        System.out.println(rs2);

//        Map<String,String> map = customers.stream().collect(Collectors.toMap(Customer::getName, Customer::getAge));
        Map<String,Integer> map = customers.stream().collect(Collectors.toMap(Customer::getName, Customer::getAge));
        System.out.println("alan's age:"+map.get("alan"));

    }

    /**
     *  示例：1.通过Stream解析文件
     */
    @Test
    public void main() throws IOException {
        String file = "/Users/caixinning/case_export.csv";
        Stream<String> stream = Files.lines(Paths.get(file));
        List rs = stream.filter(s -> s.contains("自拍")).map(s->{
            String[] arr = s.split(",");
            Map<String, Object> item = new HashMap<>();
            item.put("caseId", arr[0]);
            item.put("userName", arr[2]);
            item.put("callTime", arr[8]);
            return item;
        }).collect(Collectors.toList());


        System.out.println(rs);
    }



    static class Customer{
        Integer id;
        Integer age;
        String name;

        public Customer(Integer id, Integer age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
