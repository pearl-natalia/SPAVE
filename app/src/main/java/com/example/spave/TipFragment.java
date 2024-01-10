package com.example.spave;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class TipFragment extends Fragment {

    private Button back_button, plus_button, minus_button, calculate_button;
    private TextView splitAmt, total_per_person,
            bill_per_person, tip_per_person, tip_percent;
    private EditText total_bill;
    private SeekBar tipSeekBar;
    BottomNavigationView bottomNavigationView;

    // making scrollview with 0-100% options
    static List<String> data = new ArrayList<String>();
    static int index = 0;
    static String bill;
    static String tip;

    //METHOD: Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tip, container, false);
    }

    //METHOD: methods called when fragment starts
    @Override
    public void onStart() {
        super.onStart();

        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        // calling methods
        backButton();
        seekbar();
        split();
        displayTotalPerPerson();
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

    //METHOD: to change seekbar
    public void seekbar() {
        tipSeekBar = getActivity().findViewById(R.id.tipSeekBar);
        tip_percent = getActivity().findViewById(R.id.tip_percent);

        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tip_percent.setText(String.valueOf(progress) + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    //METHOD: Split amount
    private void split(){
        splitAmt = getActivity().findViewById(R.id.splitAmt);
        plus_button= getActivity().findViewById(R.id.plus_button);
        minus_button = getActivity().findViewById(R.id.minus_button);

        // list of 20
        ArrayList<Integer> people = new ArrayList<Integer>(20);
        for (int num = 1; num <= 20; num++){
            people.add(num); }

        // changing split value when button is clicked
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index += 1;
                if (index > 19){
                    index = 19;
                }
                else if (index < 0){
                    index = 0;
                }
                splitAmt.setText(String.valueOf(people.get(index)));
            }});

        minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index -= 1;
                if (index > 19){
                    index = 19;
                }
                else if (index < 0){
                    index = 0;
                }
                splitAmt.setText(String.valueOf(people.get(index)));
            }});
    }

    // METHOD: calculate total cost per person (tip + bill)
    private String totalPerPerson(){

        // getting total bill and converting to decimal
        total_bill = getActivity().findViewById(R.id.total_bill);
        String total_billValue = total_bill.getText().toString();
        float total;

        // checking if no bill value is entered
        if (total_billValue.matches("")){
            total = 0; }
        else {
            total = Float.parseFloat(total_billValue); }

        // getting tip percentage
        float percent = Float.parseFloat(tip_percent.getText().toString()
                .replace("%", ""));
        percent = (percent + 100)/100;

        // getting amt of people
        Float people = Float.parseFloat(splitAmt.getText().toString());

        // getting total bill per person
        DecimalFormat decimalCost = new DecimalFormat("0.00");
        String costPerPerson = String.valueOf(decimalCost.format(total*percent/people));

        // getting tip and bill amt per person
        bill = String.valueOf(decimalCost.format(total/people));
        tip = String.valueOf(decimalCost.format((percent-1)*total/people));

        return costPerPerson;
    }

    // METHOD: display total per person when calculate button is clicked
    private void displayTotalPerPerson(){
        calculate_button = getActivity().findViewById(R.id.calculate_button);
        total_per_person = getActivity().findViewById(R.id.total_per_person);
        bill_per_person = getActivity().findViewById(R.id.bill_per_person);
        tip_per_person = getActivity().findViewById(R.id.tip_per_person);

        // displaying calculations when button is clicked
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_per_person.setText("$ " + totalPerPerson());
                bill_per_person.setText("$ " + bill);
                tip_per_person.setText("$ " + tip);
            }});
    }

}