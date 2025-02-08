package ru.sicampus.bootcamp2025.ui.center;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.databinding.ItemVolunteerBinding;
import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;

public class CenterAdapter extends RecyclerView.Adapter<CenterAdapter.ViewHolder> {

    private final List<ItemUserEntity> data = new ArrayList<>();

    @NonNull
    private final Consumer<String> onItemClick;

    public CenterAdapter(@NonNull Consumer<String> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CenterAdapter.ViewHolder(
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

    public void updateData(List<ItemUserEntity> newData) {
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

        public void bind(ItemUserEntity item) {
            binding.name.setText(item.getName());
            binding.email.setText(item.getEmail());
            if (item.getPhotoUrl() != null) {
                Picasso.get().load(item.getPhotoUrl()).into(binding.photo);
            }
            binding.getRoot().setOnClickListener(v -> {
                onItemClick.accept(item.getId());
            });
        }

    }
}
