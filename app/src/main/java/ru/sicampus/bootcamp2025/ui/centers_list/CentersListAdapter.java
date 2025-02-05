package ru.sicampus.bootcamp2025.ui.centers_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.sicampus.bootcamp2025.databinding.ItemVolunteerCenterBinding;
import ru.sicampus.bootcamp2025.domain.entities.ItemCenterEntity;

public class CentersListAdapter extends RecyclerView.Adapter<CentersListAdapter.ViewHolder> {

    private final List<ItemCenterEntity> data = new ArrayList<>();

    @NonNull
    @Override
    public CentersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemVolunteerCenterBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CentersListAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<ItemCenterEntity> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemVolunteerCenterBinding binding;

        public ViewHolder(@NonNull ItemVolunteerCenterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemCenterEntity item) {
            binding.centerName.setText(item.getCentre_name());
            binding.address.setText(item.getAddress());
        }
    }
}
