package com.company;

public class Seek  {
    private int x;
    private int y;
    private int hWOfSeek;
    private int lengthOfLine;
    private int coordinatesOfStartX;
    private int coordinatesOfStartY;
    private int lengthOfRedPointLine;
    private int lengthOfAllSeekBar;
    private int widthVerticalLine;


    public int getCoordinatesOfStartY() {
        return coordinatesOfStartY;
    }

    public void setCoordinatesOfStartY(int coordinatesOfStartY) {
        this.coordinatesOfStartY = coordinatesOfStartY;
    }


    public int getWidthVerticalLine() {
        return widthVerticalLine;
    }

    public void setWidthVerticalLine(int widthVerticalLine) {
        this.widthVerticalLine = widthVerticalLine;
        sethWOfSeek(getWidthVerticalLine() * 2);
    }


    public int getLengthOfRedPointLine() {
        return lengthOfRedPointLine;
    }

    public void setLengthOfRedPointLine(int lengthOfRedPointLine) {
        this.lengthOfRedPointLine = lengthOfRedPointLine;
    }


    public int getLengthOfAllSeekBar() {
        return lengthOfAllSeekBar;
    }

    public void setLengthOfAllSeekBar(int lengthOfAllSeekBar) {
        this.lengthOfAllSeekBar = lengthOfAllSeekBar;
        setX(getLengthOfAllSeekBar()/2 + getCoordinatesOfStartX() - gethWOfSeek()/2);
        setY(getCoordinatesOfStartY());
        System.out.println("get Y = "+getY());
        setLengthOfRedPointLine(getLengthOfAllSeekBar()/25);
        setLengthOfLine(getLengthOfRedPointLine());
    }


    public int getCoordinatesOfStartX() {
        return coordinatesOfStartX;
    }

    public void setCoordinatesOfStartX(int coordinatesOfStartX) {
        this.coordinatesOfStartX = coordinatesOfStartX;
    }


    public int getLengthOfLine() {
        return lengthOfLine;
    }

    public void setLengthOfLine(int lengthOfLine) {
        this.lengthOfLine = lengthOfLine;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int gethWOfSeek() {
        return hWOfSeek;
    }

    public void sethWOfSeek(int hWOfSeek) {
        this.hWOfSeek = hWOfSeek;
    }

    public int getForCenterX() {
        return x - hWOfSeek /2;
    }

    public int getForCenterY() {
        return y - hWOfSeek /2;
    }
}
