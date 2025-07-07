package com.example.demo;

import com.example.demo.common.utils.RsaUtil;
import com.example.demo.todo.RsaHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2023, zaxh Group All Rights Reserved.
 * @since: 2023/03/31/16:55
 * @summary: 公钥加密，即非对称加密
 */
public class PublicSecretEncryptTest {

    @Test
    public void testRSA() throws NoSuchAlgorithmException {


        KeyPair keyPair = RsaUtil.generateKeyPair(1024);
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey priKey = keyPair.getPrivate();

        String  publicKey = RsaUtil.getPublicKey(pubKey);
        String  privateKey = RsaUtil.getPrivateKey(priKey);
        RsaHelper rsaHelper = new RsaHelper(publicKey,privateKey);

        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        String content = "妈妈，我爱上了一个菇凉，我把青青留在了她的身体里。我是小黄歌！！";
        byte[] data = content.getBytes(StandardCharsets.UTF_8);

        System.out.println("明文：" + content);
        System.out.println("明文大小：" + data.length);

        byte [] signBuf = rsaHelper.sign(data);

        //公钥加密
        byte[] encodeBuf = rsaHelper.encrypt(data);
        System.out.println("密文：" + Base64Utils.encodeToString(encodeBuf));
        System.out.println("密文大小：" + encodeBuf.length);


        //私钥解密
        byte[] decodeBuf = rsaHelper.decrypt(encodeBuf);

        Assertions.assertTrue(rsaHelper.verify(signBuf,decodeBuf));
        Assertions.assertFalse(rsaHelper.verify(signBuf,"123456".getBytes(StandardCharsets.UTF_8)));

        System.out.println("解密后文字：" + new String(decodeBuf, StandardCharsets.UTF_8));

    }





}
