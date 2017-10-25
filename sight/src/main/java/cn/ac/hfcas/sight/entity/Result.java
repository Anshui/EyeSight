package cn.ac.hfcas.sight.entity;

/**
 * Created by Static on 2017/10/10.
 *
 */

public class Result {
    private int horizontal;
    private int vertical;
    private int slopeUp;
    private int slopeDown;
    private int clockwise;
    private int anticlockwise;

    public Result(int horizontal, int vertical, int slopeUp, int slopeDown, int clockwise, int anticlockwise) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.slopeUp = slopeUp;
        this.slopeDown = slopeDown;
        this.clockwise = clockwise;
        this.anticlockwise = anticlockwise;
    }

    public Result() {

    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getSlopeUp() {
        return slopeUp;
    }

    public void setSlopeUp(int slopeUp) {
        this.slopeUp = slopeUp;
    }

    public int getSlopeDown() {
        return slopeDown;
    }

    public void setSlopeDown(int slopeDown) {
        this.slopeDown = slopeDown;
    }

    public int getClockwise() {
        return clockwise;
    }

    public void setClockwise(int clockwise) {
        this.clockwise = clockwise;
    }

    public int getAnticlockwise() {
        return anticlockwise;
    }

    public void setAnticlockwise(int anticlockwise) {
        this.anticlockwise = anticlockwise;
    }
}
