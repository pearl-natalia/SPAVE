package com.example.spave;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class OtherFragment extends Fragment {
    private Button list_button, fact_button, promo_button, sale_button;
    private TextView dateView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        buttons();
        dateSetter();
    }

    private void buttons(){
        list_button = getActivity().findViewById(R.id.list_button);
        sale_button = getActivity().findViewById(R.id.sale_button);
        promo_button = getActivity().findViewById(R.id.promo_button);
        fact_button = getActivity().findViewById(R.id.fact_button);




        // switching fragments when button is clicked
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new ListFragment());
                fragmentTransaction.commit();
            }
        });

        promo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new PromoFragment());
                fragmentTransaction.commit();
            }
        });

        sale_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new SalesFragment());
                fragmentTransaction.commit();
            }
        });

        promo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new PromoFragment());
                fragmentTransaction.commit();
            }
        });

        fact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new FactFragment());
                fragmentTransaction.commit();
            }
        });

    }

    private void dateSetter(){
        dateView = getActivity().findViewById(R.id.dateView);
        String monthsList[] = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        // getting day of week
        String dayOfWeek = LocalDate.now().getDayOfWeek().name().toLowerCase();
        dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);

        // getting month
        String currentMonth = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        StringBuilder month = new StringBuilder(currentMonth);

        // getting date
        String day = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());

        // getting index value of month
        if (String.valueOf(month.charAt(0)).equals("0")){
            month = month.deleteCharAt(0);
        }

        int monthIndex = Integer.parseInt(String.valueOf(month)) - 1;

        // setting date
        String date = dayOfWeek + ", " + monthsList[monthIndex] + " " + day;
        dateView.setText(date);
    }

}