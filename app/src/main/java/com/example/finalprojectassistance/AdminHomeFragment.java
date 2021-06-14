package com.example.finalprojectassistance;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import soup.neumorphism.NeumorphCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminHomeFragment newInstance(String param1, String param2) {
        AdminHomeFragment fragment = new AdminHomeFragment();
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

    //DECLARING VAR
    NeumorphCardView card_view_orders;
    NeumorphCardView card_view_complaints;
    NeumorphCardView card_view_worker;
    NeumorphCardView card_view_client;

    AppCompatButton logout;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_admin_home, container, false);

        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        //Initializing =\ activity directions

        card_view_orders = (NeumorphCardView) view.findViewById(R.id.card_view_orders);
        card_view_complaints = (NeumorphCardView) view.findViewById(R.id.card_view_complaints);
        card_view_worker = (NeumorphCardView) view.findViewById(R.id.card_view_worker);
        card_view_client = (NeumorphCardView) view.findViewById(R.id.card_view_client);

        logout = (AppCompatButton) view.findViewById(R.id.logoutBtn);


        //Logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(),Login_activity.class));

            }
        });



        //ON CLICK EVENT TO OPEN UP DIFFERENT ACTIVITIES
        card_view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),ViewOrdersActivity.class));


            }
        });//End event Open orders


        card_view_complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ViewComplaintsActivity.class));

            }
        });//End event Open complaints

        card_view_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),ViewWorkersActivity.class));
            }
        });//End event Open orders

        card_view_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),ViewClientsActivity.class));

            }
        });//End event Open orders



        return view;
    }
}