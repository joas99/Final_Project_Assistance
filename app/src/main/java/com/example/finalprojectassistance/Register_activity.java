package com.example.finalprojectassistance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register_activity extends AppCompatActivity {

    EditText fullName, email, password, phone, address;
    AppCompatButton registerBtn;
    TextView goToLogin;
    boolean valid = true;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing the variables

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName =  findViewById(R.id.registerlName);
        email =  findViewById(R.id.registerEmail);
        password =  findViewById(R.id.registerPassword);
        phone =  findViewById(R.id.registerPhone);
        address =  findViewById(R.id.registerAddress);
        registerBtn =  findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.goToLogin);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(fullName);
                checkField(email);
                checkField(password);
                checkField(phone);


                //checking
                if (valid){
                    //starting the user registration
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(Register_activity.this,"Account created", Toast.LENGTH_SHORT).show();

                            DocumentReference df = fStore.collection("Users").document(user.getUid());

                            //Storing the Data
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("FullName",fullName.getText().toString());
                            userInfo.put("UserEmail",email.getText().toString());
                            userInfo.put("PhoneNumber",phone.getText().toString());
                            userInfo.put("Address",address.getText().toString());


                            //specifying user role
                            userInfo.put("Client","1");
                            df.set(userInfo);


                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {

                            Toast.makeText(Register_activity.this,"Failed creating account",Toast.LENGTH_SHORT).show();


                        }
                    });
                }
            }
        });



    }

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