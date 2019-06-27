package com.example.androidbchang.model;

import com.jy.beans.RecentBean;

import java.util.ArrayList;
import java.util.List;

public interface CallModel {
    void updateSccess(List<RecentBean> recentBeans);
    void updateFilade(String error);

}
