package com.jake.health.entity;

/**
 * 描述：个人中心的菜单
 * 作者：jake on 2016/4/21 22:17
 */
public class MineMenuInfo {
    private String title;
    private String icon;
    private int showDot;

    public int getShowDot() {
        return showDot;
    }

    public void setShowDot(int showDot) {
        this.showDot = showDot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
