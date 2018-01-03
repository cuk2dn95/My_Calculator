package com.example.ka.mvp_learning.presenter.calculator;

import com.example.ka.mvp_learning.model.calculator.Calculation;
import com.example.ka.mvp_learning.view.calculator.IView;

/**
 * Created by KA on 12/16/2017.
 */

public class PresenterImp implements IPresenter
{

     IView iView;

    public PresenterImp(IView iView) {
        this.iView = iView;
    }

    @Override
    public void calculateOperation(Calculation.Operator operator, float param1, float param2) {

        float result = Calculation.operate(operator,param1,param2);
        iView.displayResult(result);
    }
}
