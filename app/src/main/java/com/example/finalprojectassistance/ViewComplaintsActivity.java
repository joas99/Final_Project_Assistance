package com.example.finalprojectassistance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class ViewComplaintsActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;


    //declaring var
    RecyclerView recyclerView;
    ArrayList<ComplaintData> complaintDataArrayList;
    ComplaintAdapter complaintAdapter;
    ProgressDialog progressDialog;

    private static final int REQUEST_CALL = 1;
    private TextView phoneNumber;
    NeumorphCardView callbtn;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText complainerName, complainerAddress,complainerPhone,complaintDate;
    NeumorphCardView addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);

//        //init var
//        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });


        //setting a progess dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //initializing var
        //recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //phoneNumber.findViewById(R.id.complainerPhone);
        //callbtn.findViewById(R.id.callPhone);

        recyclerView = findViewById(R.id.recyclerView);
        //initializing db
        fStore = FirebaseFirestore.getInstance();
        complaintDataArrayList = new ArrayList<ComplaintData>();
        complaintAdapter = new ComplaintAdapter(ViewComplaintsActivity.this,complaintDataArrayList);

        //setting recyclerView to Adapter
        recyclerView.setAdapter(complaintAdapter);

        //getting data from fireStore
        EventChangeListener();

//        ===============================================================


    }

    private void EventChangeListener() {
        fStore.collection("Reports").orderBy("Title", Query.Direction.ASCENDING)
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


                                complaintDataArrayList.add(dc.getDocument().toObject(ComplaintData.class));

                            }//end if statement
                            complaintAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }//end For loop
                    }
                    });

                    //@Override
//                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
//                        //call back method
//                        if (error != null){
//                            if (progressDialog.isShowing())
//                                progressDialog.dismiss();
//                            Log.e("FireStore error",error.getMessage());
//                            return;
//                        }//end if statement
//
//                        for (DocumentChange dc : value.getDocumentChanges()){
//                            if (dc.getType() == DocumentChange.Type.ADDED){
//
//
//                                complaintDataArrayList.add(dc.getDocument().toObject(ComplaintData.class));
//
//                            }//end if statement
//                            complaintAdapter.notifyDataSetChanged();
//                            if (progressDialog.isShowing())
//                                progressDialog.dismiss();
//                        }//end For loop
//                    }

    }
}