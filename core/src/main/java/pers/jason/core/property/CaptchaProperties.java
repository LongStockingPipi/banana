package pers.jason.core.property;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/4 16:11
 */
public class CaptchaProperties {

  private ImageCaptchaProperties image;

  private SmsCaptchaProperties sms;

  public ImageCaptchaProperties getImage() {
    return image;
  }

  public void setImage(ImageCaptchaProperties image) {
    this.image = image;
  }

  public SmsCaptchaProperties getSms() {
    return sms;
  }

  public void setSms(SmsCaptchaProperties sms) {
    this.sms = sms;
  }
}
