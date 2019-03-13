package com.hzq.security.core.properties;

public class BrowserProperties {

    private String longinPage="/hzq-signIn.html";

    private LoginType loginType=LoginType.JSON;
    public String getLonginPage() {
        return longinPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public void setLonginPage(String longinPage) {
        this.longinPage = longinPage;
    }
}
