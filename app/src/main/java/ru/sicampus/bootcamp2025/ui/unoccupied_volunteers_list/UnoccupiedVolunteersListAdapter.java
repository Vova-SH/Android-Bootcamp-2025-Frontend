package ru.sicampus.bootcamp2025.ui.unoccupied_volunteers_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.sicampus.bootcamp2025.databinding.ItemVolunteerBinding;
import ru.sicampus.bootcamp2025.domain.entities.ItemVolunteerEntity;

public class UnoccupiedVolunteersListAdapter extends RecyclerView.Adapter<UnoccupiedVolunteersListAdapter.ViewHolder> {

    private final List<ItemVolunteerEntity> data = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UnoccupiedVolunteersListAdapter.ViewHolder(
                ItemVolunteerBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<ItemVolunteerEntity> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemVolunteerBinding binding;

        public ViewHolder(@NonNull ItemVolunteerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemVolunteerEntity item) {
            binding.name.setText(item.getName());
            binding.email.setText(item.getEmail());
            if (item.getPhotoUrl() != null) {
                Picasso.get().load(item.getPhotoUrl()).into(binding.photo);
            }
        }
    }
}
