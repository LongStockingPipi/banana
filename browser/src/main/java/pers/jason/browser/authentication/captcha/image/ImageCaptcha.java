package pers.jason.browser.authentication.captcha.image;

import pers.jason.browser.authentication.captcha.Captcha;

import java.awt.image.BufferedImage;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/1 18:16
 */
public class ImageCaptcha extends Captcha {

  private String code;

  private BufferedImage image;

  /**
   * @param expireTime expiration time of the check code. The unit of the parameter is seconds.
   */
  public ImageCaptcha(long expireTime, String code, BufferedImage image) {
    super(expireTime);
    this.code = code;
    this.image = image;
  }

  @Override
  public String getValue() {
    return code;
  }

  public BufferedImage getImage() {
    return image;
  }
}
