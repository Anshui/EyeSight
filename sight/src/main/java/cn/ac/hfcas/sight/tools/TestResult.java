package cn.ac.hfcas.sight.tools;

/**
 * Created by Static on 2017/09/21.
 * 获取检测结果等级
 */

public class TestResult {

    private int horizontalTime,verticalTime,slopeUpTime,slopeDownTime,clockwiseTime,anticlockwiseTime;

    public TestResult() {
    }

    public TestResult(int horizontalTime, int verticalTime, int slopeUpTime, int slopeDownTime, int clockwiseTime, int anticlockwiseTime) {
        this.horizontalTime = horizontalTime;
        this.verticalTime = verticalTime;
        this.slopeUpTime = slopeUpTime;
        this.slopeDownTime = slopeDownTime;
        this.clockwiseTime = clockwiseTime;
        this.anticlockwiseTime = anticlockwiseTime;
    }

    public int getHorizontalTime() {
        return horizontalTime;
    }

    public void setHorizontalTime(int horizontalTime) {
        this.horizontalTime = horizontalTime;
    }

    public int getVerticalTime() {
        return verticalTime;
    }

    public void setVerticalTime(int verticalTime) {
        this.verticalTime = verticalTime;
    }
    public int getSlopeUpTime() {

        return slopeUpTime;
    }

    public void setSlopeUpTime(int slopeUpTime) {
        this.slopeUpTime = slopeUpTime;
    }

    public int getSlopeDownTime() {
        return slopeDownTime;
    }

    public void setSlopeDownTime(int slopeDownTime) {
        this.slopeDownTime = slopeDownTime;
    }

    public int getClockwiseTime() {
        return clockwiseTime;
    }

    public void setClockwiseTime(int clockwiseTime) {
        this.clockwiseTime = clockwiseTime;
    }

    public int getAnticlockwiseTime() {
        return anticlockwiseTime;
    }

    public void setAnticlockwiseTime(int anticlockwiseTime) {
        this.anticlockwiseTime = anticlockwiseTime;
    }

    public String getHorizontalLevel(){
        String horizontalLevel;
        if (horizontalTime >= Define.HORIZONTAL_MIN && horizontalTime <= Define.HORIZONTAL_A){
            horizontalLevel = Define.A;
        } else if (horizontalTime > Define.HORIZONTAL_A && horizontalTime <= Define.HORIZONTAL_B){
            horizontalLevel = Define.B;
        } else if (horizontalTime > Define.HORIZONTAL_B && horizontalTime <= Define.HORIZONTAL_C){
            horizontalLevel = Define.C;
        } else if (horizontalTime > Define.HORIZONTAL_C && horizontalTime <= Define.HORIZONTAL_MAX){
            horizontalLevel = Define.D;
        } else {
            horizontalLevel = Define.TEST_INVALIDATION;
        }
        return horizontalLevel;
    }

    public String getVerticalLevel(){
        String verticalLevel;
        if (verticalTime >= Define.VERTICAL_MIN && verticalTime <= Define.VERTICAL_A){
            verticalLevel = Define.A;
        } else if (verticalTime > Define.VERTICAL_A && verticalTime <= Define.VERTICAL_B){
            verticalLevel = Define.B;
        } else if (verticalTime > Define.VERTICAL_B && verticalTime <= Define.VERTICAL_C){
            verticalLevel = Define.C;
        } else if (verticalTime > Define.VERTICAL_C && verticalTime <= Define.VERTICAL_MAX){
            verticalLevel = Define.D;
        } else {
            verticalLevel = Define.TEST_INVALIDATION;
        }
        return verticalLevel;
    }

    public String getSlopeUpLevel() {
        String slopeUpLevel;
        if (slopeUpTime >= Define.SLOPE_UP_MIN && slopeUpTime <= Define.SLOPE_UP_A){
            slopeUpLevel = Define.A;
        } else if (slopeUpTime > Define.SLOPE_UP_A && slopeUpTime <= Define.SLOPE_UP_B){
            slopeUpLevel = Define.B;
        } else if (slopeUpTime > Define.SLOPE_UP_B && slopeUpTime <= Define.SLOPE_UP_C){
            slopeUpLevel = Define.C;
        } else if (slopeUpTime > Define.SLOPE_UP_C && slopeUpTime <= Define.SLOPE_UP_MAX){
            slopeUpLevel = Define.D;
        } else {
            slopeUpLevel = Define.TEST_INVALIDATION;
        }
        return slopeUpLevel;
    }

