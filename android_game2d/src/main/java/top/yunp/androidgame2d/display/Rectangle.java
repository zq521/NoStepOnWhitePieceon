package top.yunp.androidgame2d.display;

import android.graphics.Path;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/5.
 * 10:41
 */

public class Rectangle extends Shape {
    private float width = 100, height = 100;
    private int color = 0xff000000;
    private int borderWidth = 0;
    private int borderColor = 0xffffffff;


    public Rectangle(float width, float height, int color) {
        setWidth(width);
        setHeight(height);
        setColor(color);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        refershRectangle();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        refershRectangle();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
       getPaint().setColor(color);

    }

    private void refershRectangle() {
        getPath().reset();
        getPath().addRect(getBorderWidth(), getBorderWidth(), getWidth() - getBorderWidth(), getHeight() - getBorderWidth(), Path.Direction.CW);
        getPath().close();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        refershRectangle();
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }
}

