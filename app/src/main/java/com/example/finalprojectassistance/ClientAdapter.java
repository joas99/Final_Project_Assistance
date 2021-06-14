package com.example.finalprojectassistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter <ClientAdapter.MyViewHolder> {

    //declaring var
    Context context;
    ArrayList<ClientData> clientDataArrayList;

    public ClientAdapter(Context context, ArrayList<ClientData> clientDataArrayList) {
        this.context = context;
        this.clientDataArrayList = clientDataArrayList;
    }//End constructor

    @NonNull
    @NotNull
    @Override
    public ClientAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_clients,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ClientAdapter.MyViewHolder holder, int position) {

        ClientData clientData = clientDataArrayList.get(position);
        //required element
        holder.FullName.setText(clientData.FullName);
        holder.UserEmail.setText(clientData.UserEmail);
        holder.PhoneNumber.setText(clientData.PhoneNumber);
        holder.Address.setText(clientData.Address);


    }

    @Override
    public int getItemCount() {
        return clientDataArrayList.size();
    }

    //Creating an inner class
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //Declaring var
        TextView FullName,UserEmail,PhoneNumber,Address;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            //Initializing var
            FullName = itemView.findViewById(R.id.clientName);
            UserEmail = itemView.findViewById(R.id.clientMail);
            PhoneNumber = itemView.findViewById(R.id.clientPhone);
            Address = itemView.findViewById(R.id.clientAddress);


        }
    }//end viewHolder  inner class
}
