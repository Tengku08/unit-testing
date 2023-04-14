package com.bca.sampleproject.controller;

import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;


@RestController
public class Controller {
    SecureRandom random = new SecureRandom();

    public String encrypt(String strToEncrypt, String SECRET_KEY, String SALT) {
        try {
            byte[] bytesIV = new byte[16];
            random.nextBytes(bytesIV);
            IvParameterSpec ivspec = new IvParameterSpec(bytesIV);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 1000, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            return " ";
        }
    }
    public static long multiplyNumbers(int num)
    {
        if (num >= 1)
            return num * multiplyNumbers(num - 1);
        else
            return 1;
    }

    public String encryptV2(String strToEncrypt, String SECRET_KEY, String SALT) {
        try {
            byte[] bytesIV = new byte[16];
            random.nextBytes(bytesIV);
            IvParameterSpec ivspec = new IvParameterSpec(bytesIV);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 1000, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            return " ";
        }
    }
    @PostMapping("/v2")
    public String mainApiv2() {

        long factorial = multiplyNumbers(6);
        String hasil = Long.toString(factorial);
        return encryptV2(hasil,"asd","asd");
    }

    @PostMapping("/")
    public String mainApi() {

        long factorial = multiplyNumbers(6);
        String hasil = Long.toString(factorial);
        return encrypt(hasil,"asd","asd");
    }
}
