package com.jason.lee.learn;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 */
public class MD5 {
    /***
     * MD5加码 生成32位十六进制md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        //字符数组转化为字节数组
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
            System.out.println(byteArray[i]);
        }

        //字节数组转化为md5字节数组
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            System.out.println(md5Bytes[i]);
            System.out.println((int) md5Bytes[i]);
            int val = ((int) md5Bytes[i]) & 0xff;
            System.out.println(val);
            if (val < 16)
                hexValue.append("0");   //补充高位
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    // 测试主函数
    public static void main(String args[]) {
        String s1 = new String("abcdefg");
//        String s2 = "adc";
        System.out.println("原始：" + s1);
        System.out.println("MD5后：" + string2MD5(s1));
        System.out.println("加密的：" + convertMD5(s1));
        System.out.println("解密的：" + convertMD5(convertMD5(s1)));
//        System.out.println("原始：" + s2);
//        System.out.println("MD5后：" + string2MD5(s2));
//        System.out.println("加密的：" + convertMD5(s2));
//        System.out.println("解密的：" + convertMD5(convertMD5(s2)));

    }
}