package ru.sicampus.bootcamp2025.ui.registration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.sign.CreateUserUseCase;
import ru.sicampus.bootcamp2025.domain.sign.IsUserExistUseCase;

public class RegistrationViewModel extends ViewModel {

    private final MutableLiveData<String> mutableErrorLiveData = new MutableLiveData<>();
    public final LiveData<String> errorLiveData = mutableErrorLiveData;

    private final MutableLiveData<Void> mutableOpenAuthLiveData = new MutableLiveData<>();
    public final LiveData<Void> openAuthLiveData = mutableOpenAuthLiveData;

    /* UseCases */
    private final CreateUserUseCase createUserUseCase = new CreateUserUseCase(
            UserRepositoryImpl.getInstance()
    );

    private final IsUserExistUseCase isUserExistUseCase = new IsUserExistUseCase(
            UserRepositoryImpl.getInstance()
    );

    /* UseCases */

    @Nullable
    private String email = null;

    @Nullable
    private String password = null;

    @Nullable
    private String name = null;

    @Nullable
    private String nickname = null;

    public void changeLogin(@NonNull String nickname) {
        this.nickname = nickname;
    }

    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public void changeName(@NonNull String name) {
        this.name = name;
    }

    public void changeEmail(@NonNull String email) {
        this.email = email;
    }

    public void authenticateUser() {

        final String currentLogin = nickname;

        final String currentPassword = password;

        final String currentName = name;

        final String currentEmail = email;

        if (currentLogin == null || currentLogin.isEmpty()) {
            mutableErrorLiveData.postValue("Nickname cannot be null");
            return;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            mutableErrorLiveData.postValue("Password cannot be null");
            return;
        }

        isUserExistUseCase.execute(currentLogin, status -> {
            if (status.getValue() == null || status.getErrors() != null) {
                mutableErrorLiveData.postValue("Something went wrong. Try later.");
                return;
            }
            if (status.getValue()) {
                mutableErrorLiveData.postValue("User already exists. Please authenticate.");
            } else {
                createAccount(currentLogin, currentName, currentEmail, currentPassword);
            }
        });
    }

    private void createAccount(String currentLogin,
                               String currentName,
                               String currentEmail,
                               String currentPassword) {
        createUserUseCase.execute(
                currentLogin,
                currentName,
                currentEmail,
                currentPassword,
                status -> {
            if (status.getStatusCode() == 201 && status.getErrors() == null) {
                mutableOpenAuthLiveData.postValue(null);
            } else {
                mutableErrorLiveData.postValue("Something wrong");
            }
        });
    }

}
