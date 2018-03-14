package com.culturer.yoo_home.config;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public interface CaptchaConfig {
//    [options == 0 生成验证码]
//    [options == 1 验证验证码]
    int OPTIONS_CREATE =  0;
    int OPTIONS_VERFIY =  1;

//    生成验证码
//    [captcha_type == 0 数字验证码]
//    [captcha_type == 1 声音验证码]
//    [captcha_type == 2 公式验证码]
    String TYPE = "captcha_type";
    int TYPE_NUMBER = 0;
    int TYPE_AUDIO = 1;
    int TYPE_FORMULA = 2;
//    验证码密钥
    String KEY = "key";
//    验证码结果
    String VALUE = "value";
}
