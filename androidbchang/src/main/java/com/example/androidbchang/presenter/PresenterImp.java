package com.example.androidbchang.presenter;

import com.example.androidbchang.model.CallModel;
import com.example.androidbchang.model.ModelImp;
import com.example.androidbchang.view.IView;
import com.jy.beans.RecentBean;

import java.util.List;

public class PresenterImp implements IPresenter{
    IView iView;
    private ModelImp modelImp;

    public PresenterImp(IView iView) {
        this.iView = iView;
        modelImp=new ModelImp();
    }

    @Override
    public void getPresenter() {
        modelImp.getModel(new CallModel() {
            @Override
            public void updateSccess(List<RecentBean> recentBeans) {
                iView.updateSccess(recentBeans);
            }

            @Override
            public void updateFilade(String error) {
                iView.updateFilade(error);
            }
        });
    }
}
