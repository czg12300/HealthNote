package com.jake.health.entity;

/**
 * 描述：首页入口
 *
 * @author jakechen
 * @since 2016/4/20 18:35
 */
public class HomeNavInfo {
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
