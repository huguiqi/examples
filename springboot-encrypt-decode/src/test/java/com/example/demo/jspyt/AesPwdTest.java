package com.example.demo.jspyt;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.StandardEnvironment;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesPwdTest {


	private static String decryptAES(String data, String pass) {
		AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, new SecretKeySpec(pass.getBytes(), "AES"),
				new IvParameterSpec(pass.getBytes()));
		byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
		return new String(result, StandardCharsets.UTF_8);
	}


	private static String ecryptAES(String data, String salt) {
		AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, new SecretKeySpec(salt.getBytes(), "AES"),
				new IvParameterSpec(salt.getBytes()));
		//return new String(aesDecrypt(Base64.decode(ciphertext),Base64.decode(key)),“UTF-8”);
		return aes.encryptBase64(data, StandardCharsets.UTF_8);
	}



	@Test
	public void testDecryptAesForPassword(){
	String password = "";
		try {
			password = decryptAES("LwfzfWastr55SPTDxBgTvw==", "thanks,zaxhcloud");
		}
		catch (Exception e) {
			System.out.println("密码解密失败:"+ password);
		}

		System.out.println(password);
	}


	@Test
	public void testEcryptAesForPassword(){
		String password = ecryptAES("123456","thanks,zaxhcloud");

		System.out.println(password);
	}


	@Test
	public void testEncryptForConfiguratinForEnvTest(){

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("salt");
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);

		//加密方法
		System.out.println(encryptor.encrypt("zaxh"));
		//解密方法
		System.out.println(encryptor.decrypt("LwN66DdTUHf1uzFAPs1eyg=="));
		System.out.println(encryptor.decrypt("WFoi1HXY69l4kJJ40HKpcqD3jNq9tRTQ"));
	}



	@Test
	public void testEncryptForConfiguratinForEnvUat(){

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("salt");
//		config.setAlgorithm("PBEWithMD5AndDES");
		config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);

		//加密方法
		System.out.println(encryptor.encrypt("zaxh"));
		//解密方法
		System.out.println(encryptor.decrypt("Tc7gYJlPxSSxTsMDarskdw==)"));
	}
}
