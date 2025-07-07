package com.example.demo.common.utils;

import org.springframework.util.Base64Utils;

import java.security.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2023, zaxh Group All Rights Reserved.
 * @since: 2023/04/03/15:39
 * @summary:
 */
public class RsaUtil {
    private static final String RSA_ALGORITHM = "RSA";

    //------------------------------
    /**
     * generate KeyPair
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static String getPublicKey(PublicKey publicKey) {
        return Base64Utils.encodeToString(publicKey.getEncoded());
    }

    public static String getPrivateKey(PrivateKey privateKey) {
        return Base64Utils.encodeToString(privateKey.getEncoded());
    }

}
