package com.jmp.matrizes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, TextWatcher {

    private Integer row, column;
    private EditText rowsEditText, columnsEditText, formulaEditText;
    private LinearLayout container;
    private FormulaSolver solver;
    private boolean isTransposeMatrix;

    private boolean operatorWasPressed = false;
    private String number = "";
    private int number0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startObjects();
    }

    private void startObjects() {
        rowsEditText = findViewById(R.id.rowsEditText);
        rowsEditText.addTextChangedListener(this);

        columnsEditText = findViewById(R.id.columnsEditText);
        columnsEditText.addTextChangedListener(this);

        formulaEditText = findViewById(R.id.formulaEditText);
        formulaEditText.setEnabled(false);

        CheckBox transposeCheckBox = findViewById(R.id.transposeCheckBox);
        transposeCheckBox.setOnCheckedChangeListener(this);
        isTransposeMatrix = transposeCheckBox.isChecked();

        Button makeButton = findViewById(R.id.makeButton);
        makeButton.setOnClickListener(this);

        setButtons();

        container = findViewById(R.id.container);
        solver = new FormulaSolver();

        constructMatrix();
    }


    private void setI() {
        if(!rowsEditText.getText().toString().isEmpty()){
            row = Integer.valueOf(rowsEditText.getText().toString());
        }else
            row = 0;
    }

    private void setJ() {
        if(!columnsEditText.getText().toString().isEmpty()){
            column = Integer.valueOf(columnsEditText.getText().toString());
        }else
            column = 0;
    }

    private void constructMatrix(){
        container.removeAllViews();
        setI();
        setJ();

        int sum;
        if(isTransposeMatrix){
            int rowAux = row;
            row = column;
            column = rowAux;
        }

        for (int a = 1; a <= row; a++){
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int b = 1; b <= column; b++){
                sum = solver.solve(a,b,isTransposeMatrix);
                String text = String.valueOf(sum)+" ";
                if(sum >= 0) {
                    if (text.length() < 3) {
                        text = "   " + text;
                    }else {
                        text = " " + text;
                    }
                }else {
                    if (text.length() < 4) {
                        text = "   " + text;
                    }else {
                        text = " " + text;
                    }
                }
                TextView textView = new TextView(this);
                textView.setText(text);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(18);
                rowLayout.addView(textView);
            }
            container.addView(rowLayout);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isTransposeMatrix = isChecked;

        constructMatrix();
    }

    private boolean isFormulaEmpty(){
        return formulaEditText.getText().toString().isEmpty();
    }

    private void setFormulaText(String text){
        String oldText = formulaEditText.getText().toString();
        String newText = oldText + text;
        formulaEditText.setText(newText);
    }

    private void iAction(){
        int number1;
        if (!number.isEmpty())
            number1 = Integer.valueOf(number);
        else
            number1 = 1;

        setFormulaText("i");
        solver.setNumber1(number1);
        clearStates();
    }

    private void jAction() {
        int number2;
        if (!number.isEmpty())
            number2 = Integer.valueOf(number);
        else
            number2 = 1;

        setFormulaText("j");
        solver.setNumber2(number2);
        clearStates();
    }

    private void deleteAction() {
        formulaEditText.setText("");
        clearStates();
        solver.setAuxOperator("");
        solver.setOperator("");
    }

    private void plusAction(){
        if(!operatorWasPressed) {
            if (!isFormulaEmpty())
                setFormulaText("+");
            if (!number.isEmpty()) {
                number0 = Integer.valueOf(number);
                solver.setNumber0(number0);
                solver.setAuxOperator("+");
                clearStates();
            } else
                solver.setOperator("+");
            operatorWasPressed = true;
        }
    }

    private void minusAction(){
        if(!operatorWasPressed) {
            if (!number.isEmpty()) {
                number0 = Integer.valueOf(number);
                solver.setNumber0(number0);
                solver.setAuxOperator("-");
                clearStates();
            } else
                solver.setOperator("-");
            setFormulaText("-");
            operatorWasPressed = true;
        }
    }

    private void timesAction(){
        if(operatorWasPressed) {
            if (!isFormulaEmpty())
                setFormulaText("*");
            if (!number.isEmpty()) {
                number0 = Integer.valueOf(number);
                solver.setNumber0(number0);
                solver.setAuxOperator("*");
                clearStates();
            } else
                solver.setOperator("*");
            operatorWasPressed = true;
        }
    }

    private void divisionAction(){
        if(operatorWasPressed) {
            if (!isFormulaEmpty())
                setFormulaText("/");
            if (!number.isEmpty()) {
                number0 = Integer.valueOf(number);
                solver.setNumber0(number0);
                solver.setAuxOperator("/");
                clearStates();
            } else
                solver.setOperator("/");
            operatorWasPressed = true;
        }
    }

    private void clearStates(){
        number = "";
        operatorWasPressed = false;
    }

    @Override
    public void onClick(View v) {
        getWindow().getDecorView().findViewById(android.R.id.content).clearFocus();
        switch (v.getId()){
            case R.id.button0:
                setFormulaText("0");
                number = number.concat("0");
                break;
            case R.id.button1:
                setFormulaText("1");
                number = number.concat("1");
                break;
            case R.id.button2:
                setFormulaText("2");
                number = number.concat("2");
                break;
            case R.id.button3:
                setFormulaText("3");
                number = number.concat("3");
                break;
            case R.id.button4:
                setFormulaText("4");
                number = number.concat("4");
                break;
            case R.id.button5:
                setFormulaText("5");
                number = number.concat("5");
                break;
            case R.id.button6:
                setFormulaText("6");
                number = number.concat("6");
                break;
            case R.id.button7:
                setFormulaText("7");
                number = number.concat("7");
                break;
            case R.id.button8:
                setFormulaText("8");
                number = number.concat("8");
                break;
            case R.id.button9:
                setFormulaText("9");
                number = number.concat("9");
                break;
            case R.id.buttonDel:
                deleteAction();
                break;
            case R.id.buttonI:
                iAction();
                break;
            case R.id.buttonJ:
                jAction();
                break;
            case R.id.buttonPlus:
                plusAction();
                break;
            case R.id.buttonMinus:
                minusAction();
                break;
            case R.id.buttonTimes:
                timesAction();
                break;
            case R.id.buttonDivision:
                divisionAction();
                break;
            case R.id.makeButton:
                constructMatrix();
                break;
        }


    }

    private void setButtons(){
        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(this);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);

        Button buttonI = findViewById(R.id.buttonI);
        buttonI.setOnClickListener(this);

        Button buttonJ = findViewById(R.id.buttonJ);
        buttonJ.setOnClickListener(this);

        ImageButton buttonDel = findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(this);

        Button buttonPlus = findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(this);

        Button buttonMinus = findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(this);

        Button buttonTimes = findViewById(R.id.buttonTimes);
        buttonTimes.setOnClickListener(this);

        Button buttonDivision = findViewById(R.id.buttonDivision);
        buttonDivision.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        constructMatrix();
    }
}
