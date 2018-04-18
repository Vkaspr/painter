package com.company;

import java.awt.*;
import java.util.LinkedList;

public class CustomizeLinkedList<T> extends LinkedList<T> {

    private Color color;
    private int width;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
