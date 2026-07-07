package com.prozer.studentportalpro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.models.CampusLocation;

import java.util.List;

public class CampusAdapter extends RecyclerView.Adapter<CampusAdapter.ViewHolder> {

    private final List<CampusLocation> list;

    public CampusAdapter(List<CampusLocation> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_campus, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        CampusLocation campus = list.get(position);

        holder.txtBuilding.setText(campus.getBuildingName());

        holder.txtLatitude.setText(
                "Latitude : " + campus.getLatitude());

        holder.txtLongitude.setText(
                "Longitude : " + campus.getLongitude());

        holder.txtDate.setText(
                "Captured : " + campus.getDateCaptured());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBuilding;
        TextView txtLatitude;
        TextView txtLongitude;
        TextView txtDate;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            txtBuilding = itemView.findViewById(R.id.txtBuilding);
            txtLatitude = itemView.findViewById(R.id.txtLatitude);
            txtLongitude = itemView.findViewById(R.id.txtLongitude);
            txtDate = itemView.findViewById(R.id.txtDate);

        }

    }

}