    public String getSlopeDownLevel() {
        String slopeDownLevel;
        if (slopeDownTime >= Define.SLOPE_DOWN_MIN && slopeDownTime <= Define.SLOPE_DOWN_A){
            slopeDownLevel = Define.A;
        } else if (slopeDownTime > Define.SLOPE_DOWN_A && slopeDownTime <= Define.SLOPE_DOWN_B){
            slopeDownLevel = Define.B;
        } else if (slopeDownTime > Define.SLOPE_DOWN_B && slopeDownTime <= Define.SLOPE_DOWN_C){
            slopeDownLevel = Define.C;
        } else if (slopeDownTime > Define.SLOPE_DOWN_C && slopeDownTime <= Define.SLOPE_DOWN_MAX){
            slopeDownLevel = Define.D;
        } else {
            slopeDownLevel = Define.TEST_INVALIDATION;
        }
        return slopeDownLevel;
    }

    public String getClockwiseLevel() {
        String clockwiseLevel;
        if (clockwiseTime >= Define.CLOCKWISE_MIN && clockwiseTime <= Define.CLOCKWISE_A){
            clockwiseLevel = Define.A;
        } else if (clockwiseTime > Define.CLOCKWISE_A && clockwiseTime <= Define.CLOCKWISE_B){
            clockwiseLevel = Define.B;
        } else if (clockwiseTime > Define.CLOCKWISE_B && clockwiseTime <= Define.CLOCKWISE_C){
            clockwiseLevel = Define.C;
        } else if (clockwiseTime > Define.CLOCKWISE_C && clockwiseTime <= Define.CLOCKWISE_MAX){
            clockwiseLevel = Define.D;
        } else {
            clockwiseLevel = Define.TEST_INVALIDATION;
        }
        return clockwiseLevel;
    }

    public String getAnticlcokwiseLevel() {
        String anticlcokwiseLevel;
        if (anticlockwiseTime >= Define.ANTICLOCKWISE_MIN && anticlockwiseTime <= Define.ANTICLOCKWISE_A){
            anticlcokwiseLevel = Define.A;
        } else if (anticlockwiseTime > Define.ANTICLOCKWISE_A && anticlockwiseTime <= Define.ANTICLOCKWISE_B){
            anticlcokwiseLevel = Define.B;
        } else if (anticlockwiseTime > Define.ANTICLOCKWISE_B && anticlockwiseTime <= Define.ANTICLOCKWISE_C){
            anticlcokwiseLevel = Define.C;
        } else if (anticlockwiseTime > Define.ANTICLOCKWISE_C && anticlockwiseTime <= Define.ANTICLOCKWISE_MAX){
            anticlcokwiseLevel = Define.D;
        } else {
            anticlcokwiseLevel = Define.TEST_INVALIDATION;
        }
        return anticlcokwiseLevel;
    }

    /**
     * 获取3、4、5、6 平均 等级
     * @return
     */
    public String getAvg(){
        int avg = (slopeUpTime+slopeDownTime+clockwiseTime+anticlockwiseTime)/4;
        String AvgLevel;
        if (avg >= Define.AVG_MIN && avg <= Define.AVG_A){
            AvgLevel = Define.A;
        } else if (avg > Define.AVG_A && avg <= Define.AVG_B){
            AvgLevel = Define.B;
        } else if (avg > Define.AVG_B && avg <= Define.AVG_C){
            AvgLevel = Define.C;
        } else if (avg > Define.AVG_C && avg <= Define.AVG_MAX){
            AvgLevel = Define.D;
        } else {
            AvgLevel = Define.TEST_INVALIDATION;
        }
        return AvgLevel;
    }
}
