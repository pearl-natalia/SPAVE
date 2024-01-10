package com.example.spave;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class DiscountFragment extends Fragment {

    private Button back_button, calculate_button;
    private SeekBar discountSeekBar, taxSeekBar;
    private TextView discount_percent, tax_percent, total_bill,
            minus, savings, tax, total, percent_off, after_savings;
    BottomNavigationView bottomNavigationView;

    static float totalAmt, discountPercent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discount, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        // calling methods
        backButton();
        seekbar();
        displayCalculations();
    }

    //METHOD: switching fragments when button is clicked
    private void backButton() {
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
    public void seekbar(){
        discountSeekBar = getActivity().findViewById(R.id.discountSeekBar);
        taxSeekBar = getActivity().findViewById(R.id.taxSeekBar);
        discount_percent = getActivity().findViewById(R.id.discount_percent);
        tax_percent = getActivity().findViewById(R.id.tax_percent);

        discountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b){
                discount_percent.setText(String.valueOf(progress) + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        taxSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b){
                tax_percent.setText(String.valueOf(progress) + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    // METHOD: to calculate savings
    public String calculateSavings(){
        total_bill = getActivity().findViewById(R.id.total_bill);
        String originalBill = total_bill.getText().toString();

        // checking if original bill value is 0 dollars
        if (originalBill.matches("")){
            totalAmt = 0; }
        else {
            totalAmt = Float.parseFloat(originalBill); }

        // getting discount percentage
        discountPercent = Float.parseFloat((discount_percent.getText()
                .toString()).replace("%", ""));
        discountPercent = discountPercent/100;

        // getting savings value
        DecimalFormat decimalCost = new DecimalFormat("0.00");
        String savingsAmt = String.valueOf(decimalCost.format(totalAmt*discountPercent));

        return savingsAmt;
    }

    // METHOD: to calculate tax
    public String calculateTax(){

        // getting tax percentage
        float taxPercent = Float.parseFloat((tax_percent.getText()
                .toString()).replace("%", ""));
        taxPercent = taxPercent/100;

        // getting tax value
        DecimalFormat decimalCost = new DecimalFormat("0.00");
        String taxAmt = String.valueOf(decimalCost.format((totalAmt -
                Float.parseFloat(calculateSavings())) * taxPercent));

        return taxAmt;
    }

    // METHOD: to calculate total
    public String calculateTotal(){

        // getting total bill
        DecimalFormat decimalCost = new DecimalFormat("0.00");
        String total = String.valueOf(decimalCost.format(totalAmt +
                Float.parseFloat(calculateTax()) - Float.parseFloat(calculateSavings())));

        return total;
    }

    // METHOD: displaying calculations
    public void displayCalculations(){
        calculate_button = getActivity().findViewById(R.id.calculate_button);
        minus = getActivity().findViewById(R.id.minus);
        savings = getActivity().findViewById(R.id.savings);
        total = getActivity().findViewById(R.id.total);
        tax = getActivity().findViewById(R.id.tax);
        percent_off = getActivity().findViewById(R.id.percent_off);
        after_savings = getActivity().findViewById(R.id.after_savings);


        // displaying calculations when button is clicked
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus.setText("-");
                savings.setText(" $ " + calculateSavings());
                tax.setText(" $ " + calculateTax());
                total.setText(" $ " + calculateTotal());

                String percentage = String.valueOf(Math.round(discountPercent*100f));
                percent_off.setText("(" + percentage + "% off)");
                after_savings.setText("(After savings)");
            }});
    }

}

