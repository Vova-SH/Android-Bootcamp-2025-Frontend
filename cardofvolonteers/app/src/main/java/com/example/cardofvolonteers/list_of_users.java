package com.example.cardofvolonteers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cardofvolonteers.Model.User;
import com.example.cardofvolonteers.Model.UsersResponce;
import com.example.cardofvolonteers.adapter.UsersAdapter;
import com.example.cardofvolonteers.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class list_of_users extends Fragment {
    RecyclerView recyclerView;
    UsersAdapter adapter;
    List<User> userList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_of_user, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = recyclerView.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        Call<UsersResponce> call = RetrofitClient.getInstance().getApi().getUsers();
        call.enqueue(new Callback<UsersResponce>() {
            @Override
            public void onResponse(Call<UsersResponce> call, Response<UsersResponce> response) {
                userList = response.body().getUsers();
                adapter = new UsersAdapter(getActivity(), userList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UsersResponce> call, Throwable throwable) {

            }
        });

    }
}