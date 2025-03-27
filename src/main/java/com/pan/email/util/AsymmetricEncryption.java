package com.pan.email.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class AsymmetricEncryption {

    public static void main(String[] args) throws Exception{
        String input="3333";//需要加密的数据
        String s = rsaEncrypt(input);
        System.out.println(s);
        System.out.println(decryptRSA(s));

        String algorithm="RSA";//加密算法
        //生成密钥对并保存在本地文件中
//        generateKeyToFile(algorithm,"a.pub","a.pri");
        //读取私钥
        PrivateKey privateKey = getPrivateKey("a.pri", algorithm);
        //读取公钥
        PublicKey publicKey = getPublicKey("a.pub", algorithm);
        //加密
//        String en=encryptRSA(algorithm, privateKey, input);
//        System.out.println("加密:"+en);
        //解密
//        String de=decryptRSA(algorithm, publicKey, en);
//        System.out.println("解密:"+de);
    }

    public static String rsaEncrypt(String input) {
        String result = "";
        try {
            PrivateKey privateKey = getPrivateKey("a.pri", "RSA");
            // 加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] inputArray = input.getBytes();
            int inputLength = inputArray.length;
            System.out.println("加密字节数：" + inputLength);
            // 最大加密字节数，超出最大字节数需要分组加密
            int MAX_ENCRYPT_BLOCK = 117;
            // 标识
            int offSet = 0;
            byte[] resultBytes = {};
            byte[] cache = {};
            while (inputLength - offSet > 0) {
                if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                    offSet += MAX_ENCRYPT_BLOCK;
                } else {
                    cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                    offSet = inputLength;
                }
                resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
            }
//            result = Base64.encodeToString(resultBytes);
            return Base64.encodeBase64String(resultBytes);
        } catch (Exception e) {
            System.out.println("rsaEncrypt error:" + e.getMessage());
        }
        return result;
    }

    /**
     * 私钥加密
     * @param input  原文
     * @return
     * @throws Exception
     */
    public static String encryptRSA(String input){
        try {
            PrivateKey privateKey = getPrivateKey("a.pri", "RSA");
            //创建加密对象
            Cipher cipher=Cipher.getInstance("RSA");
            //对加密进行初始化,第一个参数是：加密的模式
            //第二个参数是:你想使用公钥加密还是私钥加密,这里使用的是私钥加密
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] bytes=cipher.doFinal(input.getBytes());
            return Base64.encodeBase64String(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
     /*public static String privateKeyDecrypt(String str) throws Exception {
       String algorithm = "RSA";
        PublicKey publicKey = getPublicKey("a.pub", algorithm);
        Cipher cipher=Cipher.getInstance(algorithm);
        //注意:如果使用公钥加密就必须使用私钥解密,使用私钥加密就必须使用公钥解密,否则会出现异常
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        //把base64密文转回字节数组
        byte[] decode=Base64.decodeBase64(str);

        //64位解码加密后的字符串
        *//*byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);*//*
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decode));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
//        String outStr = new String(cipher.doFinal(inputByte));
        //当长度过长的时候，需要分割后解密 128个字节
        String outStr = new String(getMaxResultDecrypt(str, cipher));
        return outStr;
    }*/

    private static byte[] getMaxResultDecrypt(String str, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] inputArray = Base64.decodeBase64(str.getBytes("UTF-8"));
        int inputLength = inputArray.length;
        // 最大解密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 128;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    /**
     * 公钥解密
     * @param encrypted 密文
     * @return
     * @throws Exception
     */
    public static String decryptRSA(String encrypted){
        try {
            String algorithm = "RSA";
            PublicKey publicKey = getPublicKey("a.pub", algorithm);
            Cipher cipher=Cipher.getInstance(algorithm);
            //注意:如果使用公钥加密就必须使用私钥解密,使用私钥加密就必须使用公钥解密,否则会出现异常
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            //把base64密文转回字节数组
            byte[] decode=Base64.decodeBase64(encrypted);
//            byte[] bytes1=cipher.doFinal(decode);
            //这里要用new String()转字符串,不能用.toString()方法,否则返回的是哈希值
            return new String(getMaxResultDecrypt(encrypted, cipher));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 把公钥和私钥保存到根目录
     * @param algorithm 算法
     * @param pubPath	公钥路径
     * @param priPath	私钥路径
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private static void generateKeyToFile(String algorithm,String pubPath,String priPath) throws NoSuchAlgorithmException, IOException {
        // 密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 初始化密钥长度
        keyPairGenerator.initialize(1024);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 获取私钥的字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 使用base64进行编码
        String privateEncodeString = Base64.encodeBase64String(privateKeyEncoded);
        System.out.println("私钥:" + privateEncodeString);
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取公钥的字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 使用base64进行编码
        String publicEncodeString = Base64.encodeBase64String(publicKeyEncoded);
        System.out.println("公钥:" + publicEncodeString);
        //把公钥和私钥保存到根目录
        FileUtils.writeStringToFile(new File(pubPath), publicEncodeString,Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateEncodeString,Charset.forName("UTF-8"));
    }
    /**
     * 读取私钥
     * @param priPath 私钥的路径
     * @param algorithm 算法
     * @return
     * @throws Exception 返回私钥的key对象
     */
    private static PrivateKey getPrivateKey(String priPath,String algorithm) throws Exception{
        String privateKeyString=FileUtils.readFileToString(new File(priPath),Charset.defaultCharset());
        //创建key的工厂
        KeyFactory keyFactory=KeyFactory.getInstance(algorithm);
        //创建私钥key的规则
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        //返回私钥对象
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 读取公钥
     * @param algorithm 算法
     * @return
     * @throws Exception 返回公钥的key对象
     */
    private static PublicKey getPublicKey(String publicPath,String algorithm) throws Exception{
        String publicKeyString=FileUtils.readFileToString(new File(publicPath),Charset.defaultCharset());
        //创建key的工厂
        KeyFactory keyFactory=KeyFactory.getInstance(algorithm);
        //创建公钥key的规则
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        //返回公钥对象
        return keyFactory.generatePublic(keySpec);

    }
}
