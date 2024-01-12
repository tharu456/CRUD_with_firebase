package com.example.crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder>{
    Context context;
    ArrayList<User> arrayList;
    OnItemClickListener onItemClickListener;

    public UserAdapter(Context context,ArrayList<User>arrayList){
        this.context=context;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_list_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(MessageFormat.format("{0}{1}",arrayList.get(position).getFirstname(),arrayList.get(position).getLastname()));
        holder.phone.setText(arrayList.get(position).getPhone());
        holder.bio.setText(arrayList.get(position).getBio());
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView name,phone,bio;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.list_item_name);
            phone=itemView.findViewById(R.id.list_item_phone);
            bio=itemView.findViewById(R.id.list_item_bio);

        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(User user);
    }
}
