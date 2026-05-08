package com.khedma.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.khedma.app.R;
import com.khedma.app.models.Demand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DemandAdapter extends ListAdapter<Demand, DemandAdapter.DemandViewHolder> {
    public DemandAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Demand> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Demand oldItem, @NonNull Demand newItem) {
            if (oldItem.getId() == null || newItem.getId() == null) {
                return oldItem == newItem;
            }
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Demand oldItem, @NonNull Demand newItem) {
            return String.valueOf(oldItem.getTitle()).equals(String.valueOf(newItem.getTitle()))
                    && String.valueOf(oldItem.getStatus()).equals(String.valueOf(newItem.getStatus()));
        }
    };

    public void submitList(List<Demand> items) {
        super.submitList(items == null ? Collections.emptyList() : new ArrayList<>(items));
    }

    @NonNull
    @Override
    public DemandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demand, parent, false);
        return new DemandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DemandViewHolder holder, int position) {
        holder.bind(getItem(position));
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
