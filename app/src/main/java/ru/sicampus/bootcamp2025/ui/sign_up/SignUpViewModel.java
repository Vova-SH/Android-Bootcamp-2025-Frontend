package ru.sicampus.bootcamp2025.ui.sign_up;

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

public class SignUpViewModel extends ViewModel {

    private final State INIT_STATE = new State(R.string.title_init, R.string.button_init, false);
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>(
            INIT_STATE
    );
    public final LiveData<State> stateLiveData = mutableStateLiveData;

    private final MutableLiveData<String> mutableErrorLiveData = new MutableLiveData<>();
    public final LiveData<String> errorLiveData = mutableErrorLiveData;

    private final MutableLiveData<Void> mutableOpenListLiveData = new MutableLiveData<>();
    public final LiveData<Void> openListLiveData = mutableOpenListLiveData;

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
    private String lastName = null;
    @Nullable
    private String firstName = null;
    @Nullable
    private String password = null;
    @Nullable
    private String confirmPassword = null;
    @Nullable
    private String contactInfo = null;

    private boolean userCheckCompleted = false;
    private boolean isNewAccount = false;

    public void changeUsername(@NonNull String username) {
        this.username = username;
        if (userCheckCompleted) {
            userCheckCompleted = false;
            mutableStateLiveData.postValue(INIT_STATE);
        }
    }
    public void changeLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }
    public void changeFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }
    public void changePassword(@NonNull String password) {
        this.password = password;
    }
    public void changeConfirmPassword(@NonNull String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public void changeContactInfo(@NonNull String contactInfo) {
        this.contactInfo = contactInfo;
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
        final String currentConfirmPassword = confirmPassword;
        final String currentLastName = lastName;
        final String currentFirstName = firstName;
        final String currentContactInfo = contactInfo;
        if (currentPassword == null || currentPassword.isEmpty()) {
            mutableErrorLiveData.postValue("Password cannot be null");
            return;
        }
        if (currentUsername == null || currentUsername.isEmpty()) {
            mutableErrorLiveData.postValue("Login cannot be null");
            return;
        }
        if (currentConfirmPassword == null || currentConfirmPassword.isEmpty() || !currentConfirmPassword.equals(currentPassword)) {
            mutableErrorLiveData.postValue("Confirm password not a same");
            return;
        }
        if (currentLastName == null || currentLastName.isEmpty()) {
            mutableErrorLiveData.postValue("Last name cannot be null");
            return;
        }
        if (currentFirstName == null || currentFirstName.isEmpty()) {
            mutableErrorLiveData.postValue("First name cannot be null");
            return;
        }
        if (currentContactInfo == null || currentContactInfo.isEmpty()) {
            mutableErrorLiveData.postValue("Contact info cannot be null");
            return;
        }
        if (isNewAccount) {
            Log.d("taggg", " " + currentFirstName + " " + currentLastName + " " + currentUsername + " " + currentPassword + " " + currentContactInfo);
            createUserUseCase.execute(currentFirstName, currentLastName, currentUsername, currentPassword, currentContactInfo, "", "", 1, null, status -> {
                Log.d("taggi", status.getStatusCode() + " " + status.getErrors());
                if (status.getStatusCode() == 201 && status.getErrors() == null) {
                    loginUser(currentUsername, currentPassword);
                } else {
                    mutableErrorLiveData.postValue("Something wrong");
                }
            });
        } else {
            mutableErrorLiveData.postValue("This username is used");
            return;
            //loginUser(currentusername, currentPassword);
        }
    }

    private void loginUser(@NonNull final String currentUsername,@NonNull final String currentPassword) {
        loginUserUseCase.execute(currentUsername, currentPassword, status -> {
            Log.d("tagg", status.getStatusCode() + " " + status.getErrors());
            if (status.getStatusCode() == 200 && status.getErrors() == null) {
                mutableOpenListLiveData.postValue(null);
            } else {
                mutableErrorLiveData.postValue("Something wrong");
            }
        });
    }

    private void checkUserExist() {
        final String currentUsername = username;
        isNewAccount = true;
        checkAuth();
        /*if (currentUsername == null || currentUsername.isEmpty()) {
            mutableErrorLiveData.postValue("Login cannot be null");
            return;
        }*/
        /*isUserExistUseCase.execute(currentUsername, status -> {
            if (status.getErrors() != null) {
                mutableErrorLiveData.postValue("Something wrong. Try later =(");
                return;
            }
            userCheckCompleted = true;
            if (status.getStatusCode() == 200) {
                isNewAccount = true;
            } else {
                isNewAccount = false;
            }
            checkAuth();
            /*if(isNewAccount) {
                checkAuth();

                mutableStateLiveData.postValue(
                        new State(R.string.title_user_new, R.string.button_user_new, true)
                );
            } else {
                mutableStateLiveData.postValue(
                        new State(R.string.title_user_exist, R.string.button_user_exist, true)
                );
            }*/
    //    });*/
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
