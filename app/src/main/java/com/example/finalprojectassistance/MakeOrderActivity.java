package com.example.finalprojectassistance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import soup.neumorphism.NeumorphCardView;

public class MakeOrderActivity extends AppCompatActivity {

    NeumorphCardView cat_Baby,cat_plumb,cat_elec;
   // AppCompatButton continueBtn, cancelBtn;


    //declaring var
    private final String CHANNEL_ID = "Order_notifications";
    private final int NOTIFICATION_ID = 001;
    TextView cat_title;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    boolean valid = true;
    CheckBox option1checked,option2checked,option3checked;
    @ServerTimestamp
    Date timeStamp;


    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        //initializing categories
        cat_Baby = findViewById(R.id.cat_babysitter);
        cat_plumb = findViewById(R.id.cat_plumber);
        cat_elec = findViewById(R.id.cat_electrician);


        //initializing the variables

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();








//        ======================================================================================
        //setting click events

        cat_Baby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating bottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MakeOrderActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                bottomSheetDialog.setCanceledOnTouchOutside(false);

                //Initializing and Assigning variables
                option1checked = bottomSheetDialog.findViewById(R.id.option1);
                option2checked = bottomSheetDialog.findViewById(R.id.option2);
                option3checked = bottomSheetDialog.findViewById(R.id.option3);
                AppCompatButton continueBtn = bottomSheetDialog.findViewById(R.id.continue_btn);
                AppCompatButton cancelBtn = bottomSheetDialog.findViewById(R.id.cancel_btn);

                RoundedImageView cat_image = bottomSheetDialog.findViewById(R.id.cat_image);
                 cat_title = bottomSheetDialog.findViewById(R.id.cat_title);

                //setting current data info


                option1checked.setText("Daily      5.000Fc - 8.000Fc");
                option2checked.setText("Weekly     20.000Fc  - 30.000Fc");
                option3checked.setText("Monthly    35.000Fc - 60.000Fc");
                cat_image.setImageResource(R.drawable.baby2);
                cat_title.setText("Babysitter");




                //cat_image.setImageDrawable(R.layout.activity_make_order);

                //set on click continue put in firebase

                continueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    String option1 = option1checked.getText().toString();
                    String option2 = option2checked.getText().toString();
                    String option3 = option3checked.getText().toString();
                    String id = UUID.randomUUID().toString();

                    saveToFirestore(option1,option2,option3,id);


                    String message = "You have received an order notification";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                                MakeOrderActivity.this
                        )
                                .setSmallIcon(R.drawable.ic_notifications_24)
                                .setContentTitle("Order notification")
                                .setContentText(message)
                                .setAutoCancel(true);
                        Intent intent = new Intent(MakeOrderActivity.this,AdminNotificationFragment.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("message",message);
                        PendingIntent pendingIntent  = PendingIntent.getActivity(MakeOrderActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT );
                        builder.setContentIntent(pendingIntent);
                        NotificationManager notificationManager = (NotificationManager)getSystemService(
                                Context.NOTIFICATION_SERVICE
                        );
                        notificationManager.notify(0,builder.build());



//                        //creating an alert dialog
//                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                        builder.setTitle("Order successful");
//                        builder.setMessage("Your order has successfully done");
//                        Toast.makeText(MakeOrderActivity.this,"Booked !!",Toast.LENGTH_SHORT).show();
//
//                        //setting dismissing click
//                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });//End Ok dismiss click event
//                        AlertDialog alertDialog = builder.create();
//                        //showing dialog
//                        alertDialog.show();
                    }
                });

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


//    ================================================================================================
//    ================================================================================================




//    ================================================================================================
//    ================================================================================================


        cat_plumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creating bottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MakeOrderActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                bottomSheetDialog.setCanceledOnTouchOutside(false);

                //Initializing and Assigning variables
                option1checked = bottomSheetDialog.findViewById(R.id.option1);
                option2checked = bottomSheetDialog.findViewById(R.id.option2);
                option3checked = bottomSheetDialog.findViewById(R.id.option3);
                AppCompatButton continueBtn = bottomSheetDialog.findViewById(R.id.continue_btn);
                AppCompatButton cancelBtn = bottomSheetDialog.findViewById(R.id.cancel_btn);


                RoundedImageView cat_image = bottomSheetDialog.findViewById(R.id.cat_image);
                TextView cat_title = bottomSheetDialog.findViewById(R.id.cat_title);


                //setting current data info
                cat_image.setImageResource(R.drawable.plumber);
                cat_title.setText("Plumber");


                //cat_image.setImageDrawable(R.layout.activity_make_order);

                //set on click continue put in firebase

                continueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String option1 = option1checked.getText().toString();
                        String option2 = option2checked.getText().toString();
                        String option3 = option3checked.getText().toString();
                        String id = UUID.randomUUID().toString();

                        saveToFirestore(option1,option2,option3,id);




                    }
                });

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


//    ================================================================================================
//    ================================================================================================


        cat_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creating bottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MakeOrderActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                bottomSheetDialog.setCanceledOnTouchOutside(false);

                //Initializing and Assigning variables
                option1checked = bottomSheetDialog.findViewById(R.id.option1);
                option2checked = bottomSheetDialog.findViewById(R.id.option2);
                option3checked = bottomSheetDialog.findViewById(R.id.option3);
                AppCompatButton continueBtn = bottomSheetDialog.findViewById(R.id.continue_btn);
                AppCompatButton cancelBtn = bottomSheetDialog.findViewById(R.id.cancel_btn);


                RoundedImageView cat_image = bottomSheetDialog.findViewById(R.id.cat_image);
                TextView cat_title = bottomSheetDialog.findViewById(R.id.cat_title);


                //setting current data info
                cat_image.setImageResource(R.drawable.electrician);
                cat_title.setText("Electrician");


                //cat_image.setImageDrawable(R.layout.activity_make_order);

                //set on click continue put in firebase

                continueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String option1 = option1checked.getText().toString();
                        String option2 = option2checked.getText().toString();
                        String option3 = option3checked.getText().toString();
                        String id = UUID.randomUUID().toString();

                        saveToFirestore(option1,option2,option3,id);

                    }
                });

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


    }

//    ================================================================================================
//    ================================================================================================
//    ================================================================================================


    private void saveToFirestore(String option1, String option2, String option3, String id) {

        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        if (!(option1checked.isChecked() || option2checked.isChecked() || option3checked.isChecked())){
            Toast.makeText(MakeOrderActivity.this,"Please select the needed service",Toast.LENGTH_SHORT).show();
            return;
        }

        if (valid){

            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseUser user = fAuth.getCurrentUser();
            DocumentReference df = fStore.collection("Orders").document(user.getUid());
            Map<String,Object> orderInfo = new HashMap<>();

            //putting the checked data in fireStore
            if (option1checked.isChecked()){
                orderInfo.put("Order",option1);
                orderInfo.put("Service",cat_title);
                orderInfo.put("UserID",userID);
                orderInfo.put("Time",currentDate);


            }

            if (option2checked.isChecked()){
                orderInfo.put("Order",option2);
                orderInfo.put("Service",cat_title);
                orderInfo.put("UserID",userID);
                orderInfo.put("Time",currentDate);
            }

            if (option3checked.isChecked()){
                orderInfo.put("Order",option3);
                orderInfo.put("Service",cat_title);
                orderInfo.put("UserID",userID);
                orderInfo.put("Time",currentDate);


            }

            df.set(orderInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(MakeOrderActivity.this, "Order done!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),MakeOrderActivity.class));

                }
            });

        }


    }
}