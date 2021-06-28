package com.example.finalprojectassistance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import soup.neumorphism.NeumorphButton;

public class MakeComplaintsActivity extends AppCompatActivity {

    EditText titleC,contentC;
    AppCompatButton submitBbtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ProgressBar progressDialog;
    @ServerTimestamp
    Date timeStamp;

    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_complaints);

        titleC = findViewById(R.id.complaint_title);
        contentC = findViewById(R.id.complaint_content);

        submitBbtn = findViewById(R.id.submitBtn);

       progressDialog = findViewById(R.id.progress_bar);








        //setting click listener
        submitBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title =  titleC.getText().toString();
                String content =  contentC.getText().toString();

                String id = UUID.randomUUID().toString();

                saveToFirestore(title,content);



            }
        });






    }

    private void saveToFirestore(String title, String content) {

        if (title.matches("") && content.matches("")) {
            Toast.makeText(this, "Please Fill Fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else{



            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();



            Map<String,Object> reportInfo = new HashMap<>();


            //putting the checked data in fireStore
            reportInfo.put("Title",title);
            reportInfo.put("ReportContent",content);

            reportInfo.put("UserId",userID);
            reportInfo.put("Date",currentDate);


            //DocumentReference df = fStore.collection("Reports").document(userID);

            fStore = FirebaseFirestore.getInstance();
            fStore.collection("Reports").document(userID).set(reportInfo)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {

                            Dialog dialog = new Dialog(MakeComplaintsActivity.this);
                            //dialog.setContentView();
                            dialog.show();
                           // Toast.makeText(MakeComplaintsActivity.this,"Report submited",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(MakeComplaintsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();


                }
            });




        }


    }
}