package ua.testerossa.utils;

import org.junit.Assert;
import org.junit.Test;

public class SecurityUtilsTest {

  @Test
  public void aes128() {
    long time = System.currentTimeMillis();
    SecurityUtils.aes(SecurityUtils.SECRET_KEY_128, JSON);
    System.out.println("aes128 time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void aes256() {
    /*long time = System.currentTimeMillis();
    SecurityUtils.aes(SecurityUtils.SECRET_KEY_256, JSON);
    System.out.println("aes256 time:" + (System.currentTimeMillis() - time));*/

    long time = System.currentTimeMillis();
    SecurityUtils.aes(SecurityUtils.SECRET_KEY_256, "admin");
    System.out.println("aes256 time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void des() {
    long time = System.currentTimeMillis();
    SecurityUtils.des(JSON);
    System.out.println("des time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void tripleDes() {
    long time = System.currentTimeMillis();
    SecurityUtils.tripleDes(JSON);
    System.out.println("tripleDes time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void md5() {
    long time = System.currentTimeMillis();
    SecurityUtils.md5(JSON);
    System.out.println("md5 time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void blowfish() {
    long time = System.currentTimeMillis();
    SecurityUtils.blowfish(JSON);
    System.out.println("blowfish time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void sha256() {
    long time = System.currentTimeMillis();
    SecurityUtils.sha256(JSON);
    System.out.println("sha256 time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void sha512() {
    long time = System.currentTimeMillis();
    SecurityUtils.sha512(JSON);
    System.out.println("sha512 time:" + (System.currentTimeMillis() - time));
  }

  @Test
  public void decrypt() {
    System.out.println(SecurityUtils.decrypt("IGRcBPzczz9a2JMQfvWtsKGLPMfwz/fNs9u35zc1XckKGtJ8/AdE72ZrU6n5pgHH", SecurityUtils.SECRET_KEY_256));
    Assert.assertEquals("test", SecurityUtils.decrypt(SecurityUtils.aes("test", SecurityUtils.SECRET_KEY_256), SecurityUtils.SECRET_KEY_256));
  }

  private static String JSON = "{\n" +
      "    \"_id\": \"6333fea5feb6d5ef2cea1d5c\",\n" +
      "    \"index\": 3,\n" +
      "    \"guid\": \"11941470-495b-4f0c-a296-1ead3d39eb95\",\n" +
      "    \"isActive\": true,\n" +
      "    \"balance\": \"$2,327.47\",\n" +
      "    \"picture\": \"http://placehold.it/32x32\",\n" +
      "    \"age\": 36,\n" +
      "    \"eyeColor\": \"green\",\n" +
      "    \"name\": \"Morton Carson\",\n" +
      "    \"gender\": \"male\",\n" +
      "    \"company\": \"TRIBALOG\",\n" +
      "    \"email\": \"mortoncarson@tribalog.com\",\n" +
      "    \"phone\": \"+1 (967) 597-3181\",\n" +
      "    \"address\": \"364 Temple Court, Martell, Virgin Islands, 4579\",\n" +
      "    \"about\": \"Dolor esse irure eu occaecat voluptate mollit deserunt culpa laborum ad ad dolor. Laboris exercitation ut veniam ad ex exercitation. Ea nisi id occaecat labore nostrud reprehenderit enim. Fugiat proident et non voluptate cillum Lorem qui aute id. Ut excepteur labore nisi et voluptate duis est reprehenderit esse. Amet officia qui aute qui.\\r\\n\",\n" +
      "    \"registered\": \"2016-03-27T01:57:23 -03:00\",\n" +
      "    \"latitude\": 38.467365,\n" +
      "    \"longitude\": 36.780056,\n" +
      "    \"tags\": [\n" +
      "      \"dolore\",\n" +
      "      \"irure\",\n" +
      "      \"esse\",\n" +
      "      \"veniam\",\n" +
      "      \"reprehenderit\",\n" +
      "      \"occaecat\",\n" +
      "      \"in\"\n" +
      "    ],\n" +
      "    \"friends\": [\n" +
      "      {\n" +
      "        \"id\": 0,\n" +
      "        \"name\": \"Yang Mack\"\n" +
      "      },\n" +
      "      {\n" +
      "        \"id\": 1,\n" +
      "        \"name\": \"Louise Ware\"\n" +
      "      },\n" +
      "      {\n" +
      "        \"id\": 2,\n" +
      "        \"name\": \"Baker Juarez\"\n" +
      "      }\n" +
      "    ],\n" +
      "    \"greeting\": \"Hello, Morton Carson! You have 8 unread messages.\",\n" +
      "    \"favoriteFruit\": \"strawberry\"\n" +
      "  }";
}