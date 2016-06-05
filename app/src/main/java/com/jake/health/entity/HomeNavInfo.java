
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

    private int type;
    private String hint;
    public static final int TYPE_QA = 1;

    public static final int TYPE_ANALYSIS = 2;

    public static final int TYPE_MOMENTS = 3;

    public static final int TYPE_HOSPITAL = 4;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }


    public boolean isShowRedDot(){
        return showDot>0;
    }
}
