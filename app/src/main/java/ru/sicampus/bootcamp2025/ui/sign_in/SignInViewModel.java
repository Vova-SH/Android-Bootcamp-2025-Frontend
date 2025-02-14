package ru.sicampus.bootcamp2025.ui.sign_in;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.sign.CreateUserUseCase;
import ru.sicampus.bootcamp2025.domain.sign.IsUserExistUseCase;
import ru.sicampus.bootcamp2025.domain.sign.LoginUserUseCase;

public class SignInViewModel extends ViewModel {

    private final State INIT_STATE = new State(R.string.title_init, R.string.button_init, false);
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>(
            INIT_STATE
    );
    public final LiveData<State> stateLiveData = mutableStateLiveData;

    private final MutableLiveData<String> mutableErrorLiveData = new MutableLiveData<>();
    public final LiveData<String> errorLiveData = mutableErrorLiveData;

    private final MutableLiveData<String> mutableOpenListLiveData = new MutableLiveData<>();
    public final LiveData<String> openListLiveData = mutableOpenListLiveData;

    /* UseCases */
    private final IsUserExistUseCase isUserExistUseCase = new IsUserExistUseCase(
            UserRepositoryImpl.getInstance()
    );
    private final CreateUserUseCase createUserUseCase = new CreateUserUseCase(
            UserRepositoryImpl.getInstance()
    );
    private final LoginUserUseCase loginUserUseCase = new LoginUserUseCase(
            UserRepositoryImpl.getInstance()
    );
    /* UseCases */

    @Nullable
    private String username = null;
    @Nullable
    private String password = null;

    private boolean userCheckCompleted = false;
    private boolean isNewAccount = false;

    public void changeUsername(@NonNull String username) {
        this.username = username;
        if (userCheckCompleted) {
            userCheckCompleted = false;
            mutableStateLiveData.postValue(INIT_STATE);
        }
    }

    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public void confirm() {
        if (userCheckCompleted) {
            checkAuth();
        } else {
            checkUserExist();
        }

    }

    private void checkAuth() {
        final String currentUsername = username;
        final String currentPassword = password;
        if (currentPassword == null || currentPassword.isEmpty()) {
            mutableErrorLiveData.postValue("Password cannot be null");
            return;
        }
        if (currentUsername == null || currentUsername.isEmpty()) {
            mutableErrorLiveData.postValue("Login cannot be null");
            return;
        }
        if (isNewAccount) {
            mutableErrorLiveData.postValue("This user not exist");
            return;
            /*createUserUseCase.execute(currentUsername, currentPassword, status -> {
                if (status.getStatusCode() == 201 && status.getErrors() == null) {
                    loginUser(currentUsername, currentPassword);
                } else {
                    mutableErrorLiveData.postValue("Something wrong");
                }
            });*/
        } else {
            Log.d("tagg", currentUsername + " " + currentPassword);
            loginUser(currentUsername, currentPassword);
        }
    }

    private void loginUser(@NonNull final String currentUsername, @NonNull final String currentPassword) {
        loginUserUseCase.execute(currentUsername, currentPassword, status -> {
            Log.d("tagg", status.getStatusCode() + " " + status.getErrors());
            if (status.getStatusCode() == 200 && status.getErrors() == null) {
                mutableOpenListLiveData.postValue(currentUsername);
            } else {
                mutableErrorLiveData.postValue("Something wrong");
            }
        });
    }

    private void checkUserExist() {
        final String currentUsername = username;
        if (currentUsername == null || currentUsername.isEmpty()) {
            mutableErrorLiveData.postValue("Login cannot be null");
            return;
        }
        isUserExistUseCase.execute(currentUsername, status -> {
            if (status.getErrors() != null) {
                //Log.d("tagg", status.getErrors().toString());
                mutableErrorLiveData.postValue("Something wrong. Try later =(" + status.getErrors());
                return;
            }
            userCheckCompleted = true;
            Log.d("tagigiii", status.getStatusCode() + "");
            if (status.getStatusCode() == 200) {
                isNewAccount = false;
            } else {
                isNewAccount = true;
            }
            //isNewAccount = !status.getValue();
            checkAuth();
            /*if(isNewAccount) {
                mutableStateLiveData.postValue(
                        new State(R.string.title_user_new, R.string.button_user_new, true)
                );
            } else {
                mutableStateLiveData.postValue(
                        new State(R.string.title_user_exist, R.string.button_user_exist, true)
                );
            }*/
        });
    }

    public class State {
        @StringRes
        private final int title;

        @StringRes
        private final int button;

        private final boolean isPasswordEnabled;

        public State(int title, int button, boolean isPasswordEnabled) {
            this.title = title;
            this.button = button;
            this.isPasswordEnabled = isPasswordEnabled;
        }

        public int getTitle() {
            return title;
        }

        public int getButton() {
            return button;
        }

        public boolean isPasswordEnabled() {
            return isPasswordEnabled;
        }
    }
}
