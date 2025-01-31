package com.example.myapplication.ui.login;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.utils.TextChangedListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegisterFragment extends Fragment {
    private TextInputEditText name;
    private TextInputLayout nameLayout;
    private TextInputEditText surname;
    private TextInputLayout surnameLayout;
    private TextInputLayout dateBirthLayout;
    private TextInputEditText dateBirth;
    private TextInputLayout cityLayout;
    private AutoCompleteTextView cityAutoCompleteTextView;
    private TextInputLayout emailLayout;
    private TextInputEditText email;
    private TextInputLayout telephoneLayout;
    private TextInputEditText telephone;
    private TextInputLayout passwordLayout;
    private TextInputEditText password;
    private TextInputLayout passwordConfirmLayout;
    private TextInputEditText passwordConfirm;
    private Button registerButton;
    private TextView loginText;

    private boolean errorFlag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        name = view.findViewById(R.id.name);
        nameLayout = view.findViewById(R.id.name_layout);
        surname = view.findViewById(R.id.surname);
        surnameLayout = view.findViewById(R.id.surname_layout);
        dateBirthLayout = view.findViewById(R.id.date_birth_layout);
        dateBirth = view.findViewById(R.id.date_birth);
        cityLayout = view.findViewById(R.id.city_layout);
        cityAutoCompleteTextView = view.findViewById(R.id.city_autoCompleteTextView);
        emailLayout = view.findViewById(R.id.email_layout);
        email = view.findViewById(R.id.email);
        telephoneLayout = view.findViewById(R.id.telephone_layout);
        telephone = view.findViewById(R.id.telephone);
        passwordLayout = view.findViewById(R.id.password_layout);
        password = view.findViewById(R.id.password);
        passwordConfirmLayout = view.findViewById(R.id.password_confirm_layout);
        passwordConfirm = view.findViewById(R.id.password_confirm);
        registerButton = view.findViewById(R.id.register_button);
        loginText = view.findViewById(R.id.login_text);


        dateBirthLayout.setEndIconOnClickListener(this::onClickListenerDateBirthLayout);
        dateBirthLayout.setErrorIconOnClickListener(this::onClickListenerDateBirthLayout);

        setCitiesAdapter(view);

        name.addTextChangedListener(new TextChangedListener<>(name) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                standardListener(nameLayout, target, s);
            }
        });

        surname.addTextChangedListener(new TextChangedListener<>(surname) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                standardListener(surnameLayout, target, s);
            }
        });

        cityAutoCompleteTextView.addTextChangedListener(new TextChangedListener<>(cityAutoCompleteTextView) {
            @Override
            public void onTextChanged(AutoCompleteTextView target, Editable s) {
                standardListener(cityLayout, target, s);
            }
        });

        email.addTextChangedListener(new TextChangedListener<>(email) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                listenerEmailEditText(target, s);
            }
        });
        telephone.addTextChangedListener(new TextChangedListener<>(telephone) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                listenerTelephoneEditText(target, s);
            }
        });

        password.addTextChangedListener(new TextChangedListener<>(password) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                standardListener(passwordLayout, target, s);
            }
        });
        passwordConfirm.addTextChangedListener(new TextChangedListener<>(passwordConfirm) {
            @Override
            public void onTextChanged(TextInputEditText target, Editable s) {
                standardListener(passwordConfirmLayout, target, s);
            }
        });

        registerButton.setOnClickListener(this::registerButtonListener);
        loginText.setOnClickListener(this::onClickListenerLoginText);
    }

    private void registerButtonListener(View view) {
        /*
            Основной поток:
            - Ввод имени, фамилии, даты рождения, email, телефона, пароля, повторного пароля и ввод/выбор города;
            - Отправка POST запроса на сервер (api/v1/auth/register)
            - Получение и сохранение в локальное хранилище токена
            - Переход на главный экран (Важно, чтобы при нажатии кнопки назад экран авторизации не показывался повторно)

            Альтернативный поток:
            - Если при нажатии кнопки "Создать аккаунт" любое из полей пустое, то мы подкрашиваем это поле
            светло-красным цветом. Красный цвет должен исчезать при вводе.
            - Если сервер возвращает ответ 409, то выводим Snackbar "Пользователь с такими данными уже существует!"
            - Если первое поле с паролем заполнено, то второе поле должно сравнивать пароли по мере ввода
            текста. Если они не совпадают подсвечивать поле с повторным паролем светло-красным цветом.
            - Если с сервера не пришел ответ, отобразить информацию об ошибке сервера/интернета.
        */
        errorFlag = false;

        if (String.valueOf(name.getText()).isEmpty()) emptyErrorWidget(nameLayout);
        if (String.valueOf(surname.getText()).isEmpty()) emptyErrorWidget(surnameLayout);
        if (String.valueOf(dateBirth.getText()).isEmpty()) emptyErrorWidget(dateBirthLayout);
        if (String.valueOf(cityAutoCompleteTextView.getText()).isEmpty()) emptyErrorWidget(cityLayout);
        if (String.valueOf(email.getText()).isEmpty()) emptyErrorWidget(emailLayout);
        if (String.valueOf(telephone.getText()).isEmpty()) emptyErrorWidget(telephoneLayout);
        if (String.valueOf(password.getText()).isEmpty()) emptyErrorWidget(passwordLayout);
        if (String.valueOf(passwordConfirm.getText()).isEmpty()) emptyErrorWidget(passwordConfirmLayout);

        if (errorFlag) return;
        if (!String.valueOf(password.getText()).equals(String.valueOf(passwordConfirm.getText()))) {
            Snackbar.make(view, "Пароли не совпадают!", Snackbar.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(getContext(), "Регистрация", Toast.LENGTH_SHORT).show();
    }

    public void emptyErrorWidget(TextInputLayout textInputLayout) {
        textInputLayout.setError("Обязательное поле");
        errorFlag = true;
    }

    public void standardListener(TextInputLayout textInputLayout, TextInputEditText target, Editable s) {
        if (s.toString().isEmpty()) textInputLayout.setError("Обязательное поле");
        else textInputLayout.setErrorEnabled(false);
    }

    public void standardListener(TextInputLayout textInputLayout, AutoCompleteTextView target, Editable s) {
        if (s.toString().isEmpty()) textInputLayout.setError("Обязательное поле");
        else textInputLayout.setErrorEnabled(false);
    }

    private void listenerEmailEditText(TextInputEditText target, Editable s) {
        if (s.toString().isEmpty()) {
            emailLayout.setError("Обязательное поле");
        } else if (!isEmailValid(s.toString())) {
            emailLayout.setError("Неверный формат");
        } else {
            emailLayout.setErrorEnabled(false);
        }
    }

    private void listenerTelephoneEditText(TextInputEditText target, Editable s) {
        if (s.toString().isEmpty()) {
            telephoneLayout.setError("Обязательное поле");
        } else if (!isTelephoneValid(s.toString())) {
            telephoneLayout.setError("Неверный формат");
        } else {
            telephoneLayout.setErrorEnabled(false);
        }
    }

    private void onClickListenerDateBirthLayout(View view) {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), this::setDateBirthText, year, month, day);
        datePickerDialog.show();
    }

    @SuppressLint("DefaultLocale")  // Это, чтобы IDEA не ругалась
    private void setDateBirthText(View view, int year, int month, int day) {
        if (month < 10) {
            dateBirth.setText(String.format("%d.0%d.%d", day, month + 1, year));
        } else {
            dateBirth.setText(String.format("%d.%d.%d", day, month + 1, year));
        }

        dateBirthLayout.setErrorEnabled(false);
    }

    private void setCitiesAdapter(View view) {
        String[] cities = getResources().getStringArray((R.array.cities));
        cityAutoCompleteTextView.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.dropdown_item, cities));
    }

    private void onClickListenerLoginText(View view) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, LoginFragment.class, null)
                .commit();
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isTelephoneValid(String telephone) {
        return Patterns.PHONE.matcher(telephone).matches();
    }
}