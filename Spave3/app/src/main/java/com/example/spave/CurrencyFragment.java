package com.example.spave;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyFragment extends Fragment {
    private Button back_button, convert_button;
    String[] country = {"AFN", "EUR", "ALL", "CAD", "USD"};
    ArrayList<String> arrayList;
    TextView finalCurrency;
    TextView spinner1, spinner2;
    EditText initialCurrency;
    String convertFromValue, convertToValue, conversionValue;
    Dialog fromDialog, toDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // calling methods
        backButton();
//        exchangeMoney();
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

//    private void exchangeMoney() {
//        spinner1 = getActivity().findViewById(R.id.spinner1);
//        spinner2 = getActivity().findViewById(R.id.spinner2);
//        convert_button = getActivity().findViewById(R.id.convert_button);
//        finalCurrency = getActivity().findViewById(R.id.finalCurrency);
//        initialCurrency = getActivity().findViewById(R.id.initialCurrency);
//
//        arrayList = new ArrayList<>();
//        for (String i : country) {
//            arrayList.add(i);
//        }
//
//        spinner1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fromDialog = new Dialog(CurrencyFragment.this.getActivity());
//                fromDialog.setContentView(R.layout.from_spinner);
//                fromDialog.getWindow().setLayout(650, 800);
//                fromDialog.show();
//
//                EditText editText = fromDialog.findViewById(R.id.edit_text);
//                ListView listView = fromDialog.findViewById(R.id.list_view);
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(CurrencyFragment.this.getActivity(),
//                        android.R.layout.simple_list_item_1, arrayList);
//                listView.setAdapter(adapter);
//
//                editText.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        adapter.getFilter().filter(s);
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                    }
//                });
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                        spinner1.setText(adapter.getItem(position));
//                        fromDialog.dismiss();
//                        convertFromValue = adapter.getItem(position);
//                    }
//                });
//            }
//        });
//
//        spinner2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toDialog = new Dialog(CurrencyFragment.this.getActivity());
//                toDialog.setContentView(R.layout.to_spinner);
//                toDialog.getWindow().setLayout(650, 800);
//                toDialog.show();
//
//                EditText editText = toDialog.findViewById(R.id.edit_text);
//                ListView listView = toDialog.findViewById(R.id.list_view);
//
//                ArrayAdapter<String>adapter = new ArrayAdapter<>(CurrencyFragment.this.getActivity(),
//                        android.R.layout.simple_list_item_1, arrayList);
//                listView.setAdapter(adapter);
//
//
//                editText.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        adapter.getFilter().filter(s); }
//
//                    @Override
//                    public void afterTextChanged(Editable s) { }
//                });
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                        spinner2.setText(adapter.getItem(position));
//                        toDialog.dismiss();
//                        convertToValue = adapter.getItem(position);
//                    }
//                });
//            }
//        });
//
//        convert_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    Double amountToConvert = Double.valueOf(initialCurrency.getText().toString());
//                    getConversionRate(convertFromValue, convertToValue, amountToConvert); }
//
//                catch (Exception e){ }
//            }
//        });
//
//
//    }
//
//    public void getConversionRate(String convertFrom, String convertTo, Double amountToConvert){
//        RequestQueue queue = Volley.newRequestQueue(this.getActivity());
//        String url = "https://free.currconv.com/api/v7/convert?q=" + convertFrom + "_" + convertTo + "&compact=ultra&apiKey=22e91ab924eb2aa6f9a4";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject(response);
//                    Double conversionRateValue = round(((Double) jsonObject.get( convertFrom + "_" + convertTo)), 2);
//                    conversionValue = "" + round((conversionRateValue * amountToConvert), 2);
//                    finalCurrency.setText(conversionValue); }
//
//                catch (JSONException e) {
//                    e.printStackTrace();
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) { }
//        });
//
//        queue.add(stringRequest);
//    }
//
//    public static double round(double value, int places){
//        if(places<0) throw new IllegalArgumentException();
//        BigDecimal bd = BigDecimal.valueOf(value);
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//
//        return bd.doubleValue();
//    }



}