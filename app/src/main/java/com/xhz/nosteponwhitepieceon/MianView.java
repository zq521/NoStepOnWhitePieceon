package com.xhz.nosteponwhitepieceon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.GameView;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/2.
 * 14:51
 */

public class MianView extends GameView {

    private RectLine.OnRectSelected rectSelectedhandler = new RectLine.OnRectSelected() {
        @Override
        public void onSelect(Rect rect, RectLine target) {
//            System.out.println(rect+" ,"+target);
            if (rect.isBlack()) {
                addNewLineAtIndex(-1);
                moveAllLineDown();
            } else {
                Toast.makeText(getContext(), "点错了", Toast.LENGTH_SHORT).show();
            }
        }

    };

    private List<RectLine> rectLines = new ArrayList<>();
    private RectLine.OnLineMoveDownTweenEnd limeMoveTweenEndHandler=new RectLine.OnLineMoveDownTweenEnd() {
        @Override
        public void onEnd(RectLine target) {
            if (target.getPositionIndex()>=4){
                rectLines.remove(target);
                remove(target);
            }
        }
    };

    public MianView(Context context) {
        super(context);
        setFps(50);
        setGameViewBackground(0xff000000);
        shouldStartGame();
    }

    private void shouldStartGame() {
        new AlertDialog.Builder(getContext())
                .setTitle("欢迎使用")
                .setMessage("点击开始按钮，开始游戏")
                .setPositiveButton("开始 ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGamne();
                    }
                }).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        }).show();
    }

    private void startGamne() {
        initProperties();
        addRectLines();
    }

    private void addRectLines() {
        for (int i = 0; i < 4; i++) {
            addNewLineAtIndex(i);
        }
    }

    private void addNewLineAtIndex(int index) {
        RectLine line = new RectLine();
        line.setOnRectSelected(rectSelectedhandler);
        line.setPositionIndex(index);
        line.setPositionYByIndex();
        line.setOnlineMoveDownTweenEnd(limeMoveTweenEndHandler);
        add(line);
        rectLines.add(line);
    }

    private void moveAllLineDown(){
        for (RectLine rl:rectLines){
            rl.setPositionIndex(rl.getPositionIndex()+1);
            rl.moveToTargetPositionByIndex();
        }
    }

    private void initProperties() {
        Config.setScrenWidth(getWidth());
        Config.setScrenHeight(getHeight());
        Config.setCardWitch(getWidth() / 4);
        Config.setCardHeihht(getHeight() / 4);
    }

    private MainActivity getActivity() {
        return (MainActivity) getContext();
    }

}
