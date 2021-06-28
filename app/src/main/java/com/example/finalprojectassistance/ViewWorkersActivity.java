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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class ViewWorkersActivity extends AppCompatActivity {


    //declaring var
    RecyclerView recyclerView;
    ArrayList<WorkerData> workerDataArrayList;
    WorkerAdapter workerAdapter;
    ProgressDialog progressDialog;


    ProgressDialog uploadDialog;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText workerName, workerAddress,workerPhone,agentSpeciality;
    NeumorphCardView addBtn;
    boolean valid = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workers);

        //initializing var
        addBtn = findViewById(R.id.addBtn);



        //setting a progess dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        uploadDialog = new ProgressDialog(this);

        //initializing var
        recyclerView = findViewById(R.id.recyclerViewWorker);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        phoneNumber.findViewById(R.id.workerPhone);
//        callbtn.findViewById(R.id.callPhone);

        //================================================================================================
//fetching in an array

        //initializing db
        fStore = FirebaseFirestore.getInstance();
        workerDataArrayList = new ArrayList<WorkerData>();
        workerAdapter = new WorkerAdapter(ViewWorkersActivity.this,workerDataArrayList);

        //setting recyclerView to Adapter
        recyclerView.setAdapter(workerAdapter);

        //getting data from fireStore
        EventChangeListener();

//================================================================================================


//        ===========================================
        //        ===========================================

        //        ===========================================
                    // ADD TO ADAPTER NOW
        //        ===========================================
//        ===========================================

//
//        //Setting the normal call
//        callbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makePhoneCall();
//            }
//        });
//        ===========================================




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating bottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ViewWorkersActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_add);
                bottomSheetDialog.setCanceledOnTouchOutside(false);


                //initializing var
                NeumorphButton addWorkerBtn = bottomSheetDialog.findViewById(R.id.add_btn);
                NeumorphButton cancelBtn = bottomSheetDialog.findViewById(R.id.cancel_btn);
                workerName = bottomSheetDialog.findViewById(R.id.wokerlName);
                workerAddress = bottomSheetDialog.findViewById(R.id.workerAddress);
                workerPhone = bottomSheetDialog.findViewById(R.id.workerPhone);
                agentSpeciality = bottomSheetDialog.findViewById(R.id.workerSpeciality);

//================================================================================================

                addWorkerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = workerName.getText().toString().trim();
                        String address = workerAddress.getText().toString().trim();
                        String phone = workerPhone.getText().toString().trim();
                        String speciality = agentSpeciality.getText().toString().trim();
                        addWorker(name,address,phone,speciality);


//                        checkField(workerName);
//                        checkField(workerAddress);
//                        checkField(workerPhone);
//                        checkField(agentSpeciality);
//
//                        if (valid){
//
//
//                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                            //FirebaseUser user = fAuth.getCurrentUser();
//                            DocumentReference df = fStore.collection("Workers").document(user.getUid());
//                            Map<String,Object> workerInfo = new HashMap<>();
//
//                            workerInfo.put("FullName",workerName.getText().toString());
//                            workerInfo.put("Address",workerAddress.getText().toString());
//                            workerInfo.put("Phone",workerPhone.getText().toString());
//                            workerInfo.put("Speciality",agentSpeciality.getText().toString());
//
//                            df.set(workerInfo);
//
//                        }




                    }
        });//End add event

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bottomSheetDialog.dismiss();
                    }
                });



                //showing bottomSheetDialog
                bottomSheetDialog.show();

            }
        });
    }//end click event



//    /===============================================================================================================
//    /===============================================================================================================
//    /===============================================================================================================
    //UPLOADING DATA
    private void addWorker(String name, String address, String phone, String speciality) {

        uploadDialog.setTitle("Adding worker...");
        uploadDialog.show();

        String id = UUID.randomUUID().toString();
        Map<String,Object> workerInfo = new HashMap<>();
        workerInfo.put("ID", id);
        workerInfo.put("FullName",name);
        workerInfo.put("Address",address);
        workerInfo.put("Phone",phone);
        workerInfo.put("Speciality",speciality);

        fStore.collection("Workers").document(id).set(workerInfo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        uploadDialog.dismiss();
                    //if completed
                Toast.makeText(ViewWorkersActivity.this,"Added successfully",Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                //if task failed

                Toast.makeText(ViewWorkersActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    //================================================================================================
//    private boolean checkField(EditText textField) {
//        if(textField.getText().toString().isEmpty()){
//            textField.setError("Error");
//            valid = false;
//
//        }//End if statement
//
//        else{
//            valid = true;
//        }//End else statement
//
//        return valid;
//
//    }//End checking boolean method

    //================================================================================================

    //EVENT CHANGE LISTENER DATA Added recycleView
    private void EventChangeListener() {

        fStore.collection("Workers").orderBy("FullName", Query.Direction.ASCENDING)
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


                                workerDataArrayList.add(dc.getDocument().toObject(WorkerData.class));

                            }//end if statement
                            workerAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }//end For loop
                    }
                });
    }




    //!!!!!!!!!!!!!!!!!!!!!! ADD TO ADAPTER NOW !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    =====================================================
        //phone call method
//        private void makePhoneCall() {
//            String number = phoneNumber.getText().toString();
//            //checking if theree's any phone number and removing empty spaces btw numbers
//            if (number.trim().length() > 0 ){
//
//                if (ContextCompat.checkSelfPermission(ViewWorkersActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
//
//                    ActivityCompat.requestPermissions(ViewWorkersActivity.this,new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//                }//END IF statement re-requesting permision phone call
//                else {
//                    String dial = "tel:" + number;
//                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
//                }//
//
//            }//end if statement
//            else {
//                Toast.makeText(ViewWorkersActivity.this,"No Phone Number found",Toast.LENGTH_SHORT).show();
//            }//end else statement
//        }//end method
//
//            @Override
//            public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//                if (requestCode == REQUEST_CALL){
//                    if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
//                        makePhoneCall();
//                    }else {
//                        Toast.makeText(this,"Permission DENIED", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }


}