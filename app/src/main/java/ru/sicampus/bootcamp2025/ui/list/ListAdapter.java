package ru.sicampus.bootcamp2025.ui.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.databinding.ItemVolunteerBinding;
import ru.sicampus.bootcamp2025.domain.entites.ItemUserEntity;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    @NonNull
    private final Consumer<Integer> onItemClick;

    private final List<ItemUserEntity> data = new ArrayList<>();

    public ListAdapter(@NonNull Consumer<Integer> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemVolunteerBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                ).getRoot()
        );

        /*return new ViewHolder(
                ItemVolunteerBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );*/
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
        //Log.d("ta)
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemVolunteerBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemVolunteerBinding.bind(itemView);
        }
        /*public ViewHolder(@NonNull ItemVolunteerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }*/

        public void bind(ItemUserEntity item) {
            binding.lastName.setText(item.getLastName());
            binding.firstName.setText(item.getFirstName());
            binding.getRoot().setOnClickListener(v -> {
                onItemClick.accept(item.getId());
            });
        }
    }
}
