package com.example.demo;

import com.example.demo.common.utils.Md5Util;
import com.example.demo.todo.AesHelper;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2023, zaxh Group All Rights Reserved.
 * @since: 2023/03/31/16:54
 * @summary: 私钥加密算法,即对称加密
 */
public class PrivateSecretEncryptTest {

    // 私钥key,规定密钥长度必须128位(128bit=16 bytes length)
    private static final String SECRET_KEY="test123456test12";


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
        String result = stringBuffer.toString();
        return result.toString();
    }

    @Test
    public void testAesEncrypt(){

        //这个地方有点不太明白，偏移量如果输入字符串，然后转成字节数组，则会报销，说长度不符合要求，必须16位
        //而当把同样的偏移量输入转成md5串以后，就可以加密，不报错
        AesHelper aesHelper = new AesHelper(SECRET_KEY.getBytes(StandardCharsets.UTF_8), Md5Util.compute((SECRET_KEY+"").getBytes(StandardCharsets.UTF_8)));
        String content = "123456";
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        byte [] bytes = aesHelper.encrypt(contentBytes);

        System.out.println("偏移量:"+ hashListTo16Str(aesHelper.getIv().getIV()));
        System.out.println(hashListTo16Str(contentBytes));
        System.out.println(hashListTo16Str(bytes));
        System.out.println(new String(aesHelper.decrypt(bytes),StandardCharsets.UTF_8));
    }
}
