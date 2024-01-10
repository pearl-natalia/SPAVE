package com.example.spave;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalculateFragment extends Fragment {
    private Button tip_button, discount_button, unitPrice_button, currency_button, info_button,
            contact_button, about_button, aboutBlock, contactBlock, backBlock, frontBlock, reloadBlock, reload, previousPage, nextPage,
            exitWebpage, linkBlock, link, block1, block2;
    private ImageView info_background, tipSymbol, discountSymbol, taxSymbol, interestSymbol;
    private TextView text_tip, text_discount, text_tax, text_interest;
    private WebView aboutWeb, aboutBlockPage, contactWeb, contactBlockPage;
    boolean infoPresent = false;
    boolean aboutPage = false;
    boolean contactPage = false;

    BottomNavigationView bottomNavigationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        buttons();
        underline();
        moreInfoScroll();
        loadURL();
        setWebPage();
        copyWebLink();
    }

    // METHOD: displaying the buttons
    public void buttons() {
        tip_button = getActivity().findViewById(R.id.tip_button);
        discount_button = getActivity().findViewById(R.id.discount_button);
        unitPrice_button = getActivity().findViewById(R.id.unit_price_button);
        currency_button = getActivity().findViewById(R.id.currency_button);

        // switching fragments when button is clicked
        tip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new TipFragment());
                fragmentTransaction.commit();
            }
        });

        discount_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new DiscountFragment());
                fragmentTransaction.commit();
            }
        });

        unitPrice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new UnitPriceFragment());
                fragmentTransaction.commit();
            }
        });

        currency_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new CalculatorFragment());
                fragmentTransaction.commit();
            }
        });
    }

    // METHOD: underlining the more info options
    private void underline() {
        contact_button = getActivity().findViewById(R.id.contact_button);
        about_button = getActivity().findViewById(R.id.about_button);

        contact_button.setPaintFlags(contact_button.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        about_button.setPaintFlags(about_button.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }

    // METHOD: displaying more info tab
    private void moreInfoScroll() {
        info_button = getActivity().findViewById(R.id.info_button);
        info_background = getActivity().findViewById(R.id.info_background);
        aboutBlock = getActivity().findViewById(R.id.aboutBlock);
        contactBlock = getActivity().findViewById(R.id.contactBlock);
        reload = getActivity().findViewById(R.id.reload);
        previousPage = getActivity().findViewById(R.id.previousPage);
        nextPage = getActivity().findViewById(R.id.nextPage);
        contact_button = getActivity().findViewById(R.id.contact_button);
        about_button = getActivity().findViewById(R.id.about_button);
        exitWebpage = getActivity().findViewById(R.id.exitWebpage);
        tipSymbol = getActivity().findViewById(R.id.tipSymbol);
        discountSymbol = getActivity().findViewById(R.id.discountSymbol);
        taxSymbol = getActivity().findViewById(R.id.taxSymbol);
        interestSymbol = getActivity().findViewById(R.id.interestSymbol);
        text_tip = getActivity().findViewById(R.id.text_tip);
        text_discount = getActivity().findViewById(R.id.text_discount);
        text_interest = getActivity().findViewById(R.id.text_interest);
        text_tax = getActivity().findViewById(R.id.text_tax);
        aboutBlockPage = getActivity().findViewById(R.id.aboutBlockPage);
        contactBlockPage = getActivity().findViewById(R.id.contactBlockPage);
        link = getActivity().findViewById(R.id.webLink);

        block1 = getActivity().findViewById(R.id.block1);
        block2 = getActivity().findViewById(R.id.block2);

        Animation animSlideIn = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in);
        Animation animSlideOut = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out);
        Animation animSlideIn2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.web_in);
        Animation animSlideOut2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.web_out);

        final Handler handler = new Handler();

        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadURL();

                if (!infoPresent) {
                    info_background.startAnimation(animSlideIn);
                    contact_button.startAnimation(animSlideIn);
                    about_button.startAnimation(animSlideIn);
                    infoPresent = true; }

                else if (infoPresent) {
                    info_background.startAnimation(animSlideOut);
                    contact_button.startAnimation(animSlideOut);
                    about_button.startAnimation(animSlideOut);
                    infoPresent = false; }
            }
        });

        block1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoPresent){
                    info_background.startAnimation(animSlideOut);
                    contact_button.startAnimation(animSlideOut);
                    about_button.startAnimation(animSlideOut);
                    infoPresent = false;
                }
            }
        });

        block2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoPresent){
                    info_background.startAnimation(animSlideOut);
                    contact_button.startAnimation(animSlideOut);
                    about_button.startAnimation(animSlideOut);
                    infoPresent = false;
                }
            }
        });

        aboutBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoPresent){
                    aboutPage = true;
                    link.setText(aboutBlockPage.getUrl());
                    setWebPage();
                    aboutWeb.startAnimation(animSlideIn2);
                    showWidget();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            aboutBlockPage.setVisibility(View.VISIBLE);
                            aboutWeb.setVisibility(View.INVISIBLE);
                        }
                    }, 299);

                    infoPresent = false;
                }
            }
        });

        contactBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoPresent) {
                    contactPage = true;
                    link.setText(contactBlockPage.getUrl());
                    setWebPage();
                    contactWeb.startAnimation(animSlideIn2);
                    showWidget();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            contactBlockPage.setVisibility(View.VISIBLE);
                            contactWeb.setVisibility(View.INVISIBLE);
                        }
                    }, 299);

                    infoPresent = false;
                }
            }
        });

        exitWebpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (aboutPage){
                    aboutWeb.setVisibility(View.VISIBLE);
                    aboutBlockPage.setVisibility(View.INVISIBLE);
                    aboutWeb.startAnimation(animSlideOut2);
                    aboutPage = false;
                }

                else if (contactPage){
                    contactWeb.setVisibility(View.VISIBLE);
                    contactBlockPage.setVisibility(View.INVISIBLE);
                    contactWeb.startAnimation(animSlideOut2);
                    contactPage = false;
                }

                reload.startAnimation(animSlideOut2);
                previousPage.startAnimation(animSlideOut2);
                nextPage.startAnimation(animSlideOut2);
                link.startAnimation(animSlideOut2);

                info_button.setVisibility(View.VISIBLE);
                discountSymbol.setVisibility(View.VISIBLE);
                taxSymbol.setVisibility(View.VISIBLE);
                tipSymbol.setVisibility(View.VISIBLE);
                interestSymbol.setVisibility(View.VISIBLE);
                text_discount.setVisibility(View.VISIBLE);
                text_tip.setVisibility(View.VISIBLE);
                text_interest.setVisibility(View.VISIBLE);
                text_tax.setVisibility(View.VISIBLE);
                tip_button.setVisibility(View.VISIBLE);
                discount_button.setVisibility(View.VISIBLE);
                currency_button.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.VISIBLE);
                unitPrice_button.setVisibility(View.VISIBLE);

                linkBlock.setVisibility(View.INVISIBLE);
                exitWebpage.setVisibility(View.INVISIBLE);
                backBlock.setVisibility(View.INVISIBLE);
                frontBlock.setVisibility(View.INVISIBLE);
                reloadBlock.setVisibility(View.INVISIBLE);

            }
        });

    }

    private void loadURL(){
        aboutWeb = getActivity().findViewById(R.id.aboutPage);
        aboutWeb.setWebViewClient(new WebViewClient());
        aboutWeb.loadUrl("https://spavetech.wixsite.com/spave/about");

        aboutBlockPage.setWebViewClient(new WebViewClient());
        aboutBlockPage.loadUrl(aboutWeb.getUrl());

        contactWeb = getActivity().findViewById(R.id.contactPage);
        contactWeb.setWebViewClient(new WebViewClient());
        contactWeb.loadUrl("https://spavetech.wixsite.com/spave/contact");

        contactBlockPage.setWebViewClient(new WebViewClient());
        contactBlockPage.loadUrl(contactWeb.getUrl());

        String mobileView = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ " +
                "(KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3";

        WebSettings webSettings = aboutWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        aboutWeb.getSettings().setUserAgentString(mobileView);

        WebSettings webSettings2 = aboutBlockPage.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        aboutBlockPage.getSettings().setUserAgentString(mobileView);

        WebSettings webSettings3 = contactWeb.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        contactWeb.getSettings().setUserAgentString(mobileView);

        WebSettings webSettings4 = contactBlockPage.getSettings();
        webSettings4.setJavaScriptEnabled(true);
        contactBlockPage.getSettings().setUserAgentString(mobileView);

        aboutWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        aboutBlockPage.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        contactWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        contactBlockPage.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

    }

    private void setWebPage(){
        // back button
        backBlock = getActivity().findViewById(R.id.backBlock);
        backBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aboutPage && aboutBlockPage.canGoBack()){
                    aboutBlockPage.goBack(); }

                else if (contactPage && contactBlockPage.canGoBack()){
                    contactBlockPage.goBack(); }
            }
        });

        // forward button
        frontBlock = getActivity().findViewById(R.id.frontBlock);
        frontBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aboutPage && aboutBlockPage.canGoForward()){
                    aboutBlockPage.goForward(); }

                else if (contactPage && contactBlockPage.canGoForward()){
                    contactBlockPage.goForward(); }
            }
        });

        reloadBlock = getActivity().findViewById(R.id.reloadBlock);
        reloadBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aboutPage) {
                    aboutBlockPage.reload(); }
                else if (contactPage){
                    contactBlockPage.reload(); }

            }
        });

    }

    // METHOD: copy link
    private void copyWebLink(){
        linkBlock = getActivity().findViewById(R.id.linkBlock);
        linkBlock.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

                if (aboutPage){
                    ClipData clip = ClipData.newPlainText("EditText", aboutBlockPage.getUrl());
                    clipboard.setPrimaryClip(clip);
                }
                else if (contactPage){
                    ClipData clip = ClipData.newPlainText("EditText", contactBlockPage.getUrl());
                    clipboard.setPrimaryClip(clip);
                }

                Toast.makeText(getActivity().getApplicationContext(), "Link Copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private void showWidget(){
        Animation animSlideOut = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out);
        Animation animSlideIn2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.web_in);

        info_background.startAnimation(animSlideOut);
        contact_button.startAnimation(animSlideOut);
        about_button.startAnimation(animSlideOut);

        reload.startAnimation(animSlideIn2);
        previousPage.startAnimation(animSlideIn2);
        nextPage.startAnimation(animSlideIn2);
        link.startAnimation(animSlideIn2);

        info_button.setVisibility(View.INVISIBLE);
        tip_button.setVisibility(View.INVISIBLE);
        discount_button.setVisibility(View.INVISIBLE);
        currency_button.setVisibility(View.INVISIBLE);
        unitPrice_button.setVisibility(View.INVISIBLE);

        discountSymbol.setVisibility(View.INVISIBLE);
        taxSymbol.setVisibility(View.INVISIBLE);
        tipSymbol.setVisibility(View.INVISIBLE);
        interestSymbol.setVisibility(View.INVISIBLE);

        text_discount.setVisibility(View.INVISIBLE);
        text_tip.setVisibility(View.INVISIBLE);
        text_interest.setVisibility(View.INVISIBLE);
        text_tax.setVisibility(View.INVISIBLE);
        tip_button.setVisibility(View.INVISIBLE);

        exitWebpage.setVisibility(View.VISIBLE);
        backBlock.setVisibility(View.VISIBLE);
        frontBlock.setVisibility(View.VISIBLE);
        reloadBlock.setVisibility(View.VISIBLE);
        linkBlock.setVisibility(View.VISIBLE);

        bottomNavigationView.setVisibility(View.GONE);
    }

}