package com.imdada.util;

/**
 * String 工具
 * Created by Caixinning on 2016/8/22.
 */
public class StringTools {

    private final static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};


    /**
     * 生成随机字符串
     */
    public static String genRandomChars(int len){
        int charsLen = alphabet.length;
        StringBuilder code = new StringBuilder();
        for (int i=0;i<len;i++){
            int idx = (int) (System.nanoTime()%charsLen);
            idx = idx<0?-idx:idx;
            code.append(alphabet[idx]);
        }
        return code.toString();
    }
}
