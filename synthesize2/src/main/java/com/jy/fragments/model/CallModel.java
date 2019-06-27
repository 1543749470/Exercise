package com.jy.fragments.model;

import com.jy.beans.ResultsBean;

import java.util.List;

public interface CallModel {
    void updateSccess(List<ResultsBean> resultsBeans);
    void updateFilade(String error);

}
