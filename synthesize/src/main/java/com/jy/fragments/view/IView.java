package com.jy.fragments.view;

import com.jy.beans.ResultsBean;

import java.util.List;

public interface IView {
    void updateSccess(List<ResultsBean> resultsBeans);
    void updateFilade(String error);

}
