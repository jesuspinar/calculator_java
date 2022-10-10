package com.jesuspinar.calculator_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Display
        tvScreen = findViewById(R.id.tvScreen);
        //Get buttons
        Button btnCE = findViewById(R.id.btnCE);
        Button btnALT = findViewById(R.id.btnALT);
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
        View.OnClickListener nums = v -> {
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

        btn00.setOnClickListener(nums);
        btn01.setOnClickListener(nums);
        btn02.setOnClickListener(nums);
        btn03.setOnClickListener(nums);
        btn04.setOnClickListener(nums);
        btn05.setOnClickListener(nums);
        btn06.setOnClickListener(nums);
        btn07.setOnClickListener(nums);
        btn08.setOnClickListener(nums);
        btn09.setOnClickListener(nums);

        View.OnClickListener symbols = v -> {
            checkLogic();
            switch (v.getId()){
                case R.id.btnCE:   clear(); break;
                case R.id.btnALT:  alter(); break;
                case R.id.btnPERC: percent(); break;
                case R.id.btnDIV:  add("/"); break;
                case R.id.btnMULT: add("*"); break;
                case R.id.btnSUB:  add("-"); break;
                case R.id.btnSUM:  add("+"); break;
                case R.id.btnDOT:  add("."); break;
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
        //Valid eval
        //Set result
        //Toast errors
    }

    private void percent() {
    }

    private void alter() {
    }

    private void clear() {
    }

    private void checkLogic() {
    }

    private void add(String value){
        StringBuilder sb = new StringBuilder();
        String current = tvScreen.getText().toString();
        sb.append(current).append(value);
        tvScreen.setText(sb.toString());
    }

}