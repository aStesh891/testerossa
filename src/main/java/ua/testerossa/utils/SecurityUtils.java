package ua.testerossa.utils;

import com.sun.mail.util.BASE64EncoderStream;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class SecurityUtils {

  public static final String SECRET_KEY_64 = "KaPdSgVk";
  public static final String SECRET_KEY_128 = "cRfUjXnZr4u7x!A%";
  public static final String SECRET_KEY_256 = "q4t7w!z%C*F-JaNcRfUjXn2r5u8x/A?D";
  public static final String SECRET_KEY_512 = "WmZq4t7w!z%C*F-JaNcRfUjXn2r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B&E)H@McQ";
  private static final String SALT = "ssshhhhhhhhhhh!!!!";

  public static String des(String strToEncrypt) {
    try {
      SecretKey key = KeyGenerator.getInstance("DES").generateKey();
      Cipher ecipher = Cipher.getInstance("DES");
      ecipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] utf8 = strToEncrypt.getBytes(StandardCharsets.UTF_8);
      byte[] enc = ecipher.doFinal(utf8);
      enc = BASE64EncoderStream.encode(enc);
      return new String(enc);
    } catch (Exception e) {
      System.out.println("Error occured during des encryption: " + e.toString());
    }
    return null;
  }

  public static String tripleDes(String strToEncrypt) {
    try {
      final MessageDigest md = MessageDigest.getInstance("md5");
      final byte[] digestOfPassword = md.digest(SECRET_KEY_128.getBytes(StandardCharsets.UTF_8));
      final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      for (int j = 0, k = 16; j < 8; ) {
        keyBytes[k++] = keyBytes[j++];
      }

      final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
      final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] plainTextBytes = strToEncrypt.getBytes(StandardCharsets.UTF_8);
      return Base64.getEncoder()
          .encodeToString(cipher.doFinal(plainTextBytes));
    } catch (Exception e) {
      System.out.println("Error occured during des encryption: " + e.toString());
    }
    return null;
  }

  public static String blowfish(String strToEncrypt) {
    try {
      byte[] KeyData = SECRET_KEY_128.getBytes(StandardCharsets.UTF_8);
      SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
      Cipher cipher = Cipher.getInstance("Blowfish");
      cipher.init(Cipher.ENCRYPT_MODE, KS);
      return Base64.getEncoder().
          encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      System.out.println("Error occured during blowfish encryption: " + e.toString());
    }
    return null;
  }

  public static String md5(String strToEncrypt) {
    try {
      StringBuilder code = new StringBuilder();
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      byte[] bytes = strToEncrypt.getBytes(StandardCharsets.UTF_8);
      byte[] digest = messageDigest.digest(bytes);

      for (byte aDigest : digest) {
        code.append(Integer.toHexString(0x0100 + (aDigest & 0x00FF)).substring(1));
      }
      return code.toString();
    } catch (Exception e) {
      System.out.println("Error occured during md5 encryption: " + e.toString());
    }
    return null;
  }

  public static String sha256(String strToEncrypt) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(strToEncrypt.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().
          encodeToString(hash);
    } catch (Exception e) {
      System.out.println("Error occured during sha256 encryption: " + e.toString());
    }
    return null;
  }

  public static String sha512(String passwordToHash) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for (byte aByte : bytes) {
        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();
    } catch (Exception e) {
      System.out.println("Error occured during sha512 encryption: " + e.toString());
    }
    return null;
  }

  public static String aes(String strToEncrypt, String key) {
    log.info("aes: strToEncrypt={}", strToEncrypt);
    try {
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      IvParameterSpec ivspec = new IvParameterSpec(iv);

      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(key.toCharArray(), SALT.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
      return Base64.getEncoder()
          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }

  public static String decrypt(String strToDecrypt, String key) {
    log.info("decrypt: strToEncrypt={}", strToDecrypt);
    try {
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      IvParameterSpec ivspec = new IvParameterSpec(iv);

      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(key.toCharArray(), SALT.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
  }
}
