package com.jy.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class RecentBean implements Serializable {

    private static final long serialVersionUID = 3045848714015097960L;
    @Id
    private Long id;
    private int news_id;
    private String url;
    private String thumbnail;
    private String title;
    @Generated(hash = 20979369)
    public RecentBean(Long id, int news_id, String url, String thumbnail,
            String title) {
        this.id = id;
        this.news_id = news_id;
        this.url = url;
        this.thumbnail = thumbnail;
        this.title = title;
    }
    @Generated(hash = 1697461393)
    public RecentBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNews_id() {
        return this.news_id;
    }
    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
