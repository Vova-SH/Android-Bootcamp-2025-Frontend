package ru.sicampus.bootcamp2025.ui.volunteers_centers_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.sicampus.bootcamp2025.databinding.ItemVolunteerCenterBinding;
import ru.sicampus.bootcamp2025.domain.entities.ItemVolunteerCenterEntity;

public class VolunteersCentersListAdapter extends RecyclerView.Adapter<VolunteersCentersListAdapter.ViewHolder> {

    private final List<ItemVolunteerCenterEntity> data = new ArrayList<>();

    @NonNull
    @Override
    public VolunteersCentersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemVolunteerCenterBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteersCentersListAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<ItemVolunteerCenterEntity> newData) {
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

        public void bind(ItemVolunteerCenterEntity item) {
            binding.centerName.setText(item.getCentre_name());
            binding.phone.setText(item.getPhone());
        }
    }
}
