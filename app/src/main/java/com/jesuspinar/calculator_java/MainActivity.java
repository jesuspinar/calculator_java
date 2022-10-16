package com.jesuspinar.calculator_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

public class MainActivity extends AppCompatActivity {
    private TextView tvScreen;
    private StringBuilder sb ;
    private final ExpressionConfiguration configuration = ExpressionConfiguration.builder()
            .decimalPlacesRounding(2).build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = new StringBuilder();
        //Get Display
        tvScreen = findViewById(R.id.tvScreen);
        //Get buttons
        Button btnCE = findViewById(R.id.btnCE);
        Button btnALT = findViewById(R.id.btnDel);
        Button btnPERC = findViewById(R.id.btnPERC);
        Button btnDIV = findViewById(R.id.btnDIV);

        Button btn07 = findViewById(R.id.btn07);
        Button btn08 = findViewById(R.id.btn08);
        Button btn09 = findViewById(R.id.btn09);
        Button btnMULT = findViewById(R.id.btnMULT);

        Button btn04 = findViewById(R.id.btn04);
        Button btn05 = findViewById(R.id.btn05);
        Button btn06 = findViewById(R.id.btn06);
        Button btnSUB = findViewById(R.id.btnSUB);

        Button btn01 = findViewById(R.id.btn01);
        Button btn02 = findViewById(R.id.btn02);
        Button btn03 = findViewById(R.id.btn03);
        Button btnSUM = findViewById(R.id.btnSUM);

        Button btn00 = findViewById(R.id.btn00);
        Button btnDOT = findViewById(R.id.btnDOT);
        Button btnEQ = findViewById(R.id.btnEQ);

        //Add listeners
        View.OnClickListener num = v -> {
            switch (v.getId()){
                case R.id.btn00: add("0"); break;
                case R.id.btn01: add("1"); break;
                case R.id.btn02: add("2"); break;
                case R.id.btn03: add("3"); break;
                case R.id.btn04: add("4"); break;
                case R.id.btn05: add("5"); break;
                case R.id.btn06: add("6"); break;
                case R.id.btn07: add("7"); break;
                case R.id.btn08: add("8"); break;
                case R.id.btn09: add("9"); break;
            }
        };

        btn00.setOnClickListener(num);
        btn01.setOnClickListener(num);
        btn02.setOnClickListener(num);
        btn03.setOnClickListener(num);
        btn04.setOnClickListener(num);
        btn05.setOnClickListener(num);
        btn06.setOnClickListener(num);
        btn07.setOnClickListener(num);
        btn08.setOnClickListener(num);
        btn09.setOnClickListener(num);

        View.OnClickListener symbols = v -> {
            boolean isValid = checkLogic();
            if (isValid){
                switch (v.getId()){
                    case R.id.btnCE:   clear(); break;
                    case R.id.btnDel:  del(); break;
                    case R.id.btnPERC: percent(); break;
                    case R.id.btnEQ:   eval(); break;
                    case R.id.btnDIV:  add("/");break;
                    case R.id.btnMULT: add("*");break;
                    case R.id.btnSUB:  add("-");break;
                    case R.id.btnSUM:  add("+");break;
                    case R.id.btnDOT:  if (!hasDot()){add(".");}break;
                }
            }
        };
        btnCE.setOnClickListener(symbols);
        btnALT.setOnClickListener(symbols);
        btnPERC.setOnClickListener(symbols);
        btnDIV.setOnClickListener(symbols);
        btnMULT.setOnClickListener(symbols);
        btnSUB.setOnClickListener(symbols);
        btnSUM.setOnClickListener(symbols);
        btnDOT.setOnClickListener(symbols);
        btnEQ.setOnClickListener(symbols);
    }

    private void eval() {
        Expression expression = new Expression(sb.toString(),configuration);
        try {
            EvaluationValue result = expression.evaluate();
            clear();
            add(result.getStringValue());
        } catch (EvaluationException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void percent() {
        add("/100");
        eval();
    }

    private void del() {
        String current = tvScreen.getText().toString();
        if (current.length() > 0) {
            sb = sb.deleteCharAt(current.length()-1);
            tvScreen.setText(sb.toString());
        }
    }

    private void clear() {
        tvScreen.setText("");
        sb.setLength(0);
    }

    private boolean checkLogic() {
        //can't add symbols
        if(sb.length() <= 0){
           return false;
        }
        //can change last symbol for a new select
        else{
            char c = sb.charAt(sb.length()-1);
            switch (c){case '/': case '*': case'-': case'+': case'.': del();}
            return true;
        }
    }

    /*Checks if the String builder has a dot before symbol */
    private boolean hasDot(){
        boolean hasDot = false;
        for (int i = 1; i < sb.length(); i++) {
            if (!hasDot && sb.charAt(i)  == '.'){
                hasDot = true;//start track
            }
            else if (hasDot){
                switch (sb.charAt(i)){
                    case '/': case '*': case'-': case'+': hasDot = false;
                }
            }
        }
        return hasDot;
    }

    private void add(String value){
        sb.append(value);
        tvScreen.setText(sb.toString());
    }

}