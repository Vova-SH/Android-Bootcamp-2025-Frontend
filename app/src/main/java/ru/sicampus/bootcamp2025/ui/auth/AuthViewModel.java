package ru.sicampus.bootcamp2025.ui.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.sign.IsUserExistUseCase;
import ru.sicampus.bootcamp2025.domain.sign.LoginUserUseCase;

public class AuthViewModel extends ViewModel {

    private final MutableLiveData<String> mutableErrorLiveData = new MutableLiveData<>();
    public final LiveData<String> errorLiveData = mutableErrorLiveData;

    private final MutableLiveData<Void> mutableOpenProfileLiveData = new MutableLiveData<>();
    public final LiveData<Void> openProfileLiveData = mutableOpenProfileLiveData;

    private final MutableLiveData<Void> mutableOpenSignLiveData = new MutableLiveData<>();
    public final LiveData<Void> openSignLiveData = mutableOpenSignLiveData;

    /* UseCases */
    private final IsUserExistUseCase isUserExistUseCase = new IsUserExistUseCase(
            UserRepositoryImpl.getInstance()
    );

    private final LoginUserUseCase loginUserUseCase = new LoginUserUseCase(
            UserRepositoryImpl.getInstance()
    );
    /* UseCases */

    @Nullable
    private String email = null;

    @Nullable
    private String password = null;

    private boolean userCheckCompleted = false;

    private boolean isNewAccount = false;

    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public void changeLogin(@NonNull String email) {
        this.email = email;
    }

    private void checkUserExist() {
        final String currentLogin = email;
        if (currentLogin == null || currentLogin.isEmpty()) {
            mutableErrorLiveData.postValue("Email cannot be null");
            return;
        }
        isUserExistUseCase.execute(currentLogin, status -> {
            if (status.getValue() == null || status.getErrors() != null) {
                mutableErrorLiveData.postValue("Something wrong. Try later");
                return;
            }
            userCheckCompleted = true;
            isNewAccount = !status.getValue();
            if(isNewAccount) {
                mutableErrorLiveData.postValue("Account already exists. Try to login");
            } else {
                login();
            }
        });
    }




    public void login() {
        final String currentLogin = email;
        final String currentPassword = password;

        if (currentLogin == null || currentLogin.isEmpty()) {
            mutableErrorLiveData.postValue("Login cannot be null");
            return;
        }

        if (currentPassword == null || currentPassword.isEmpty()) {
            mutableErrorLiveData.postValue("Password cannot be null");
            return;
        }

        loginUserUseCase.execute(currentLogin, currentPassword, status -> {
            if (status.getStatusCode() == 200 && status.getErrors() == null) {
                mutableOpenProfileLiveData.postValue(null);
            } else {
                mutableErrorLiveData.postValue("Login failed. Please try again.");
            }
        });
    }
}
