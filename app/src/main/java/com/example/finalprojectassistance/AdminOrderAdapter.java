package com.example.finalprojectassistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.MyViewHolder> {


    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    //declaring var
    private static final int REQUEST_CALL = 1;
    private TextView phoneNumber;
    NeumorphCardView callBtn;

    Context context;
    ArrayList<AdminOrderData> adminOrderDataArrayList;

    public AdminOrderAdapter(Context context, ArrayList<AdminOrderData> adminOrderDataArrayList) {
        this.context = context;
        this.adminOrderDataArrayList = adminOrderDataArrayList;
    }




    @NonNull
    @NotNull
    @Override
    public AdminOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_admin,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdminOrderAdapter.MyViewHolder holder, int position) {

        AdminOrderData adminOrderData = adminOrderDataArrayList.get(position);

        //Fetching user Details
        fStore  = FirebaseFirestore.getInstance();



        //holder.name.setText(complaintData.FullName);

        DocumentReference cREf= FirebaseFirestore.getInstance().collection("Users").document(adminOrderData.UserID);

        cREf.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {


                holder.clientname.setText(value.get("FullName").toString());
                holder.clientaddress.setText(value.get("Address").toString());
                holder.clientphone.setText(value.get("PhoneNumber").toString());

            }
        });



        fStore  = FirebaseFirestore.getInstance();
        holder.title.setText(adminOrderData.Service);
        holder.ordercontent.setText(adminOrderData.Order);
        holder.orderDate.setText(adminOrderData.Time);
    }

    @Override
    public int getItemCount() { return adminOrderDataArrayList.size(); }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,ordercontent,clientname,orderDate,clientaddress,clientphone;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.orderServiceAdmin);
            ordercontent = itemView.findViewById(R.id.orderContentAdmin);
            clientname = itemView.findViewById(R.id.orderClientNameAdmin);
            orderDate = itemView.findViewById(R.id.orderDateClientAdmin);
            clientaddress = itemView.findViewById(R.id.orderclientAddressAdmin);
            clientphone = itemView.findViewById(R.id.orderClientPhoneAdmin);

        }
    }

}
