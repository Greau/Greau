package com.example.a14161.guochuang.model;

/**
 * Created by 14161 on 2017/9/4.
 */

public class Bean {

    private int iconId;


    private String title;


    private String content;

    private String comments;
    public Bean(int iconId,String title,String content,String conment){
        this.iconId=iconId;
        this.title=title;
        this.content=content;
        this.comments=conment;
    }
    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
