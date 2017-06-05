package com.xhz.nosteponwhitepieceon;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private  MianView mianView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mianView = new MianView(this);
        setContentView(mianView);
    }
}
