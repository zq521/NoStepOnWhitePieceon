package com.xhz.nosteponwhitepieceon;

import top.yunp.androidgame2d.display.Rectangle;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/5.
 * 10:51
 * <p>
 * 构造Rect方块对象，在构造该对象前保证以调用了Config.getCardWitch(), Config.getCardHeihht()
 */
public class Rect extends Rectangle {
    private boolean black = false;

    public Rect(boolean isBlack) {
        super(Config.getCardWitch(), Config.getCardHeihht(), isBlack ? 0xff000000 : 0xffffffff);
        black = isBlack;
    }


    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
        setColor(black ? 0xff000000 : 0xffffffff);
    }

}
