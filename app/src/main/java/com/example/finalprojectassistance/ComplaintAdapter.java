package com.example.finalprojectassistance;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;



    Context context;
    ArrayList<ComplaintData> complaintDataArrayList;

    public ComplaintAdapter(Context context, ArrayList<ComplaintData> complaintDataArrayList) {
        this.context = context;
        this.complaintDataArrayList = complaintDataArrayList;

    }

    @NonNull
    @NotNull
    @Override
    public ComplaintAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_complaints,parent,false);




        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ComplaintAdapter.MyViewHolder holder, int position) {

        ComplaintData complaintData = complaintDataArrayList.get(position);

        fStore  = FirebaseFirestore.getInstance();


        holder.title.setText(complaintData.Title);
        holder.content.setText(complaintData.ReportContent);
        //holder.name.setText(complaintData.FullName);

        DocumentReference cREf= FirebaseFirestore.getInstance().collection("Users").document(complaintData.UserID);

        cREf.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {


                holder.name.setText(value.get("FullName").toString());
                holder.address.setText(value.get("Address").toString());
                holder.phone.setText(value.get("PhoneNumber").toString());

            }
        });


        holder.date.setText(complaintData.Time);
        holder.address.setText(complaintData.Address);


                //,,SubjectName

    }

    @Override
    public int getItemCount() {
        return complaintDataArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,content,name,date,address,phone;




        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.complaintTitle);
            content = itemView.findViewById(R.id.complaint_content);
            name = itemView.findViewById(R.id.complainerName);
            date = itemView.findViewById(R.id.complaintdate);
            address = itemView.findViewById(R.id.complainerAddress);
            phone = itemView.findViewById(R.id.complainerPhone);

            //========================


        }


    }






}
