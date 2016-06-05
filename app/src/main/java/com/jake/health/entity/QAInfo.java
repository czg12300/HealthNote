
package com.jake.health.entity;

/**
 * 描述：求医问药的实体
 *
 * @author jakechen
 * @since 2016/4/27 18:09
 */
public class QAInfo {
    private String title;

    private String avatar;

    private String nickname;

    private String content;

    private long zanNum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNikeName() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getZanNum() {
        return zanNum;
    }

    public void setZanNum(long zanNum) {
        this.zanNum = zanNum;
    }

    public String getZanNumText() {
        String result = "" + zanNum;
        if (zanNum > 1000) {
            result = ((float) zanNum / 1000.0f) + "k";
        }
        return result;
    }
}
