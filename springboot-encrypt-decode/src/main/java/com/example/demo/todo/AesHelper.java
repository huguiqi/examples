package com.example.demo.todo;

import com.example.demo.common.utils.Md5Util;
import net.jcip.annotations.NotThreadSafe;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2023, zaxh Group All Rights Reserved.
 * @since: 2023/03/31/16:56
 * @summary: Aes加密算法，属于对称加密,替代Des加密
 */
@NotThreadSafe
public class AesHelper {

    private SecretKeySpec keySpec;
    private IvParameterSpec iv;

    public IvParameterSpec getIv() {
        return iv;
    }

    public AesHelper(byte[] aesKey, byte[] iv) {
        if (aesKey == null || aesKey.length < 16 || (iv != null && iv.length < 16)) {
            throw new RuntimeException("错误的初始密钥");
        }
        if (iv == null) {
            iv = Md5Util.compute(aesKey);
        }
        //根据字节数组生成AES密钥
        keySpec = new SecretKeySpec(aesKey, "AES");
        this.iv = new IvParameterSpec(iv);
    }

    public AesHelper(byte[] aesKey) {
        if (aesKey == null || aesKey.length < 16) {
            throw new RuntimeException("错误的初始密钥");
        }
        keySpec = new SecretKeySpec(aesKey, "AES");
        this.iv = new IvParameterSpec(Md5Util.compute(aesKey));

    }

    public byte[] encrypt(byte[] data) {
        byte[] result = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public byte[] decrypt(byte[] secret) {
        byte[] result = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            result = cipher.doFinal(secret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static byte[] randomKey(int size) {
        byte[] result = null;
        try {
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(size, new SecureRandom());
            result = gen.generateKey().getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
