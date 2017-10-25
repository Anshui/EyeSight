package cn.ac.hfcas.sight.entity;

/**
 * Created by Static on 2017/09/25.
 *
 */

public class UserInfo {

    private String userName;
    private String userPwd;
    private String userPhone;
    private double eyeSight;
    private String sex;
    private int userAge;
    private int height;
    private double weight;
    private String visionProblem;
    private Result result;


    public UserInfo() {
    }

    public UserInfo( String userName, String userPwd, String userPhone, double eyeSight,String sex,
                     int userAge, int height, double weight, String visionProblem,Result result) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.eyeSight = eyeSight;
        this.sex = sex;
        this.userAge = userAge;
        this.height = height;
        this.weight = weight;
        this.visionProblem = visionProblem;
        this.result = result;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public double getEyeSight() {
        return eyeSight;
    }

    public void setEyeSight(double eyeSight) {
        this.eyeSight = eyeSight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getVisionProblem() {
        return visionProblem;
    }

    public void setVisionProblem(String visionProblem) {
        this.visionProblem = visionProblem;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
