package com.example.demo;

import com.example.demo.todo.HMacHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@163.cn)
 * @Copyright (c) 2023, 163 Group All Rights Reserved.
 * @since: 2023/03/30/17:35
 * @summary:
 */

public class HashEncryptDecodeTest {

    private   String computeMD5(byte[] content) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] bytes = md5.digest(content);
            return hashListTo16Str(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private String computeSHA1(byte [] content){
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            byte [] bytes = sha1.digest(content);
            return hashListTo16Str(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Created with IntelliJ IDEA.
     * @Author: sam.hu (huguiqi@huazhu.com)
     * @version v1
     * @param bytes
     * @return str16
     * @since: 2023/3/30 19:35
     * @summary: 将散列值串转成16进制数字符表示
     */
    private static String hashListTo16Str(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int offset = 0; offset < bytes.length; offset++) {
            int i = bytes[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                stringBuffer.append("0");
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString();
    }

    @Test
    public void testMd5Encrypt(){
        String actual = computeMD5("123456".getBytes(StandardCharsets.UTF_8)).toUpperCase();
        String expected = "E10ADC3949BA59ABBE56E057F20F883E";
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.length()>0 );
        Assertions.assertEquals(expected,actual);
        System.out.println(actual);
    }


    @Test
    public void testEncryptSHA1(){
        //表示为40位的十六进制数
        String actual = computeSHA1("123456".getBytes(StandardCharsets.UTF_8)).toUpperCase();
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.length()>0 );
        System.out.println(actual);
    }


    @Test
    public void testEncryptyHMAC(){
        HMacHelper hMacHelper = new HMacHelper("keyTest");
        byte[] content = "123456".getBytes(StandardCharsets.UTF_8);
        byte [] bytes = hMacHelper.sign(content);
        Assertions.assertTrue(hMacHelper.verify(bytes,content));
        Assertions.assertFalse(hMacHelper.verify(bytes,"654321".getBytes(StandardCharsets.UTF_8)));
        String encryptPwd = hashListTo16Str(bytes);

        System.out.println(encryptPwd);

    }

}
