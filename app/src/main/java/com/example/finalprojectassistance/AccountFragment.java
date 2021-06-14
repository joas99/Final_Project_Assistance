package com.example.finalprojectassistance;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soup.neumorphism.NeumorphCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    AppCompatButton terms,faq;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        //Initializing =\ activity directions

        terms = (AppCompatButton) view.findViewById(R.id.termsBtn);
        faq = (AppCompatButton) view.findViewById(R.id.faqBtn);



        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder termsDialog = new AlertDialog.Builder(v.getContext());
                termsDialog.setTitle("Terms and Conditions");
                termsDialog.setMessage(" " +
                        "Lorem upseum  Enter Your Email To Receive reset Link lorem" +
                        "Lorem upseum  Enter Your Email To Receive reset Link lorem" +
                        "Lorem upseum  Enter Your Email To Receive reset Link lorem" +
                        "Lorem upseum  Enter Your Email To Receive reset Link lorem" +
                        "Lorem upseum  Enter Your Email To Receive reset Link lorem" +
                        "Lorem upseum  Enter Your Email To Receive reset Link lorem" +
                        "Lorem upseum  Enter Your Email To Receive reset Link lorem");
                termsDialog.setNegativeButton("I Agreed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Closing the dialog

                    }
                });

                //Displaying The forgot password dialog
                termsDialog.create().show();

                //Displaying The forgot password dialog
                termsDialog.create().show();
            }
        });


        return view;
    }
}