package com.hzq.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 */
@Data
@AllArgsConstructor
public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    /**
     * 验证是否过期
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
    public ImageCode(BufferedImage image,String code,int expireIn){
        this.image=image;
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expireIn);
    }
}
