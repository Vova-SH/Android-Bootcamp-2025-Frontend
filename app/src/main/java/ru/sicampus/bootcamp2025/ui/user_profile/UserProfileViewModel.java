package ru.sicampus.bootcamp2025.ui.user_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.sign.LogoutUseCase;
import ru.sicampus.bootcamp2025.domain.user.GetUserByIdUseCase;
import ru.sicampus.bootcamp2025.domain.user.UpdateUserProfileUseCase;

public class UserProfileViewModel extends ViewModel {

    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();
    public final LiveData<State> stateLiveData = mutableStateLiveData;

    private final MutableLiveData<Void> mutableLogoutLiveData = new MutableLiveData<>();
    public final LiveData<Void> logoutLiveData = mutableLogoutLiveData;

    private FullUserEntity user;

    /* UseCases */
    private final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase(
            UserRepositoryImpl.getInstance()
    );

    private final UpdateUserProfileUseCase updateUserProfileUseCase = new UpdateUserProfileUseCase(
            UserRepositoryImpl.getInstance()
    );

    private final LogoutUseCase logoutUseCase = new LogoutUseCase(
            UserRepositoryImpl.getInstance()
    );
    /* UseCases */

    public void loadUser(@NonNull String userId) {
        mutableStateLiveData.setValue(new State(null, null, true));
        getUserByIdUseCase.execute(userId, (status) -> {
            mutableStateLiveData.postValue(new State(
                    status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                    status.getValue(),
                    false
            ));
        });
    }

    public void updateUserProfile(
            @NonNull String id,
            String newName,
            String newNickname,
            String newEmail,
            String newPhotoUrl) {
        if (user == null) return;

        if (newName == null || newName.isEmpty()) {
            mutableStateLiveData.postValue(new State("Name cannot be null", user, false));
            return;
        }

        if (newNickname == null || newNickname.isEmpty()) {
            mutableStateLiveData.postValue(new State("Nickname cannot be null", user, false));
            return;
        }

        if (newEmail == null || newEmail.isEmpty()) {
            mutableStateLiveData.postValue(new State("Email cannot be null", user, false));
            return;
        }

        mutableStateLiveData.setValue(new State(null, user, true));

        updateUserProfileUseCase.execute(
                id,
                newName,
                newNickname,
                newEmail,
                newPhotoUrl,
                state -> {
                    if (state.getErrors() != null) {
                        mutableStateLiveData.postValue(new State(
                                state.getErrors().getLocalizedMessage(),
                                user,
                                false
                        ));
                    } else {
                        user = state.getValue();
                        mutableStateLiveData.postValue(new State(null, user, false));
                    }
                }
        );
    }

    public void logout() {
        logoutUseCase.execute();
        mutableLogoutLiveData.postValue(null);
    }

    public void setUser(FullUserEntity user) {
        this.user = user;
        mutableStateLiveData.postValue(new State(null, user, false));
    }

    public static class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final FullUserEntity user;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, @Nullable FullUserEntity user, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.user = user;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public FullUserEntity getUser() {
            return user;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }
}