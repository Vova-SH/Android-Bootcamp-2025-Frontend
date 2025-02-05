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

    private final MutableLiveData<Void> mutableOpenCentersListLiveData = new MutableLiveData<>();
    public final LiveData<Void> openCentersListLiveData = mutableOpenCentersListLiveData;

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


    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public void changeLogin(@NonNull String email) {
        this.email = email;
    }

    public void authenticateUser() {
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

        isUserExistUseCase.execute(currentLogin, status -> {
            if (status.getValue() == null || status.getErrors() != null) {
                mutableErrorLiveData.postValue("Something went wrong. Try later.");
                return;
            }
            if (!status.getValue()) {
                mutableErrorLiveData.postValue("User does not exist. Please register.");
            } else {
                loginUser(currentLogin, currentPassword);
            }
        });
    }

    private void loginUser(@NonNull final String currentLogin, @NonNull final String currentPassword) {
        loginUserUseCase.execute(currentLogin, currentPassword, status -> {
            if (status.getStatusCode() == 200 && status.getErrors() == null) {
                mutableOpenCentersListLiveData.postValue(null);
            } else {
                mutableErrorLiveData.postValue("Something wrong");
            }
        });
    }

}
