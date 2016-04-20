
package com.jake.health.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.jake.health.R;
import com.jake.health.ui.base.BaseWorkerFragmentActivity;


public class MainActivity extends BaseWorkerFragmentActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
