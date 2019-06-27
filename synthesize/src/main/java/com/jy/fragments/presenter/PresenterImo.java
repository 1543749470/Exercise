package com.jy.fragments.presenter;

import com.jy.beans.ResultsBean;
import com.jy.fragments.model.CallModel;
import com.jy.fragments.model.ModelImp;
import com.jy.fragments.view.IView;

import java.util.List;

public class PresenterImo implements IPresenter {
    IView iView;
    private ModelImp modelImp;

    public PresenterImo(IView iView) {
        this.iView = iView;
        modelImp=new ModelImp();
    }

    @Override
    public void getPrensenter() {
        modelImp.getModel(new CallModel() {
            @Override
            public void updateSccess(List<ResultsBean> resultsBeans) {
                iView.updateSccess(resultsBeans);
            }

            @Override
            public void updateFilade(String error) {
                iView.updateFilade(error);
            }
        });
    }
}
