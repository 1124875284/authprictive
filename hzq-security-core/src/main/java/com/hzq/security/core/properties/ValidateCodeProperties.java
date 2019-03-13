/**
 * 
 */
package com.hzq.security.core.properties;

/**
 * 多种验证码
 * @author 黄志强
 *
 */
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();

	public ImageCodeProperties getImage() {
		return image;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}
	
}
