package com.example.spave;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotesFragment extends Fragment {
    Button back_button, copy_button, bold_button, italic_button, underline_button, noFormat, button;
    BottomNavigationView bottomNavigationView;
    EditText notes_input;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);
        notes_input = getActivity().findViewById(R.id.notes_input);

        backButton();
        copyText();
        boldText();
        italicizeText();
        underlineText();
        noFormatText();
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

    // METHOD: copying text to clipboard when copy button is clicked
    public void copyText() {
        copy_button = getActivity().findViewById(R.id.copy);
        notes_input = getActivity().findViewById(R.id.notes_input);

        copy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startSelection = notes_input.getSelectionStart();
                int endSelection = notes_input.getSelectionEnd();
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

                // coping selected text if highlighted
                if (startSelection != endSelection) {
                    String selectedText = notes_input.getText().toString().substring(startSelection, endSelection);
                    ClipData clip = ClipData.newPlainText("EditText", selectedText);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getActivity().getApplicationContext(), "Selected Text Copied", Toast.LENGTH_SHORT).show();

                }

                // copying entire text if nothing is highlighted
                else {
                    ClipData clip = ClipData.newPlainText("EditText", notes_input.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getActivity().getApplicationContext(), "Entire note copied", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void boldText() {
        bold_button = getActivity().findViewById(R.id.bold);
        notes_input = getActivity().findViewById(R.id.notes_input);

        bold_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spannable spannableString = new SpannableStringBuilder(notes_input.getText());

                spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                        notes_input.getSelectionStart(),
                        notes_input.getSelectionEnd(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                notes_input.setText(spannableString);

            }
        });
    }

    public void italicizeText() {
        italic_button = getActivity().findViewById(R.id.italicize);
        notes_input = getActivity().findViewById(R.id.notes_input);

        italic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spannable spannableString = new SpannableStringBuilder(notes_input.getText());

                spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                        notes_input.getSelectionStart(),
                        notes_input.getSelectionEnd(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                notes_input.setText(spannableString);
            }
        });
    }

    public void underlineText() {
        underline_button = getActivity().findViewById(R.id.underline);
        notes_input = getActivity().findViewById(R.id.notes_input);

        underline_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spannable spannableString = new SpannableStringBuilder(notes_input.getText());

                spannableString.setSpan(new UnderlineSpan(),
                        notes_input.getSelectionStart(),
                        notes_input.getSelectionEnd(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                notes_input.setText(spannableString);
            }
        });
    }

    public void noFormatText() {
        noFormat = getActivity().findViewById(R.id.noFormat);
        notes_input = getActivity().findViewById(R.id.notes_input);

        noFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes_input.setText(notes_input.getText().toString());
            }
        });
    }


}



