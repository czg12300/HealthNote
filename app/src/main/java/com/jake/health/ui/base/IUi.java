
package com.jake.health.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import java.util.List;

public interface IUi {
    void setupSystemActions(List<String> actions);
    void setupLocalActions(List<String> actions);

    void handleLocalBroadcast(Context context, Intent intent);
    void handleSystemBroadcast(Context context, Intent intent);

    void handleUiMessage(Message msg);

    void goActivity(Class<?> clazz);

    void goActivity(Class<?> clazz, Bundle bundle);

    void goActivityForResult(Class<?> clazz);

    void goActivityForResult(Class<?> clazz, Bundle bundle);

    void goActivityForResult(Class<?> clazz, Bundle bundle, int requestCode);
}
