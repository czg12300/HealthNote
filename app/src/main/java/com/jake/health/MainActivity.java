
package com.jake.health;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import cn.common.ui.activity.BaseWorkerFragmentActivity;

public class MainActivity extends BaseWorkerFragmentActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
