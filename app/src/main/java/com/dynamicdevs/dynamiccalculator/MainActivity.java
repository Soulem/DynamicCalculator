package com.dynamicdevs.dynamiccalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    enum OppEnum{ DIVIDE, MULTIPLY, PLUS, MINUS, MOD, FACTORIAL, SQU, SQRT, NONE;
        public static OppEnum fromInteger(int x) {
            switch (x) {
                case 0: {
                    return DIVIDE;
                }
                case 1: {
                    return MULTIPLY;
                }
                case 2: {
                    return PLUS;
                }
                case 3:{
                    return MINUS;
                }
                case 4: {
                    return MOD;
                }
                default:{
                    return NONE;
                }
            }
        }
    }

    private TextView currAnswerTV;
    private TextView currEquationTV;
    private boolean isNewNumber;
    private double num;
    private OppEnum currOpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currAnswerTV = findViewById(R.id.currAnswer);
        num = 0.0f;
        isNewNumber = true;
        currOpp = OppEnum.NONE;

        // stuff for later use if i come back to this.
        currEquationTV = findViewById(R.id.currEquation);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CURR_ANSWER_KEY", currAnswerTV.getText().toString());
        outState.putBoolean("IS_NEW_NUMBER_KEY", isNewNumber);
        outState.putDouble("NUM_KEY", num);
        outState.putInt("CURR_OPP_KEY", currOpp.ordinal());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currAnswerTV.setText(savedInstanceState.getString("CURR_ANSWER_KEY"));
        isNewNumber = savedInstanceState.getBoolean("IS_NEW_NUMBER_KEY");
        num = savedInstanceState.getDouble("NUM_KEY");
        currOpp = OppEnum.fromInteger(savedInstanceState.getInt("CURR_OPP_KEY"));
    }

    public void onCalcClick(View view){
        switch (view.getId()){
            case R.id.one_button:
                handleNumberClick(1);
                break;
            case R.id.two_button:
                handleNumberClick(2);
                break;
            case R.id.three_button:
                handleNumberClick(3);
                break;
            case R.id.four_button:
                handleNumberClick(4);
                break;
            case R.id.five_button:
                handleNumberClick(5);
                break;
            case R.id.six_button:
                handleNumberClick(6);
                break;
            case R.id.seven_button:
                handleNumberClick(7);
                break;
            case R.id.eight_button:
                handleNumberClick(8);
                break;
            case R.id.nine_button:
                handleNumberClick(9);
                break;
            case R.id.zero_button:
                handleNumberClick(0);
                break;
            case R.id.period_button:
                handleDecimalClick();
                break;

            case R.id.negate_button:
                double tempNum = Double.parseDouble(currAnswerTV.getText().toString());
                if (0.0 < Math.abs(tempNum)) {
                    tempNum *= -1;
                }
                currAnswerTV.setText(String.valueOf(tempNum));
                break;

            // do the operations
            case R.id.divide_button:
                handleOperationClick(OppEnum.DIVIDE);
                break;

            case R.id.multiply_button:
                handleOperationClick(OppEnum.MULTIPLY);
                break;

            case R.id.plus_button:
                handleOperationClick(OppEnum.PLUS);
                break;

            case R.id.minus_button:
                handleOperationClick(OppEnum.MINUS);
                break;

            case R.id.equals_button:
                handleOperation();
                isNewNumber = true;
                currOpp = OppEnum.NONE;
                break;

            case R.id.ac_button:
                num = 0;
                currOpp = OppEnum.NONE;
                currAnswerTV.setText("0.0");
                break;

            case R.id.switch_button:
                break;

            case R.id.rad_deg_button:
                break;

            case R.id.mod_button:
                handleOperationClick(OppEnum.MOD);
                break;

            case R.id.sin_button:
                break;

            case R.id.cos_button:
                break;

            case R.id.tan_button:
                break;

            case R.id.inv_button:
                break;

            case R.id.log_button:
                break;

            case R.id.squ_root_button:
                handleScientificOperationClick(OppEnum.SQRT);
                break;

            case R.id.pi_button:
                break;

            case R.id.e_button:
                break;

            case R.id.squ_button:
                handleScientificOperationClick(OppEnum.SQU);
                break;

            case R.id.open_bracket_button:
                break;

            case R.id.close_bracket_button:
                break;

            case R.id.not_button:
                break;
        }
    }

    private void handleNumberClick(int i) {
        String currentNum = currAnswerTV.getText().toString();
        //String currentEquation = currEquationTV.getText().toString();

        if(Math.abs(Double.parseDouble(currentNum)) > 0.0f &&
                !isNewNumber){
            currAnswerTV.setText(currentNum+i);
        }
        else {
            currAnswerTV.setText(i + "");
            isNewNumber = false;
        }
    }

    private void handleOperationClick(OppEnum opp){
        if (OppEnum.NONE == currOpp) {
            num = Double.parseDouble(currAnswerTV.getText().toString());
        }
        else{
            handleOperation();
        }
        currOpp = opp;
        isNewNumber = true;
    }

    private void handleScientificOperationClick(OppEnum opp){
        switch (opp){
            case SQRT:{
                double temp = Double.parseDouble(currAnswerTV.getText().toString());
                temp = Math.sqrt(temp);
                currAnswerTV.setText(String.valueOf(temp));
                break;
            }
            case SQU:{
                double temp = Double.parseDouble(currAnswerTV.getText().toString());
                temp *= temp;
                currAnswerTV.setText(String.valueOf(temp));
                break;
            }
            default:{
                break;
            }
        }
    }

    private void handleOperation(){
        double tempNum = Double.parseDouble(currAnswerTV.getText().toString());

        switch (currOpp){
            case DIVIDE:{
                num /= tempNum;
                break;
            }
            case MULTIPLY:{
                num *= tempNum;
                break;
            }
            case PLUS:{
                num += tempNum;
                break;
            }
            case MINUS:{
                num -= tempNum;
                break;
            }
            case MOD:{
                num %= tempNum;
                break;
            }
            default:{
                break;
            }
        }
        currAnswerTV.setText(String.valueOf(num));
    }

    private void handleDecimalClick(){
        String currentNum = currAnswerTV.getText().toString();
        if (!currentNum.contains("."))
            currAnswerTV.setText(currentNum + ".");
    }
}