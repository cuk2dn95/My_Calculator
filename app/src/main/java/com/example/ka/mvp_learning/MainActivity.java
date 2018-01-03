package com.example.ka.mvp_learning;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ka.mvp_learning.R;
import com.example.ka.mvp_learning.view.calculator.CalculatorFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout,new CalculatorFragment());
        transaction.commit();
    }
}
