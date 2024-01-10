package com.example.spave;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import android.content.ClipboardManager;
import android.widget.Toast;

public class FactFragment extends Fragment {
    private Button back_button, new_fact_button, copy_button;
    private TextView factView;
    private ImageView lightBulb;

    static ArrayList<Integer> numbers = new ArrayList<Integer>();

    static String facts[] = {
            "85-95% of US bills contain traces of cocaine",
            "A $1 US bill typically lasts 5.8 years from wear and tear",
            "$2 bills are considered unlucky",
            "A ripped bill can be replaced at the bank if you still have more than half of it",
            "It takes a 10-year apprenticeship to become a bank-note engraver",
            "People have sued over the \"In God We Trust\" motto on US currency due to the implicit religious message behind it",
            "The $1 US bill hasn’t been redesigned in over 50 years",
            "Under a UV light, the US $5 bill has security threads that glow blue",
            "The secret service was originally made to deal with counterfeit money",
            "Bills cost less than 20¢ to produce",
            "US bills are actually made from 75% cotton + 25% linen rather than paper",
            "US bills are only printed in 2 places of the world (Washington D.C and Fort Worth, Texas)",
            "US states almost were close to having their own currencies at one point in time",
            "During the coin shortage in the civil war, the government would accept postage stamps as currency",
            "It costs more than a penny to make a penny",
            "It takes about 8000 folds before a bill will start to wear and tear",
            "The $10 bill has the shortest life expectancy (from wear and tear)",
            "There are over 1.1 billion US $2 bills still around",
            "There was once a $100,000 US bill (from 1934-1935) used by the government",
            "A dime has 118 grooves around it",
            "The grooves in coins prevent people from scraping off the faces and selling them as previous metals",
            "There are 293 ways to make change for a dollar",
            "⅔ of US $100 bills are overseas in other countries",
            "$20 bills is the most counterfeited bill",
            "$100 bills is the second most counterfeited bill",
            "North Korea is the largest counterfeiter of US bills",
            "You can somewhat restore a worn out US bill by ironing it",
            "Worn out coins are melted to make new coins",
            "90% of paper money is dirtier than a household toilet",
            "Before 1913, each bank printed out its own money",
            "Only 8% of the world's currency is physical money ",
            "More monopoly money is printed in a year than money in the entire world",
            "Apple makes 1 million dollars every 8 minutes",
            "The average person has $56 of loose change in their house",
            "There are ATMS in every country of the world",
            "If you have $10 in your pocket and you have no debt, you are wealthier than 25% of americans.",
            "Nickels used to be half the size of a dime",
            "ALL US paper bills weigh 1 gram each",
            "Switzerland's bills were deemed to be the prettiest currency in the world in 2016",
            "Kuwait, a country in the Middle East, has the highest valued currency in the entire world"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fact, container, false);
    }

    // calling methods on start
    @Override
    public void onStart() {
        super.onStart();
        backButton();
        if (numbers.size() != facts.length){
            listMaker();
        }
        displayFacts();
        copyText();


    }

    //METHOD: switching fragments when button is clicked
    private void backButton() {
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

    //METHOD: initializing list
    private void listMaker(){
        for (int i = 0; i < facts.length; i++){
            numbers.add(i);
        }
    }

    //METHOD: picking a fact
    private String getFact(){
        String newFact;

        if (numbers.size() == 0){
            listMaker();
        }

        // getting length of numbers list
        int length = numbers.size();

        // random number generator
        Random random = new Random();
        int num = random.nextInt(length);

        // getting index
        int index = numbers.get(num);

        // printing fact
        newFact = (facts[index]);

        // removing index value so same fact can't be re-printed
        numbers.remove(num);

        return newFact;
    }

    //METHOD: displaying the fact when button is clicked
    private void displayFacts(){
        new_fact_button = getActivity().findViewById(R.id.new_fact_button);
        factView = getActivity().findViewById(R.id.factView);
        lightBulb = getActivity().findViewById(R.id.lightBulb);


        new_fact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightBulb.setVisibility(View.GONE);
                copy_button.setVisibility(View.VISIBLE);
                factView.setText(getFact() + ".");
            }
        });
    }

    // METHOD: copying text to clipboard when copy button is clicked
    public void copyText(){
        copy_button = getActivity().findViewById(R.id.copy_button);

        copy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", factView.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity().getApplicationContext(), "Fact Copied", Toast.LENGTH_SHORT).show();

            }
        });

    }

}