package com.example.finalprojectassistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdminNotificationAdapter extends RecyclerView.Adapter<AdminNotificationAdapter.MyViewHolder>{

    Context context;
    ArrayList<AdminNotificationData> adminNotificationDataArrayList;
    FirebaseFirestore fStore;

    public AdminNotificationAdapter(Context context, ArrayList<AdminNotificationData> adminNotificationDataArrayList) {
        this.context = context;
        this.adminNotificationDataArrayList = adminNotificationDataArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public AdminNotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdminNotificationAdapter.MyViewHolder holder, int position) {

        AdminNotificationData adminNotificationData = adminNotificationDataArrayList.get(position);
        //Fetching user Details
        fStore  = FirebaseFirestore.getInstance();

        DocumentReference cREf= FirebaseFirestore.getInstance().collection("Users").document(adminNotificationData.UserID);
        cREf.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {


                holder.notName.setText(value.get("FullName").toString());
                holder.notAddress.setText(value.get("Address").toString());

            }
        });

        fStore  = FirebaseFirestore.getInstance();
        holder.title.setText(adminNotificationData.Service);
        holder.notContent.setText(adminNotificationData.Order);
        holder.notDate.setText(adminNotificationData.Time);

    }

    @Override
    public int getItemCount() { return adminNotificationDataArrayList.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,notContent,notName,notAddress,notDate;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notificationTitle);
            notContent = itemView.findViewById(R.id.notificationContent);
            notName = itemView.findViewById(R.id.nameNotification);
            notAddress = itemView.findViewById(R.id.addressNotification);
            notAddress = itemView.findViewById(R.id.addressNotification);
            notDate = itemView.findViewById(R.id.dateNotification);

        }
    }
}
