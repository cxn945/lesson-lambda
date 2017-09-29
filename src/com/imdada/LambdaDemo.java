package com.imdada;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Lambda 表达式：特性
 */
public class LambdaDemo {

    List<Integer> list = Arrays.asList(10,3,7,5,6);
    /**
     * 1.lambda实例
     */
    @Test
    public void lambda(){

        //0.匿名内部类
        new IntCalculate(){
            @Override
            public int cal(int a, int b) {
                return a+b;
            }
        }.cal(1, 3);

        // 1.类型声明
        IntCalculate add1 = (int a, int b)->{return a+b;};
        // 2.无类型声明（jvm会通过目标的上下文进行类型推断）
        IntCalculate add2 = (a, b)->{return a+b;};
        // 3.无显示return
        IntCalculate add3 = (a, b)->a+b;

        // 4.void方法
        ObjectPrinter printer = obj-> System.out.println(obj.toString());

        System.out.println(add1.cal(1,3));
        System.out.println(add2.cal(1,3));
        System.out.println(add3.cal('1',3));
        printer.print("abc");

    }

    /**
     * 2.变量作用域：
     *  lambda 表达式对值封闭，对变量开放（否则容易出现竞态条件）
     *  即lambda对外部的引用至少必须是"形式上"final（不可变）的。
     */
    //lambda expressions close over values, not variables
    @Test
    public void scope(){
        final int size = 1;
        list.forEach(e-> System.out.println(size)); //正常
//        list.forEach(e->size++);    // 报错，size是一个值

        AtomicInteger bsize = new AtomicInteger();
        list.forEach(e->bsize.incrementAndGet());   // 不报错，bsize是一个对象，引用不变
        System.out.println(bsize);

    }

    /**
     * 3.方法引用
     */
    @Test
    public void functionReference(){
        //构造器引用
        Object obj = build(StringBuffer::new);
        System.out.println(obj.getClass().getCanonicalName());
        //方法引用
        list.sort(Integer::compareTo);
        System.out.println(list);
        /*
         * 其它：
         *  实例上的实例方法引用：instanceReference::methodName
         *  超类上的实例方法引用：super::methodName
         *  数组构造方法引用：TypeName[]::new
         */
    }


    public Object build(Supplier<Object> supplier){
        return supplier.get();
    }

    interface IntCalculate {
        // 有返回值的方法
        int cal(int a, int b);
    }

    interface ObjectPrinter{
        // 无返回值的
        void print(Object obj);
    }



}

