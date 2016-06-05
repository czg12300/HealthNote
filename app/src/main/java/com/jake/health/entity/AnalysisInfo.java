package com.jake.health.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：病例分析
 *
 * @author jakechen
 * @since 2016/4/26 16:18
 */
public class AnalysisInfo {
    private long id;
    private String title;

    private String avatar;

    private String nickname;

    private String articleUrl;
    private String summary;
    private long zanNum;
    private long date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getZanNum() {
        return zanNum;
    }

    public void setZanNum(long zanNum) {
        this.zanNum = zanNum;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return "发表于 "+dateFormat.format(new Date(date));
    }

    public String getZanNumText() {
        String result = "" + zanNum;
        if (zanNum > 1000) {
            result = ((float) zanNum / 1000.0f) + "k";
        }
        return result;
    }
}
