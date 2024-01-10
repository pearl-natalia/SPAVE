package com.example.spave;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CalculateFragment extends Fragment {
    private Button tip_button, discount_button, unitPrice_button, currency_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        tip_button = getActivity().findViewById(R.id.tip_button);
        discount_button = getActivity().findViewById(R.id.discount_button);
        unitPrice_button = getActivity().findViewById(R.id.unit_price);
        currency_button = getActivity().findViewById(R.id.currency_button);

        // switching fragments when button is clicked
        tip_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new TipFragment());
                fragmentTransaction.commit();
            }
        });

        discount_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new DiscountFragment());
                fragmentTransaction.commit();
            }
        });

        unitPrice_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new UnitPriceFragment());
                fragmentTransaction.commit();
            }
        });

        currency_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new CurrencyFragment());
                fragmentTransaction.commit();
            }
        });

    }
}