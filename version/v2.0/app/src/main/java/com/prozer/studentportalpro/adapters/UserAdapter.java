package com.prozer.studentportalpro.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.activities.UserDetailsActivity;
import com.prozer.studentportalpro.models.USER;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter
        extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<USER> userList;
    private List<USER> fullList;

    public UserAdapter(List<USER> userList) {
        this.userList = new ArrayList<>(userList);
        this.fullList = new ArrayList<>(userList);
    }

    public void filter(String text) {

        userList.clear();

        if (text.isEmpty()) {

            userList.addAll(fullList);

        } else {

            text = text.toLowerCase();

            for (USER user : fullList) {

                if (user.getName()
                        .toLowerCase()
                        .contains(text)) {

                    userList.add(user);
                }
            }
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_user,
                        parent,
                        false
                );

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull UserViewHolder holder,
            int position) {

        USER user = userList.get(position);

        holder.txtName.setText(user.getName());
        holder.txtEmail.setText(user.getEmail());
        holder.txtPhone.setText(user.getPhone());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    v.getContext(),
                    UserDetailsActivity.class
            );

            intent.putExtra("name", user.getName());
            intent.putExtra("email", user.getEmail());
            intent.putExtra("phone", user.getPhone());
            intent.putExtra("website", user.getWebsite());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtEmail;
        TextView txtPhone;

        public UserViewHolder(
                @NonNull View itemView) {

            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtPhone = itemView.findViewById(R.id.txtPhone);
        }
    }
}