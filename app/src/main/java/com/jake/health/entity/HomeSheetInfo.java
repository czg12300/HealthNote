
package com.jake.health.entity;

/**
 * 描述：首页fab的sheet实体
 *
 * @author jakechen
 * @since 2016/4/28 14:57
 */
public class HomeSheetInfo {
    private String icon;

    private String name;

    private int iconRes;
    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
