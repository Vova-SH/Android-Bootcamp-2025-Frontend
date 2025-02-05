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

    private boolean userCheckCompleted = false;

    public void changeLogin(@NonNull String email) {
        this.email = email;
    }

    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public void changeName(@NonNull String name) {
        this.name = name;
    }

    public void changeNickname(@NonNull String nickname) {
        this.nickname = nickname;
    }

    public void register() {
        final String currentLogin = email;
        final String currentPassword = password;
        final String currentName = name;
        final String currentNickname = nickname;

        if (currentLogin == null || currentLogin.isEmpty()) {
            mutableErrorLiveData.postValue("Login cannot be null");
            return;
        }

        if (currentPassword == null || currentPassword.isEmpty()) {
            mutableErrorLiveData.postValue("Password cannot be null");
            return;
        }

        if (currentName == null || currentName.isEmpty()) {
            mutableErrorLiveData.postValue("Name cannot be null");
            return;
        }

        if (currentNickname == null || currentNickname.isEmpty()) {
            mutableErrorLiveData.postValue("Nickname cannot be null");
            return;
        }

        createUserUseCase.execute(currentLogin, currentPassword, currentName, currentNickname, status -> {
            if (status.getStatusCode() == 201 && status.getErrors() == null) {

            } else {
                mutableErrorLiveData.postValue("Registration failed. Please try again.");
            }
        });
    }
}
