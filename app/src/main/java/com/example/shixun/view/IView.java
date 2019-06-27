package com.example.shixun.view;

import com.jy.beans.RecentBean;

import java.util.List;

public interface IView {
    void updateSccess(List<RecentBean> recentBeans);
    void updateFilade(String error);

}
