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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class WorkerAdapter extends RecyclerView.Adapter <WorkerAdapter.MyViewHolder>{

    //declaring var
    Context context;
    ArrayList<WorkerData> workerDataArrayList;

    private static final int REQUEST_CALL = 1;
    private TextView phoneNumber;
    NeumorphCardView callbtn;

    public WorkerAdapter(Context context, ArrayList<WorkerData> workerDataArrayList) {
        this.context = context;
        this.workerDataArrayList = workerDataArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public WorkerAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_workers,parent,false);


        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorkerAdapter.MyViewHolder holder, int position) {

        WorkerData workerData = workerDataArrayList.get(position);
        //required element
        holder.FullName.setText(workerData.FullName);
        holder.PhoneNumber.setText(workerData.Phone);
        holder.Address.setText(workerData.Address);
        holder.Specility.setText(workerData.Speciality);




    }

    @Override
    public int getItemCount() {
        return workerDataArrayList.size();
    }

        //
        //Creating an inner class
        public static class MyViewHolder extends RecyclerView.ViewHolder {

            //Declaring var
            TextView FullName, PhoneNumber, Address, Specility, phoneNumberCall;
            NeumorphCardView callBtn;

            public MyViewHolder(@NonNull @NotNull View itemView) {
                super(itemView);


                //Initializing var
                FullName = itemView.findViewById(R.id.workerNameItem);
                PhoneNumber = itemView.findViewById(R.id.workerPhone_item);
                Address = itemView.findViewById(R.id.workerAddressItem);
                Specility = itemView.findViewById(R.id.workerSpecialityItem);
                callBtn = itemView.findViewById(R.id.callPhoneWorkerItem);


            }

        }



}

