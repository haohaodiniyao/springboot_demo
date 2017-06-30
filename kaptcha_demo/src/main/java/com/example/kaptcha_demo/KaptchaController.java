package com.example.kaptcha_demo;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by yaokai on 2017/6/30.
 */
@RestController
public class KaptchaController {
    @Autowired
    private Producer captchaProducer;
    @RequestMapping("/producer")
    public String producer(){
        String text = captchaProducer.createText();
        BufferedImage bufferedImage = captchaProducer.createImage(text);
        try {
            ImageIO.write(bufferedImage, "gif", new File(text + ".gif"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }
}
