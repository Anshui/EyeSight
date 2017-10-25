package cn.ac.hfcas.sight.entity;

/**
 * Created by Static on 2017/09/25.
 *
 */

public class UserLogin {

    private String userName;
    private String userPwd;

    public UserLogin() {
    }

    public UserLogin(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
