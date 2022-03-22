package com.example.kalkulator2;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText display;
    boolean and = false;
    boolean or = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display= findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");

            }
        });

    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view) {
        updateText("0");

    }

    public void oneBTN(View view) {
        updateText("1");


    }

    public void twoBTN(View view) {
        updateText("2");

    }

    public void threeBTN(View view) {
        updateText("3");

    }

    public void fourBTN(View view) {
        updateText("4");

    }

    public void fiveBTN(View view) {
        updateText("5");

    }

    public void sixBTN(View view) {
        updateText("6");

    }

    public void sevenBTN(View view) {
        updateText("7");

    }

    public void eightBTN(View view) {
        updateText("8");

    }

    public void nineBTN(View view) {
        updateText("9");

    }

    public void clearBTN(View view) {
        display.setText("");

    }

    public void oklepajBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")) {
                openPar +=1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")) {
                closedPar +=1;
            }

        }

        if (openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }

        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void potencaBTN(View view) {
        updateText("^");

    }

    public void divideBTN(View view) {
        updateText("/");

    }

    public void multiplyBTN(View view) {
        updateText("*");

    }

    public void minusBTN(View view) {
        updateText("-");

    }

    public void plusBTN(View view) {
        updateText("+");

    }

    public void equalBTN(View view) {

        if (and){
            String[] a = display.getText().toString().split(" IN ");
            if (a[0].equals("1") && a[1].equals("1")){
                //cout1
                display.setText("1");
            }else {
                //cout 0
                display.setText("0");
            }
            and = false;
            return;
        }

        if (or){
            String[] a = display.getText().toString().split(" ALI ");
            if (a[0].equals("0") && a[1].equals("0")){
                //cout 0
                display.setText("0");
            }else {
                //cout 1
                display.setText("1");
            }
            or = false;
            return;
        }




        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());


    }

    public void dotBTN(View view) {
        updateText(".");

    }

    public void PlusMinusBTN(View view) {
        updateText("-");

    }

    public void backspaceBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen !=0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }


    public void dvoBTN(View view){
        display.setText(Integer.toBinaryString(Integer.parseInt(display.getText().toString())));
    }

    public void sestBTN(View view){
        display.setText(Integer.toHexString(Integer.parseInt(display.getText().toString())));
    }

    public void osemBTN(View view){
        display.setText(Integer.toOctalString(Integer.parseInt(display.getText().toString())));
    }


    public void inBTN(View view){
        and = true;
        display.setText(" IN " + display.getText().toString());
    }

    public void aliBTN(View view){
        or = true;
        display.setText(" ALI " + display.getText().toString());
    }








}