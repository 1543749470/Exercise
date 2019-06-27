package com.example.synthesize;

import android.util.Log;

import com.example.synthesize.dao.DaoSession;
import com.example.synthesize.dao.ResultsBeanDao;
import com.jy.beans.ResultsBean;

import java.util.List;

public class DBUtils {
    private static final String TAG = "DBUtils";
    public static void insert(ResultsBean resultsBean){
        DaoSession daoSession = MyApp.getDaoSession();
        ResultsBean resultsBean1 = queryOne(resultsBean.get_id());
        if (resultsBean1==null){
            daoSession.insert(resultsBean);
        }else{
            Log.e(TAG, "insert: 已存在" );
        }
    }

    public static void delete(ResultsBean resultsBean){
        DaoSession daoSession = MyApp.getDaoSession();
        ResultsBean resultsBean1 = queryOne(resultsBean.get_id());
        if (resultsBean1!=null){
            daoSession.delete(resultsBean);
        }else{
            Log.e(TAG, "delete: 不存在" );
        }
    }

    public static List<ResultsBean> queryAll(){
        DaoSession daoSession = MyApp.getDaoSession();
        return daoSession.loadAll(ResultsBean.class);
    }

    public static ResultsBean queryOne(String _id){
        DaoSession daoSession = MyApp.getDaoSession();
        ResultsBean unique = daoSession.queryBuilder(ResultsBean.class)
                .where(ResultsBeanDao.Properties._id.eq(_id))
                .build()
                .unique();
        return unique;
    }
 }
