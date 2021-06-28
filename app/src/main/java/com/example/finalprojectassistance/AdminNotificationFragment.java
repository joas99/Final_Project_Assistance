package com.example.finalprojectassistance;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminNotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminNotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminNotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminNotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminNotificationFragment newInstance(String param1, String param2) {
        AdminNotificationFragment fragment = new AdminNotificationFragment();
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

    RecyclerView recyclerView;
    FirebaseFirestore fStore;
    ArrayList<AdminNotificationData> adminNotificationDataArrayList;
    AdminNotificationAdapter adminNotificationAdapter;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_admin_notification, container, false);

        //recyclerViewNotification
        //setting a progess dialog
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //initializing var
        recyclerView = view.findViewById(R.id.recyclerViewNotification);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


//==============================================
        //initializing db
        fStore = FirebaseFirestore.getInstance();
        adminNotificationDataArrayList = new ArrayList<AdminNotificationData>();
        adminNotificationAdapter = new AdminNotificationAdapter(view.getContext(),adminNotificationDataArrayList);



        //setting recyclerView to Adapter
        recyclerView.setAdapter(adminNotificationAdapter);

        //getting data from fireStore
        EventChangeListener();

        return view;
    }

    private void EventChangeListener() {
        fStore.collection("Orders").orderBy("Address", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        //call back method
                        if (error != null){
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("FireStore error",error.getMessage());
                            return;
                        }//end if statement

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){


                                adminNotificationDataArrayList.add(dc.getDocument().toObject(AdminNotificationData.class));

                            }//end if statement
                            adminNotificationAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }//end For loop

                    }
                });

    }
}