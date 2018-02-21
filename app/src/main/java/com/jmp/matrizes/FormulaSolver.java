package com.jmp.matrizes;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

class FormulaSolver {

    private String formula = "";
    private String character = "";
    private String nextCharacter = "";
    private String operator = "";
    private String auxOperator = "";

    FormulaSolver(String formula){
        //"2i-3j"
        formula = formula.replace(" ","");
        formula = formula.toLowerCase();
        this.formula = formula;
    }

    Integer solve(int a, int b, boolean isTranspose, Context context){
        int sum = 0;
        boolean shouldAppend;
        int number0 = 0;
        int number1 = 1;
        int number2 = 1;
        for (int i = 0; i<= formula.length() - 1;i++){
            character = formula.substring(i,i+1);
            if(isNumber()) {
                try {
                    nextCharacter = String.valueOf(formula.charAt(i + 1));
                }catch (Exception e){
                }
                int result = Integer.valueOf(character);
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

    private boolean isNumber(String nextCharacter,Context context){
        Integer code = (int) nextCharacter.charAt(0);
        Toast.makeText(context, code.toString(), Toast.LENGTH_SHORT).show();
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
