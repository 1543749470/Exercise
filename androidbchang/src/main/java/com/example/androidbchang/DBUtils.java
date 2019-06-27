package com.example.androidbchang;

import android.util.Log;

import com.example.androidbchang.dao.DaoSession;
import com.example.androidbchang.dao.RecentBeanDao;
import com.jy.beans.RecentBean;

import java.util.List;

public class DBUtils {
    private static final String TAG = "DBUtils";

    public static void insert(RecentBean recentBean){
        DaoSession daoSession = MyApp.getDaoSession();
        RecentBean recentBean1 = queryOne(recentBean.getNews_id());
        if (recentBean1==null){
            daoSession.insert(recentBean);
        }else{
            Log.e(TAG, "insert: 已存在");
        }
    }
    public static List<RecentBean> queryAll(){
        DaoSession daoSession = MyApp.getDaoSession();
        return daoSession.loadAll(RecentBean.class);
    }
    public static RecentBean queryOne(int title){
        DaoSession daoSession = MyApp.getDaoSession();
        RecentBean unique = daoSession.queryBuilder(RecentBean.class)
                .where(RecentBeanDao.Properties.News_id.eq(title))
                .build()
                .unique();
        return unique;
    }
}
