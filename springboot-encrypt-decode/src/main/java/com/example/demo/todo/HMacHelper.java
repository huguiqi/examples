package com.example.demo.todo;

import net.jcip.annotations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2023, zaxh Group All Rights Reserved.
 * @since: 2023/03/30/19:16
 * @summary: HMAC 算法实例在 多线程环境 下是 不安全的。但是需要在 多线程访问 时，
 * 进行同步的辅助类，使用 ThreadLocal 为 每个线程缓存 一个实例可以避免进行锁操作。
 */
@NotThreadSafe
public class HMacHelper {

    private static final Logger logger = LoggerFactory.getLogger(HMacHelper.class);
    private Mac mac;
    /**
     * MAC算法可选以下多种算法 * HmacMD5/HmacSHA1/HmacSHA256/HmacSHA384/HmacSHA512
     * 返回的16进制的位数大小，和采用的MAC算法一致
     */
    private static final String KEY_MAC = "HmacMD5";

    public HMacHelper(String key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), KEY_MAC);
            mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
        } catch (Exception e) {
            logger.error("create hmac helper failed.", e);
        }
    }

    public byte[] sign(byte[] content) {
        return mac.doFinal(content);
    }

    public boolean verify(byte[] signature, byte[] content) {
        try {
            byte[] result = mac.doFinal(content);
            return Arrays.equals(signature, result);
        } catch (Exception e) {
            logger.error("verify sig failed.", e);
        }
        return false;
    }
}
