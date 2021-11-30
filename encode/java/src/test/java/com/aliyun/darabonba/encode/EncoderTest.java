package com.aliyun.darabonba.encode;

import org.junit.Assert;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class EncoderTest {

    @Test
    public void urlEncodeTest() throws UnsupportedEncodingException {
        String value = "123abc!@#$%^&*()-=_+ ~|\\/";
        String res = Encoder.urlEncode(value);
        Assert.assertEquals("123abc%21%40%23%24%25%5E%26*%28%29-%3D_%2B+%7E%7C%5C%2F", res);
    }

    @Test
    public void percentEncodeTest() throws UnsupportedEncodingException {
        String value = "123abc!@#$%^&*()-=_+ ~|\\/";
        String res = Encoder.percentEncode(value);
        Assert.assertEquals("123abc%21%40%23%24%25%5E%26%2A%28%29-%3D_%2B%20~%7C%5C%2F", res);
    }

    @Test
    public void pathEncode() {

    }

    @Test
    public void hexEncode() {
        byte[] raw = { 20 , 10 , 30 , 5 };
        assertEquals("140a1e05", Encoder.hexEncode(raw));
    }

    @Test
    public void hash() throws Exception {
        String s = "阿里云,coding the world!";
        byte[] raw = s.getBytes();
    }

    @Test
    public void base64EncodeToString() throws Exception {
        String str1 = "阿里云,coding the world!";
        String str2 = "ABCabc0123456789+/123abc!@#$%^&*()-=_+ ~|\\/";
        byte[] raw1 = str1.getBytes("UTF-8");
        byte[] raw2 = str2.getBytes("UTF-8");
        String base64Str1 = Encoder.base64EncodeToString(raw1);
        String base64Str2 = Encoder.base64EncodeToString(raw2);
        assertEquals("6Zi/6YeM5LqRLGNvZGluZyB0aGUgd29ybGQh", base64Str1);
        assertEquals("QUJDYWJjMDEyMzQ1Njc4OSsvMTIzYWJjIUAjJCVeJiooKS09XysgfnxcLw==", base64Str2);
    }

    @Test
    public void base64Decode() throws Exception {
        String base64Str = "6Zi/6YeM5LqRLGNvZGluZyB0aGUgd29ybGQh";
        byte[] res = Encoder.base64Decode(base64Str);
        assertEquals("阿里云,coding the world!", new String(res, StandardCharsets.UTF_8));
    }
}