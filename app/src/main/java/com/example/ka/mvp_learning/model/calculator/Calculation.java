package com.example.ka.mvp_learning.model.calculator;

/**
 * Created by KA on 12/16/2017.
 */

public class Calculation {
    public  enum Operator{
        ADD,SUB,MUL,DIV
    }

    public static float operate(Operator operator,float param1,float param2){

        switch(operator){
            case ADD:
               return param1 + param2;
            case SUB:
                return param1 - param2;
            case MUL:
                return param1*param2;
            case DIV:
                return param1 / param2;
        }

        return 0;

    }
}
