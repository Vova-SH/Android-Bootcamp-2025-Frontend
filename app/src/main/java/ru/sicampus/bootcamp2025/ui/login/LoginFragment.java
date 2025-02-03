package com.example.myapplication.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.ui.main.IventsActivity;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.core.SettingConstants;
import com.example.myapplication.utils.TextChangedListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends Fragment {
    private static final String testEmail = "test@gmail.com";
    private static final String testPassword = "kloun";

    private TextInputEditText email;
    private TextInputEditText password;

    private TextInputLayout email_layout;
    private TextInputLayout password_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        password_layout = view.findViewById(R.id.password_layout);
        email_layout = view.findViewById(R.id.email_layout);

        Button loginButton = view.findViewById(R.id.loginButton);
        TextView singupText = view.findViewById(R.id.singupText);


        email.addTextChangedListener(new TextChangedListener<>(email) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                listenerEmailEditText(target, s);
            }
        });

        password.addTextChangedListener(new TextChangedListener<>(password) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                listenerPasswordEditText(target, s);
            }
        });

        loginButton.setOnClickListener(this::onClickListenerLoginButton);
        //singupText.setOnClickListener(this::onClickListenerSingupText);
    }

    private void listenerEmailEditText(TextInputEditText target, Editable s) {
        if (s.toString().isEmpty()) {
            email_layout.setError("Обязательное поле");
        } else if (!isEmailValid(s.toString())) {
            email_layout.setError("Неверный формат");
        } else {
            email_layout.setErrorEnabled(false);
        }
    }

    private void listenerPasswordEditText(TextInputEditText target, Editable s) {
        if (s.toString().isEmpty()) {
            password_layout.setError("Обязательное поле");
        } else {
            password_layout.setErrorEnabled(false);
        }
    }

    private void onClickListenerLoginButton(View view) {
        // Отправка запроса на сервер
        // Получение и обработка ответа
        // Проверка ответа и переход на главную активность

        String emailString = String.valueOf(email.getText());
        String passwordString = String.valueOf(password.getText());
        SharedPreferences settings = view.getContext().getSharedPreferences(
                SettingConstants.PREFS_FILE, Context.MODE_PRIVATE
        );

        if (emailString.equals(testEmail) && passwordString.equals(testPassword)) {
            settings.edit().putString(SettingConstants.PREF_TOKEN, "token").apply();
            startActivity(new Intent(getActivity(), IventsActivity.class));
            requireActivity().finish();
        } else {
            Snackbar.make(view, "Некорректный email или пароль!", Snackbar.LENGTH_LONG).show();
        }
    }

   /* private void onClickListenerSingupText(View view) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, RegisterFragment.class, null)
                .commit();
    }*/

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}