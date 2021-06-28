package com.example.finalprojectassistance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class ViewClientsActivity extends AppCompatActivity {

    //declaring var
    RecyclerView recyclerView;
    ArrayList<ClientData> clientAdapterArrayList;
    ClientAdapter clientAdapter;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;

    private static final int REQUEST_CALL = 1;
    private TextView phoneNumber;
    NeumorphCardView callbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clients);


        //setting a progess dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //initializing var
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //phoneNumber.findViewById(R.id.clientPhone);
//        callbtn.findViewById(R.id.callPhoneViewClient);



//================================================================================================
//fetching in an array






//================================================================================================

//        //Setting the normal call
//        callbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makePhoneCall();
//            }
//        });





        //initializing db
        fStore = FirebaseFirestore.getInstance();
        clientAdapterArrayList = new ArrayList<ClientData>();
        clientAdapter = new ClientAdapter(ViewClientsActivity.this,clientAdapterArrayList);



        //setting recyclerView to Adapter
        recyclerView.setAdapter(clientAdapter);

        //getting data from fireStore
        EventChangeListener();

    }


//=============================================



    //fun
    private void EventChangeListener() {
        fStore.collection("Users").orderBy("FullName", Query.Direction.ASCENDING)
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


                                clientAdapterArrayList.add(dc.getDocument().toObject(ClientData.class));

                            }//end if statement
                            clientAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }//end For loop

                    }
                });
    }//End eventChangeListener





    //phone call method
    private void makePhoneCall() {
        String number = phoneNumber.getText().toString();
        //checking if theree's any phone number and removing empty spaces btw numbers
        if (number.trim().length() > 0 ){

            if (ContextCompat.checkSelfPermission(ViewClientsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(ViewClientsActivity.this,new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }//END IF statement re-requesting permision phone call
            else {
               String dial = "tel:" + number;
               startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }//

        }//end if statement
        else {
            Toast.makeText(ViewClientsActivity.this,"No Phone Number found",Toast.LENGTH_SHORT).show();
        }//end else statement
    }//end method

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else {
                Toast.makeText(this,"Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }

    }
}