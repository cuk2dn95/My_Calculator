package com.example.ka.mvp_learning.presenter.calculator;

import com.example.ka.mvp_learning.model.calculator.Calculation;

/**
 * Created by KA on 12/16/2017.
 */

public interface IPresenter {
    void calculateOperation(Calculation.Operator operator,float param1,float param2);
}
