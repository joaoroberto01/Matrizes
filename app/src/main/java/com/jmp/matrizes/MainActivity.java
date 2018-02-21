package com.jmp.matrizes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private Integer row, column;
    private EditText rowsEditText, columnsEditText, formulaEditText;
    private LinearLayout container;
    private Button makeButton;
    private String formula;
    private boolean isTransposeMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startObjects();
    }

    private void startObjects() {
        rowsEditText = findViewById(R.id.rowsEditText);

        columnsEditText = findViewById(R.id.columnsEditText);

        formulaEditText = findViewById(R.id.formulaEditText);

        CheckBox transposeCheckBox = findViewById(R.id.transposeCheckBox);
        transposeCheckBox.setOnCheckedChangeListener(this);
        isTransposeMatrix = transposeCheckBox.isChecked();

        makeButton = findViewById(R.id.makeButton);
        makeButton.setOnClickListener(this);

        container = findViewById(R.id.container);

        constructMatrix();
    }

    private void setFormula(){
        formula = formulaEditText.getText().toString();
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
        setFormula();

        FormulaSolver solver = new FormulaSolver(formula);
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
                sum = solver.solve(a,b,isTransposeMatrix,this);
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

    @Override
    public void onClick(View v) {
        constructMatrix();
    }
}
