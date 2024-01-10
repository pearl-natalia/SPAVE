package com.example.spave;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    Button back_button, zero, one, two, three, four, five, six, seven, eight, nine, clear,
            openBracket, closeBracket, divide, multiply, add, minus, equal, decimal, delete;
    TextView calculations, results;
    String stringCalculate = "";
    Double result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    // calling methods on start
    @Override
    public void onStart() {
        super.onStart();

        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        backButton();
        clearOnClick();
        zeroOnClick();
        oneOnClick();
        twoOnClick();
        threeOnClick();
        fourOnClick();
        fiveOnClick();
        sixOnClick();
        sevenOnClick();
        eightOnClick();
        nineOnClick();
        addOnClick();
        subtractOnClick();
        multiplyOnClick();
        divideOnClick();
        decimalOnClick();
        openBracketOnClick();
        closeBracketOnClick();
        equalOnClick();
        deleteOnClick();
        copyResults();

    }

    //METHOD: switching fragments when button is clicked
    private void backButton(){
        back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new CalculateFragment());
                fragmentTransaction.commit();
            }
        });
    }


    public void setView(String givenValue){
        calculations = getActivity().findViewById(R.id.calculations);
        stringCalculate += givenValue;

        calculations.setText(stringCalculate);
    }

    public void clearOnClick(){
        clear = getActivity().findViewById(R.id.clear);
        calculations = getActivity().findViewById(R.id.calculations);
        results = getActivity().findViewById(R.id.results);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.setText("");
                stringCalculate = "";
                results.setText("");
            }
        });
    }

    public void zeroOnClick(){
        zero = getActivity().findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("0"); }
        });
    }

    public void oneOnClick(){
        one = getActivity().findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("1"); }
        });
    }
    public void twoOnClick(){
        two = getActivity().findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("2"); }
        });
    }
    public void threeOnClick(){
        three = getActivity().findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("3"); }
        });
    }
    public void fourOnClick(){
        four = getActivity().findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("4"); }
        });
    }

    public void fiveOnClick(){
        five = getActivity().findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("5"); }
        });
    }

    public void sixOnClick(){
        six = getActivity().findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("6"); }
        });
    }

    public void sevenOnClick(){
        seven = getActivity().findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("7"); }
        });
    }

    public void eightOnClick(){
        eight = getActivity().findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("8"); }
        });
    }

    public void nineOnClick(){
        nine = getActivity().findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("9"); }
        });
    }

    public void divideOnClick(){
        divide = getActivity().findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("/"); }
        });
    }

    public void multiplyOnClick(){
        multiply = getActivity().findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("*"); }
        });
    }

    public void addOnClick(){
        add = getActivity().findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("+"); }
        });
    }

    public void subtractOnClick(){
        minus = getActivity().findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("-"); }
        });
    }

    public void openBracketOnClick() {
        openBracket = getActivity().findViewById(R.id.openBracket);
        openBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("("); }
        });
    }


    public void closeBracketOnClick(){
        closeBracket = getActivity().findViewById(R.id.closeBracket);
        closeBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(")"); }
        });
    }

    public void decimalOnClick(){
        decimal = getActivity().findViewById(R.id.decimal);
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView("."); }
        });
    }

    public void deleteOnClick(){
        delete = getActivity().findViewById(R.id.delete);
        calculations = getActivity().findViewById(R.id.calculations);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!calculations.getText().toString().matches("")){
                    stringCalculate = stringCalculate.substring(0, stringCalculate.length()-1);
                    calculations.setText(stringCalculate);
                }
            }
        });
    }

    public void equalOnClick(){
        equal = getActivity().findViewById(R.id.equal);
        results = getActivity().findViewById(R.id.results);

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = null;
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

                // catching errors if calculations has 2 or more division signs in a row
                int index = stringCalculate.indexOf("/");
                boolean incorrect = false;

                if (index != -1){
                    while (index >= 0) {
                        if (index!= 0 && index != stringCalculate.length()-1){
                            if (String.valueOf(stringCalculate.charAt(index-1)).matches("/")
                                    | String.valueOf(stringCalculate.charAt(index+1)).matches("/")){
                                incorrect = true; } }
                        else if (index == 0 && stringCalculate.length() != 1){
                            if (String.valueOf(stringCalculate.charAt(index+1)).matches("/")){
                                incorrect = true; } }
                        else if (index == stringCalculate.length()-1 && stringCalculate.length() != 1){
                            if (String.valueOf(stringCalculate.charAt(index-1)).matches("/")){
                                incorrect = true; } }
                        else if (stringCalculate.length() == 1){
                            incorrect = true; }
                        index = stringCalculate.indexOf("/", index + 1);
                    }
                }

                try {
                    if (incorrect == false) {
                        result = (double) engine.eval(stringCalculate);
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    }
                }

                catch (ScriptException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }

                if (stringCalculate.indexOf("/0")!=-1|stringCalculate.indexOf("-/0")!=-1
                        |stringCalculate.indexOf("+/0")!=-1|stringCalculate.indexOf("/.0")!=-1|
                        stringCalculate.indexOf("/-.0")!=-1|stringCalculate.indexOf("/+.0")!=-1){
                    results.setText("Undefined"); }

                else if (result != null){
                    if (result % 1 == 0){
                        results.setText(String.valueOf(result.intValue()));
                    }
                    else {
                        results.setText(String.valueOf(result.doubleValue()));
                    }
                }
            }
        });

    }

    public void copyResults(){
        calculations = getActivity().findViewById(R.id.calculations);
        results = getActivity().findViewById(R.id.results);

        calculations.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", calculations.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity().getApplicationContext(), "Calculation copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        results.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", results.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity().getApplicationContext(), "Answer copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }


}