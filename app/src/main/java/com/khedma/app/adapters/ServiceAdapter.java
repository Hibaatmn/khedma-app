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
import com.khedma.app.models.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceAdapter extends ListAdapter<Service, ServiceAdapter.ServiceViewHolder> {
    public ServiceAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Service> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Service oldItem, @NonNull Service newItem) {
            if (oldItem.getId() == null || newItem.getId() == null) {
                return oldItem == newItem;
            }
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Service oldItem, @NonNull Service newItem) {
            return String.valueOf(oldItem.getTitle()).equals(String.valueOf(newItem.getTitle()))
                    && String.valueOf(oldItem.getDescription()).equals(String.valueOf(newItem.getDescription()));
        }
    };

    public void submitList(List<Service> items) {
        super.submitList(items == null ? Collections.emptyList() : new ArrayList<>(items));
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;

        ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_service_title);
            description = itemView.findViewById(R.id.tv_service_description);
        }

        void bind(Service service) {
            title.setText(service.getTitle());
            description.setText(service.getDescription());
        }
    }
}
