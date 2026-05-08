package com.khedma.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khedma.app.R;
import com.khedma.app.models.Demand;

import java.util.ArrayList;
import java.util.List;

public class DemandAdapter extends RecyclerView.Adapter<DemandAdapter.DemandViewHolder> {
    private List<Demand> demands = new ArrayList<>();

    public void submitList(List<Demand> items) {
        demands = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DemandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demand, parent, false);
        return new DemandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DemandViewHolder holder, int position) {
        holder.bind(demands.get(position));
    }

    @Override
    public int getItemCount() {
        return demands.size();
    }

    static class DemandViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView status;

        DemandViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_demand_title);
            status = itemView.findViewById(R.id.tv_demand_status);
        }

        void bind(Demand demand) {
            title.setText(demand.getTitle());
            status.setText(demand.getStatus());
        }
    }
}
