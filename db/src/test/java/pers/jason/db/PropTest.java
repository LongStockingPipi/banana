package pers.jason.db;

import org.junit.Assert;
import org.junit.Test;
import pers.jason.db.support.Properties;

import java.io.IOException;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 22:36
 */
public class PropTest {

  private final String conFile = "D:\\WorkSpace\\2019-10\\banana\\db\\src\\main\\resources\\application.properties";

  @Test
  public void testForLoadFile() throws IOException {
    Properties properties = new Properties(null, conFile);
    Assert.assertEquals("D:\\files\\banana", properties.get("h2.path"));
  }

  public static void main(String[] args) {
    System.out.println(System.getProperty("user.dir"));
  }

}
