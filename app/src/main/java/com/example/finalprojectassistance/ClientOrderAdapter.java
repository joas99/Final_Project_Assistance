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

public class ClientOrderAdapter extends RecyclerView.Adapter<ClientOrderAdapter.MyViewHolder> {

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;



    Context context;
    ArrayList<ClientOrderData> clientOrderDataArrayList;


    public interface OrderCall{

        void makePhoneCall(ClientOrderData clientOrderData);
    }

    public ClientOrderAdapter(Context context, ArrayList<ClientOrderData> clientOrderDataArrayList) {
        this.context = context;
        this.clientOrderDataArrayList = clientOrderDataArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ClientOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_orders,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ClientOrderAdapter.MyViewHolder holder, int position) {

        ClientOrderData clientOrderData = clientOrderDataArrayList.get(position);

        //Fetching user Details
        fStore  = FirebaseFirestore.getInstance();

        DocumentReference cREf= FirebaseFirestore.getInstance().collection("Users").document(clientOrderData.UserID);

        cREf.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {





                holder.clientname.setText(value.get("FullName").toString());
                holder.clientaddress.setText(value.get("Address").toString());
                holder.clientphone.setText(value.get("PhoneNumber").toString());

            }
        });


        fStore  = FirebaseFirestore.getInstance();
        holder.title.setText(clientOrderData.Service);
        holder.ordercontent.setText(clientOrderData.Order);
        holder.orderDate.setText(clientOrderData.Time);

//        holder.clientphone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() { return clientOrderDataArrayList.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,ordercontent,clientname,orderDate,clientaddress,clientphone;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.orderServiceClient);
            ordercontent = itemView.findViewById(R.id.orderContentClient);
            clientname = itemView.findViewById(R.id.orderClientName);
            orderDate = itemView.findViewById(R.id.orderDateClient);
            clientaddress = itemView.findViewById(R.id.orderclientAddress);
            clientphone = itemView.findViewById(R.id.orderClientPhone);

        }
    }
}
