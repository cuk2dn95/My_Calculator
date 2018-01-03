package com.example.ka.mvp_learning.view.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ka.mvp_learning.R;
import com.example.ka.mvp_learning.model.calculator.Calculation;
import com.example.ka.mvp_learning.presenter.calculator.IPresenter;
import com.example.ka.mvp_learning.presenter.calculator.PresenterImp;
import com.example.ka.mvp_learning.view.calculator.adapter.Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment  implements  IView{


    IPresenter presenter;
    float param1,param2;
    Adapter adapter;
    //@isFirst : the first time when user press number key
    boolean isFirst=true,isCalculating=false,isEqualPressed=false,isNegative=false;
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_operator)
    TextView textOperator;
    @BindView(R.id.text_result)
    TextView textResult;

    //function key
    @BindView(R.id.C)
    TextView btnC;
    @BindView(R.id.backSpace)
    View btnBack;


    // number key
    @BindView(R.id.div)
    TextView btnDiv;
    @BindView(R.id.mul)
    TextView btnMul;
    @BindView(R.id.add)
    TextView btnAdd;
    @BindView(R.id.sub)
    TextView btnSub;
    @BindView(R.id.zero)
    TextView btnZero;
    @BindView(R.id.one)
    TextView btnOne;
    @BindView(R.id.two)
    TextView btnTwo;
    @BindView(R.id.three)
    TextView btnThree;
    @BindView(R.id.four)
    TextView btnFour;
    @BindView(R.id.five)
    TextView btnFive;
    @BindView(R.id.six)
    TextView btnSix;
    @BindView(R.id.seven)
    TextView btnSeven;
    @BindView(R.id.eight)
    TextView btnEight;
    @BindView(R.id.nine)
    TextView btnNine;
    @BindView(R.id.equal)
    TextView btnEqual;
    @BindView(R.id.dot)
    TextView btnDot;
    @BindView(R.id.percent)
    TextView btnPercent;



    Calculation.Operator operator = null;

    @Override
    public void displayResult(float result) {
        textResult.setVisibility(View.VISIBLE);

        if((result %1) >0)
        {
            textResult.setText("="+String.valueOf(result));
        }
        else textResult.setText("="+String.valueOf(Math.round(result)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator,container,false);
        ButterKnife.bind(this,view);
        presenter = new PresenterImp(this);
        mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        setUpKey();
        return view;
    }


    void setUpKey(){
        btnZero.setOnClickListener(new NumberListener());
        btnOne.setOnClickListener(new NumberListener());
        btnTwo.setOnClickListener(new NumberListener());
        btnThree.setOnClickListener(new NumberListener());
        btnFour.setOnClickListener(new NumberListener());
        btnFive.setOnClickListener(new NumberListener());
        btnSix.setOnClickListener(new NumberListener());
        btnSeven.setOnClickListener(new NumberListener());
        btnEight.setOnClickListener(new NumberListener());
        btnNine.setOnClickListener(new NumberListener());


        btnMul.setOnClickListener( new OperatorListener());
        btnDiv.setOnClickListener(new OperatorListener());
        btnAdd.setOnClickListener(new OperatorListener());
        btnSub.setOnClickListener(new OperatorListener());

        btnC.setOnClickListener(new CommandListener());
        btnEqual.setOnClickListener(new EqualListener());
        btnBack.setOnClickListener(new backSpaceListener());
        btnDot.setOnClickListener(new DotListener());
        btnPercent.setOnClickListener( new PercentListener());

    }


    class NumberListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            TextView textView = (TextView) v;
            String number = textView.getText().toString();
            String text = textOperator.getText().toString();
            if(text != null) {
                StringBuilder stringBuilder = new StringBuilder(text);
                stringBuilder.append(number);
                textOperator.setText(stringBuilder.toString());



            }
            else {
                textOperator.setText(number);


            }
            if(isEqualPressed) {
                adapter.clear();
                textResult.setText("");
                textResult.setVisibility(View.GONE);
                isEqualPressed =false;
            }
            else if(isCalculating){

                String p2 = textOperator.getText().toString();
                p2 = p2.substring(1,p2.length());
                param2 = Float.valueOf(p2);
                presenter.calculateOperation(operator,param1,param2);

            }



        }
    }

    class OperatorListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TextView textView = (TextView) v;
            String text = textView.getText().toString();


            if(textOperator.getText().toString().length() == 0 ){

                if(text.equals("-"))
                    textOperator.setText("-");


                return ;
            }




            if(isFirst == false)
            {
                String p2 = textOperator.getText().toString();
                p2 = p2.substring(1,p2.length());
                param2 = Float.valueOf(p2);
                presenter.calculateOperation(operator,param1,param2);

            }


            if(text.equals("/"))
                operator = Calculation.Operator.DIV;
            if(text.equals("x"))
                operator = Calculation.Operator.MUL;
            if(text.equals("+"))
                operator = Calculation.Operator.ADD;
            if(text.equals("-"))
                operator = Calculation.Operator.SUB;


            adapter.addOperator(textOperator.getText().toString());
            if(isEqualPressed){
                String p1 = textResult.getText().toString();
                if(p1.length()>2) {
                    p1 = p1.substring(1, p1.length());
                    param1 = Float.valueOf(p1);
                    adapter.addOperator(p1);
                    isEqualPressed = false;
                    isFirst = false;
                }
            }
            else if(isFirst){
                param1 = Float.valueOf(textOperator.getText().toString());
                isFirst = !isFirst;
            }
            if(isCalculating){
                String p1 = textResult.getText().toString();
                if(p1.length() >2)
                {
                    p1 = p1.substring(1,p1.length());
                    param1 = Float.valueOf(p1);
                    adapter.addOperator(p1);
                }
            }

            textOperator.setText(text);
            mLayoutManager.scrollToPosition(adapter.getItemCount()-1);

            isCalculating = true;
        }
    }

    class CommandListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            adapter.clear();
            isFirst = true;
            isEqualPressed= false;
            textResult.setVisibility(View.GONE);
            textOperator.setText("");
            isCalculating = false;

        }
    }


    class EqualListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(isFirst){
//                    textResult.setText(textOperator.getText().toString());
//                    textResult.setVisibility(View.VISIBLE);
                textOperator.setText("");
                return ;
            }

            if(isFirst == false)
            {
                String p2 = textOperator.getText().toString();
                adapter.addOperator(p2);
                p2 = p2.substring(1,p2.length());
                try{
                    param2 = Float.valueOf(p2);
                    presenter.calculateOperation(operator,param1,param2);
                }catch (NumberFormatException e){
                    textOperator.setText("");
                    textResult.setText("");
                    adapter.clear();
                }

                isFirst = true;
                textOperator.setText("");
                mLayoutManager.scrollToPosition(adapter.getItemCount()-1);

            }
            isEqualPressed = true;
            isCalculating = false;
        }
    }


    class backSpaceListener implements ViewPager.OnClickListener{
        @Override
        public void onClick(View v) {
            String text = textOperator.getText().toString();
            if( (text.length()>1) && (isFirst == false) ) {
                text = text.substring(0, text.length() - 1);
                textOperator.setText(text);

                String p2 = textOperator.getText().toString();
                if(p2.length() >=2) {
                    p2 = p2.substring(1, p2.length());
                    param2 = Float.valueOf(p2);
                    presenter.calculateOperation(operator, param1, param2);
                }else {
                    textOperator.setText("");
                    textResult.setText("");
                    textResult.setVisibility(View.GONE);
                    textOperator.setText(adapter.getItem(adapter.getItemCount()-1));
                    adapter.clear();
                    isFirst = true;


                }
            }
            else if( (isFirst == true) && (text.length()>1) ){
                text = text.substring(0, text.length() - 1);
                textOperator.setText(text);

            } else {
                textOperator.setText("");
                textResult.setText("");
                textResult.setVisibility(View.GONE);
                isCalculating = false;
                adapter.clear();
            }




        }
    }


    class DotListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String text = textOperator.getText().toString();
            if(!text.contains(".")){
                StringBuilder stringBuilder = new StringBuilder(text);
                stringBuilder.append(".");
                textOperator.setText(stringBuilder.toString());
            }
        }
    }

    class PercentListener implements  View.OnClickListener{
        @Override
        public void onClick(View v) {
            String text = textOperator.getText().toString();
            if(text.length() >0){
                if(text.contains("+") || text.contains("-")
                        ||text.contains("/") || text.contains("*"))
                {
                    if(text.length() > 1){
                        String text1 = text.substring(1,text.length());
                        Float number = Float.valueOf(text1);
                        number = number/100;
                        textOperator.setText(text.charAt(0)+String.valueOf(number));
                    }

                }else {
                    Float number = Float.valueOf(text);
                    number = number/100;
                    textOperator.setText(String.valueOf(number));
                }
            }

        }
    }

}
