package com.jmp.matrizes;

import android.app.Activity;

class FormulaSolver {

    private String operator = "+";
    private String auxOperator = "";
    private int number0 = 0;
    private int number1 = 1;
    private int number2 = 1;


    void setOperator(String operator) {
        this.operator = operator;
    }

    void setAuxOperator(String auxOperator){
        this.auxOperator = auxOperator;
    }

    void setNumber0(int number0) {
        this.number0 = number0;
    }

    void setNumber1(int number1) {
        this.number1 = number1;
    }

    void setNumber2(int number2) {
        this.number2 = number2;
    }

    Integer solve(int a, int b, boolean isTranspose){
        int sum = 0;

        if(isTranspose){
            int aAux = a;
            a = b;
            b = aAux;
        }

        if(isPlusSignal(operator)){
            sum = number1*a + number2*b;
        }else if(isMinusSignal(operator)){
            sum = number1*a - number2*b;
        }else if(isTimesSignal(operator)){
            sum = number1*a * number2*b;
        }else if(isDivisionSignal(operator)){
            sum = number1*a / number2*b;
        }

        if(isPlusSignal(auxOperator)){
            sum += number0;
        }else if(isMinusSignal(auxOperator)){
            sum -= number0;
        }else if(isTimesSignal(auxOperator)){
            sum *= number0;
        }else if(isDivisionSignal(auxOperator)){
            sum /= number0;
        }

        return sum;
    }

    private boolean isPlusSignal(String operator){
        return operator.equals("+");
    }

    private boolean isMinusSignal(String operator){
        return operator.equals("-");
    }

    private boolean isTimesSignal(String operator){
        return operator.equals("*");
    }

    private boolean isDivisionSignal(String operator){
        return operator.equals("/");
    }
}
