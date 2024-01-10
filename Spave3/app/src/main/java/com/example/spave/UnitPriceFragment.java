package com.example.spave;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class UnitPriceFragment extends Fragment {
    private Button back_button, calculate_button;
    private Spinner spinner1, spinner2;
    List<String> units;
    List<String> solidUnits;
    List<String> liquidUnits;
    boolean done;
    TextView unitPriceDisplay;
    EditText bulkCost, amount;
    ArrayAdapter<String> arrayAdapterSolid;
    ArrayAdapter<String> arrayAdapterLiquid;
    ArrayAdapter<String> arrayAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unit_price, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // calling methods
        done = true;
        backButton();
        dropDownMenu();
        setDropDownMenu();
        setUnitPrice();

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

    // METHOD: Creating drop down menu
    public void dropDownMenu() {

        // creating options
        units = new ArrayList<>();
        units.add("Milligrams");
        units.add("Grams");
        units.add("Pounds");
        units.add("Kilograms");
        units.add("Millilitres");
        units.add("Pints");
        units.add("Quarts");
        units.add("Litres");
        units.add("Gallons");

        solidUnits = new ArrayList<>();
        solidUnits.add("Milligrams");
        solidUnits.add("Grams");
        solidUnits.add("Pounds");
        solidUnits.add("Kilograms");

        liquidUnits = new ArrayList<>();
        liquidUnits.add("Millilitres");
        liquidUnits.add("Pints");
        liquidUnits.add("Quarts");
        liquidUnits.add("Litres");
        liquidUnits.add("Gallons");

        arrayAdapterSolid = new ArrayAdapter<>(getActivity(),
                R.layout.dropdown_item, solidUnits);
        arrayAdapterSolid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        arrayAdapterLiquid = new ArrayAdapter<>(getActivity(),
                R.layout.dropdown_item, liquidUnits);
        arrayAdapterLiquid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.dropdown_item, units);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // setting spinners with options
        spinner1 = getActivity().findViewById(R.id.spinner1);
        spinner2 = getActivity().findViewById(R.id.spinner2);

        spinner1.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapterSolid);

    }


    public void setDropDownMenu(){
        unitPriceDisplay = getActivity().findViewById(R.id.unitPriceDisplay);

        if (!done){
            // making both unit options match at first
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    String startUnit = spinner1.getSelectedItem().toString();

                    for (int counter = 0; counter < units.size(); counter++) {
                        if (startUnit.equals(units.get(counter))) {
                            if (counter <= 3) {
                                spinner2.setAdapter(arrayAdapterSolid); }
                            else if (counter >= 4) {
                                spinner2.setAdapter(arrayAdapterLiquid); }

                        }
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) { }
            });
        }

        else if (done){
            // making both unit options match at first
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    String startUnit = spinner1.getSelectedItem().toString();

                    for (int counter = 0; counter < units.size(); counter++) {
                        if (startUnit.equals(units.get(counter))) {
                            if (counter <= 3){
                                for (int count = 0; count < solidUnits.size(); count++){
                                    if(startUnit.equals(solidUnits.get(count))){
                                        spinner2.setAdapter(arrayAdapterSolid);
                                        spinner2.setSelection(count); } }
                            }

                            else if (counter >= 4){
                                for (int count = 0; count < liquidUnits.size(); count++){
                                    if(startUnit.equals(liquidUnits.get(count))){
                                        spinner2.setAdapter(arrayAdapterLiquid);
                                        spinner2.setSelection(count); } }
                            }
                        }
                    }
                    done = false;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) { }
            });

        }
    }

    public String calculateSolidUnitPrice(){
        bulkCost = getActivity().findViewById(R.id.bulkCost);
        amount = getActivity().findViewById(R.id.quantity);

        Double startPrice;
        Double quantity;
        String cost = "";
        DecimalFormat decimalCost = new DecimalFormat("0.00");

        String startUnit = spinner1.getSelectedItem().toString();
        String endUnit = spinner2.getSelectedItem().toString();

        // In Case no values are entered
        if (bulkCost.getText().toString().matches("")){
            startPrice = 0.0; }
        else {
            startPrice = Double.parseDouble(bulkCost.getText().toString()); }
        if (amount.getText().toString().matches("")){
            quantity = 0.0; }
        else {
            quantity = Double.parseDouble(amount.getText().toString()); }

        // conversions
        if (!startUnit.equals(endUnit) && quantity != 0.0 && startPrice != 0.0) {
            if (startUnit.equals("Milligrams")) {
                quantity /= 1000; }

            else if (startUnit.equals("Pounds")) {
                quantity *= 453.59237; }

            else if (startUnit.equals("Kilograms")) {
                quantity *= 1000; }


            if (endUnit.equals("Milligrams")) {
                quantity *= 1000; }

            else if (endUnit.equals("Pounds")) {
                quantity /= 453.59237; }

            else if (endUnit.equals("Kilograms")) {
                quantity /= 1000; }

            endUnit = endUnit.replace("s", "");
            cost = "$ " + decimalCost.format(startPrice/quantity) + " / " + endUnit; }

        else if (quantity <= 0.0 && startPrice > 0.0){
            cost = "Please enter a\nquantity."; }

        else if (quantity > 0.0 && startPrice <= 0.0){
            endUnit = endUnit.replace("s", "");
            cost = "$ 0.00 / " + endUnit; }

        else if (quantity <= 0.0 && startPrice <= 0.0){
            cost = "Please enter a\nquantity and its\ncost."; }

        else{
            endUnit = endUnit.replace("s", "");
            cost = "$ " + decimalCost.format(startPrice/quantity) + " / " + endUnit; }

        return cost;
    }

    public String calculateLiquidUnitPrice(){
        bulkCost = getActivity().findViewById(R.id.bulkCost);
        amount = getActivity().findViewById(R.id.quantity);

        Double startPrice;
        Double quantity;
        String cost = "";
        DecimalFormat decimalCost = new DecimalFormat("0.00");


        String startUnit = spinner1.getSelectedItem().toString();
        String endUnit = spinner2.getSelectedItem().toString();


        // In Case no values are entered
        if (amount.getText().toString().matches("")){
            quantity = 0.0; }
        else {
            quantity = Double.parseDouble(amount.getText().toString()); }

        if (bulkCost.getText().toString().matches("")){
            startPrice = 0.0; }
        else {
            startPrice = Double.parseDouble(bulkCost.getText().toString()); }

        // conversions
        if (!startUnit.equals(endUnit) && quantity != 0.0 && startPrice != 0.0){
            if (startUnit.equals("Millilitres")){
                quantity /= 1000; }

            else if (startUnit.equals("Pints")){
                quantity /= 2.113376; }

            else if (startUnit.equals("Quarts")){
                quantity /= 1.056688; }

            else if (startUnit.equals("Gallons")){
                quantity *= 4.54609; }


            if (endUnit.equals("Millilitres")){
                quantity *= 1000; }

            else if (endUnit.equals("Pints")){
                quantity *= 2.113376; }

            else if (endUnit.equals("Quarts")){
                quantity *= 1.056688; }

            else if (endUnit.equals("Gallons")){
                quantity /= 4.54609; }

            endUnit = endUnit.replace("s", "");
            cost = "$ " + decimalCost.format(startPrice/quantity) + " / " + endUnit; }

        else if (quantity <= 0.0 && startPrice > 0.0){
            cost = "Please enter a\nquantity."; }

        else if (quantity > 0.0 && startPrice <= 0.0){
            endUnit = endUnit.replace("s", "");
            cost = "$ 0.00 / " + endUnit; }

        else if (quantity <= 0.0 && startPrice <= 0.0){
            cost = "Please enter a\nquantity and its\ncost."; }

        else{
            endUnit = endUnit.replace("s", "");
            cost = "$ " + decimalCost.format(startPrice/quantity) + " / " + endUnit; }

        return cost;
    }

    public void setUnitPrice(){
        calculate_button = getActivity().findViewById(R.id.calculate_button);
        unitPriceDisplay = getActivity().findViewById(R.id.unitPriceDisplay);


        // displaying calculations when button is clicked
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startUnit = spinner1.getSelectedItem().toString();

                for (int counter = 0; counter < units.size(); counter++) {
                    if (startUnit.equals(units.get(counter))) {
                        if (counter <= 3) {
                            unitPriceDisplay.setText(calculateSolidUnitPrice()); }
                        else if (counter >= 4) {
                            unitPriceDisplay.setText(calculateLiquidUnitPrice());
                        }
                    }
                }

            }});
    }


}