/**
 * 
 */
package com.hzq.security.core.social.qq.connet;

import com.hzq.security.core.social.qq.api.QQ;
import com.hzq.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;


/**
 * @author zhailiang
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 用来测试qq的服务 是否可用
	 * @param api
	 * @return
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	/**
	 * 获取相关数据
	 * @param api
	 * @param values
	 */

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		//用户名
		values.setDisplayName(userInfo.getNickname());
		//qq的头像
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		//服务商的用户id
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		//do noting
	}

}
