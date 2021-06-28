package com.example.finalprojectassistance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

public class ViewOrdersActivity extends AppCompatActivity {

    //declaring var
    RecyclerView recyclerView;
    ArrayList<AdminOrderData> adminOrderAdapterArrayList;
    AdminOrderAdapter adminOrderAdapter;
    ProgressDialog progressDialog;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    TextView clientName, clientAddress,clientPhone,orderDate,orderContent, orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

//        clientName = findViewById(R.id.clientName);
//        clientAddress = findViewById(R.id.clientAddress);
//        clientPhone = findViewById(R.id.clientPhone);
//        orderDate = findViewById(R.id.orderDate);
//        orderContent = findViewById(R.id.orderContent);
//        orderService = findViewById(R.id.orderService);



        //setting a progess dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


//==================================================================================
        recyclerView = findViewById(R.id.recyclerViewOrderAdmin);
        //initializing db
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        adminOrderAdapterArrayList = new ArrayList<AdminOrderData>();
        adminOrderAdapter = new AdminOrderAdapter(ViewOrdersActivity.this,adminOrderAdapterArrayList);

        //setting recyclerView to Adapter
        recyclerView.setAdapter(adminOrderAdapter);

        //getting data from fireStore
        EventChangeListener();

    }                   //End of onCreate(Bundle savedInstanceState)

    //==================================================================================

    private void EventChangeListener() {
//       String userID = fAuth.getCurrentUser().getUid();
//        fStore.collection("Order").orderBy("Users").equals(userID);
        fStore.collection("Orders").orderBy("Time", Query.Direction.ASCENDING)
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


                                adminOrderAdapterArrayList.add(dc.getDocument().toObject(AdminOrderData.class));

                            }//end if statement
                            adminOrderAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }//end For loop
                    }
                });

    }//End of EventChange Listener



}