package com.xhz.nosteponwhitepieceon;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.Container;
import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.androidgame2d.events.TweenEvent;
import top.yunp.androidgame2d.tween.TranslateTween;
import top.yunp.androidgame2d.tween.Tween;
import top.yunp.lib.java.event.EventListener;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/5.
 * 10:54
 */

public class RectLine extends Container {

    private final EventListener<TouchEvent, Display> recttouchDownHanlder = new EventListener<TouchEvent, Display>() {
        @Override
        public boolean onReceive(TouchEvent event, Display display) {
            if (display instanceof Rect) {
                if (getOnRectSelected() != null) {
                    getOnRectSelected().onSelect((Rect) display, RectLine.this);

                }
            }
            return false;
        }
    };
    private final EventListener<TweenEvent, Tween> tweenEndHandler = new EventListener<TweenEvent, Tween>() {
        @Override
        public boolean onReceive(TweenEvent event, Tween tween) {
            tweenRunning = false;
            if (getOnlineMoveDownTweenEnd()!=null){
                getOnlineMoveDownTweenEnd().onEnd(RectLine.this);
            }

                return false;
        }
    };
    private List<Rect> rects = new ArrayList<>();

    public RectLine() {
        Rect r;
        for (int i = 0; i < 4; i++) {
            r = new Rect(false);
            r.setBorderWidth(5);//设置便可宽度
            r.setX(Config.getCardWitch() * i);
            add(r);
            rects.add(r);
            r.touchDown.add(recttouchDownHanlder);
        }
        rects.get((int) (Math.random() * rects.size())).setBlack(true);
        //Create a TranslateTween object which is using to move this line down
        tt = new TranslateTween(this, 0, 0, 0, 0);
        tt.tweenEnd.add(tweenEndHandler);
        tt.setFrames(5);
    }

    private int positionIndex = 0;
    private OnRectSelected onRectSelected = null;
    public interface OnRectSelected {
        void onSelect(Rect rect, RectLine target);
    }

    public OnRectSelected getOnRectSelected() {
        return onRectSelected;
    }

    public void setOnRectSelected(OnRectSelected onRectSelected) {
        this.onRectSelected = onRectSelected;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int index) {
        if (!tweenRunning) {
            this.positionIndex = index;
            this.setTouchEnable(index == 3);//等于3才可以被点击
        }
    }

    public void setPositionYByIndex() {
        setY(Config.getCardHeihht() * getPositionIndex());
    }

    public void moveToTargetPositionByIndex(){
        if (!tweenRunning){
            tweenRunning=true;
            tt.setStartY(getY());
            tt.setEndY(getPositionIndex()*Config.getCardHeihht());
            tt.start();
        }
    }


    private TranslateTween tt;
    private boolean tweenRunning = false;
    private OnLineMoveDownTweenEnd onlineMoveDownTweenEnd=null;

    public void setOnlineMoveDownTweenEnd(OnLineMoveDownTweenEnd onlineMoveDownTweenEnd) {
        this.onlineMoveDownTweenEnd = onlineMoveDownTweenEnd;
    }

    public OnLineMoveDownTweenEnd getOnlineMoveDownTweenEnd() {
        return onlineMoveDownTweenEnd;
    }

    public interface OnLineMoveDownTweenEnd {
        void onEnd(RectLine target);
    }




}
