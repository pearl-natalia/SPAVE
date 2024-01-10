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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SalesFragment extends Fragment {

    private WebView webView;
    private Button back_button, previousPage, nextPage, reload, webLink;
    BottomNavigationView bottomNavigationView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sales, container, false);
    }

    @Override
    public void onStart() {
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.GONE);

        super.onStart();
        backButton();
        setWebPage();
        copyWebLink();
    }


    //METHOD: switching fragments when button is clicked
    private void backButton(){
        back_button = getActivity().findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new OtherFragment());
                fragmentTransaction.commit();
            }
        });
    }

    //METHOD: setting webpage
    private void setWebPage(){
        // the webpage
        webView = getActivity().findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.retailmenot.com/ca#:~:text=Best%20Clothing%2C%20Accessories%20%26%20Shoes%20Deals");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // back button
        previousPage = getActivity().findViewById(R.id.previousPage);
        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()){
                    webView.goBack();
                }
            }
        });

        // forward button
        nextPage = getActivity().findViewById(R.id.nextPage);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()){
                    webView.goForward();
                }
            }
        });

        reload = getActivity().findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });
    }

    private void copyWebLink(){
        webLink = getActivity().findViewById(R.id.webLink);
        webLink.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", webLink.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity().getApplicationContext(), "Link Copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


}