package pers.jason.browser.authentication.captcha.image;

import pers.jason.browser.authentication.captcha.CaptchaGenerator;
import pers.jason.core.property.BananaProperties;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/1 18:19
 */
public class ImageCaptchaGenerator implements CaptchaGenerator {

  private BananaProperties bananaProperties;

  public ImageCaptchaGenerator(BananaProperties bananaProperties) {
    this.bananaProperties = bananaProperties;
  }

  @Override
  public ImageCaptcha generate() {

    // TODO Auto-generated method stub
    ;
    int width = bananaProperties.getCaptcha().getImage().getWidth();
    int height = bananaProperties.getCaptcha().getImage().getHeight();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    Random random = new Random();
    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);
    g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, x + xl, y + yl);
    }
    String sRand = "";
    for (int i = 0; i < bananaProperties.getCaptcha().getImage().getLength(); i++) {
      String rand = String.valueOf(random.nextInt(10));
      sRand += rand;
      g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
      g.drawString(rand, 13 * i + 6, 16);
    }
    g.dispose();
    return new ImageCaptcha(bananaProperties.getCaptcha().getImage().getExpireIn(), sRand, image);
  }

  /**
   * 生成随机背景条纹
   *
   * @param fc
   * @param bc
   * @return
   */
  private Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }
}
