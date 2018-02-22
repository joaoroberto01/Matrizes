package com.jmp.matrizes;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

class FormulaSolver {

    private String formula = "";
    private String character = "";
    private String operator = "";
    private String auxOperator = "";

    FormulaSolver(String formula){
        //"2i-3j"
        formula = formula.replace(" ","");
        formula = formula.toLowerCase();
        this.formula = formula;
    }

    Integer solve(int a, int b, boolean isTranspose, Activity activity){
        int sum = 0;
        boolean shouldAppend;
        String nextCharacter = "";
        int result;
        int number0 = 0;
        int number1 = 1;
        int number2 = 1;
        for (int i = 0; i<= formula.length() - 1;i++){
            character = formula.substring(i,i+1);
            if(isNumber()) {
                try {
                    nextCharacter = String.valueOf(formula.charAt(i + 1));
                }catch (Exception e){
                    nextCharacter = "";
                }
                if(isNumber(nextCharacter)){
                    result = Integer.valueOf(character+ nextCharacter);
                }else {
                    result = Integer.valueOf(character);
                }
                View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
                Snackbar.make(view,result+"",Snackbar.LENGTH_LONG).show();
                if(isAuxNumber(i)){
                    number0 = result;
                }else if(nextCharacter.equals("i")){
                    number1 = result;
                }else if(nextCharacter.equals("j")){
                    number2 = result;
                }

            }else if(isOperator(character)){
                operator = character;
            }
        }

        if(isTranspose){
            int aAux = a;
            a = b;
            b = aAux;
        }

        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view,"Number 0: "+number0+ " | Number 1: "+number1+"| Number 2: "+number2,Snackbar.LENGTH_LONG).show();

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

    private boolean isNumber(){
        Integer code = (int) character.charAt(0);
        return code > 47 && code < 58;
    }

    private boolean isNumber(String nextCharacter){
        Integer code = (int) nextCharacter.charAt(0);
        return code > 47 && code < 58;
    }

    private boolean isAuxNumber(int i){
        if(i < formula.length() - 1){
            if (isOperator(String.valueOf(formula.charAt(i + 1)))) {
                auxOperator = String.valueOf(formula.charAt(i + 1));
                return true;
            } else
                return false;
        }else if(i > 0){
            if (isOperator(String.valueOf(formula.charAt(i - 1)))) {
                auxOperator = String.valueOf(formula.charAt(i - 1));
                return true;
            } else
                return false;
        }else
            return false;
    }

    private boolean isOperator(String character){
        return character.equals("+") || character.equals("-") || character.equals("*") || character.equals("/");
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
