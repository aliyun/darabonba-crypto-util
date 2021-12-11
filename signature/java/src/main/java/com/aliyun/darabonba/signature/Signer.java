// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.darabonba.signature;

import com.aliyun.tea.*;
import com.aliyun.darabonba.encode.Encoder;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

public class Signer {
    private final static String ENCODING = "UTF-8";
    private final static String PEM_BEGIN = "-----BEGIN RSA PRIVATE KEY-----\n";
    private final static String PEM_END = "\n-----END RSA PRIVATE KEY-----";

    /**
     * HmacSHA1 Signature
     *
     * @param stringToSign string
     * @param secret       string
     * @return signed bytes
     */
    public static byte[] HmacSHA1Sign(String stringToSign, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(secret.getBytes(ENCODING), "HmacSHA1"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(ENCODING));
        return signData;
    }

    /**
     * HmacSHA256 Signature
     *
     * @param stringToSign string
     * @param secret       string
     * @return signed bytes
     */
    public static byte[] HmacSHA256Sign(String stringToSign, String secret) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] signData = sha256_HMAC.doFinal(stringToSign.getBytes());
        return signData;
    }

    /**
     * HmacSM3 Signature
     *
     * @param stringToSign string
     * @param secret       string
     * @return signed bytes
     */
    public static byte[] HmacSM3Sign(String stringToSign, String secret) throws Exception {
        SecretKey key = new SecretKeySpec((secret).getBytes(ENCODING), "HMAC-SM3");
        HMac mac = new HMac(new SM3Digest());
        byte[] signData = new byte[mac.getMacSize()];
        byte[] inputBytes = stringToSign.getBytes(ENCODING);
        mac.init(new KeyParameter(key.getEncoded()));
        mac.update(inputBytes, 0, inputBytes.length);
        mac.doFinal(signData, 0);
        return signData;
    }

    /**
     * SHA256withRSA Signature
     *
     * @param stringToSign string
     * @param secret       string
     * @return signed bytes
     */
    public static byte[] SHA256withRSASign(String stringToSign, String secret) throws Exception {
        Signature rsaSign = Signature.getInstance("SHA256withRSA");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        byte[] keySpec = Encoder.base64Decode(checkRSASecret(secret));
        PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(keySpec));
        rsaSign.initSign(privateKey);
        rsaSign.update(stringToSign.getBytes(ENCODING));
        byte[] signData = rsaSign.sign();
        return signData;
    }

    private static String checkRSASecret(String accessKeySecret) {
        if (accessKeySecret != null) {
            if (accessKeySecret.startsWith(PEM_BEGIN)) {
                accessKeySecret = accessKeySecret.replace(PEM_BEGIN, "");
            }
            while (accessKeySecret.endsWith("\n") || accessKeySecret.endsWith("\r")) {
                accessKeySecret = accessKeySecret.substring(0, accessKeySecret.length() - 1);
            }
            if (accessKeySecret.endsWith(PEM_END)) {
                accessKeySecret = accessKeySecret.replace(PEM_END, "");
            }
        }
        return accessKeySecret;
    }

    /**
     * MD5 Signature
     *
     * @param stringToSign string
     * @return signed bytes
     */
    public static byte[] MD5Sign(String stringToSign) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] signData = md.digest(stringToSign.getBytes(ENCODING));
        return signData;
    }

}
