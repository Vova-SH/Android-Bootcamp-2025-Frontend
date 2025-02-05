package ru.sicampus.bootcamp2025.ui.volunteer_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.user.GetUserByIdUseCase;


public class VolunteerProfileViewModel extends ViewModel {
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<State> stateLiveData = mutableStateLiveData;

    private final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase(
            UserRepositoryImpl.getInstance()
    );

    public void load(@NonNull String id) {

        mutableStateLiveData.setValue(new State(null, null, true));

        getUserByIdUseCase.execute(id, (status) -> {
            if (status.getErrors() != null) {
                mutableStateLiveData.postValue(new State(
                        status.getErrors().getLocalizedMessage(),
                        null,
                        false
                ));
            } else if (status.getValue() != null) {
                mutableStateLiveData.postValue(new State(
                        null,
                        status.getValue(),
                        false
                ));
            } else {
                mutableStateLiveData.postValue(new State(
                        "User data is not available",
                        null,
                        false
                ));
            }
        });
    }

    public void goBack(){
        //TODO: implement method
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

