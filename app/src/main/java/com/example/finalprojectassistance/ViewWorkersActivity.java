package com.example.finalprojectassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class ViewWorkersActivity extends AppCompatActivity {


    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText workerName, workerAddress,workerPhone;
    NeumorphCardView addBtn;
    boolean valid = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workers);

        //initializing var
        addBtn = findViewById(R.id.addBtn);


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


                addWorkerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkField(workerName);
                        checkField(workerAddress);
                        checkField(workerPhone);

                        if (valid){


                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseUser user = fAuth.getCurrentUser();
                            DocumentReference df = fStore.collection("Workers").document(user.getUid());
                            Map<String,Object> workerInfo = new HashMap<>();

                            workerInfo.put("FullName",workerName.getText().toString());
                            workerInfo.put("Address",workerAddress.getText().toString());
                            workerInfo.put("Phone",workerPhone.getText().toString());

                            df.set(workerInfo);

                        }




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

    private boolean checkField(EditText textField) {
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;

        }//End if statement

        else{
            valid = true;
        }//End else statement

        return valid;

    }//End checking boolean method


}