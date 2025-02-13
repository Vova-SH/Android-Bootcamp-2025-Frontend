package ru.sicampus.bootcamp2025.ui.profile;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.GetUserByIdUseCase;
import ru.sicampus.bootcamp2025.domain.GetVolunteerCenterByIdUseCase;
import ru.sicampus.bootcamp2025.domain.GetVolunteerCentersListUseCase;
import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;
import ru.sicampus.bootcamp2025.domain.sign.IsUserExistUseCase;
import ru.sicampus.bootcamp2025.ui.list.ListViewModel;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<State> stateLiveData = mutableStateLiveData;

    public final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase(
            UserRepositoryImpl.getInstance()
    );

    public final GetVolunteerCentersListUseCase getVolunteerCentersListUseCase = new GetVolunteerCentersListUseCase(
            UserRepositoryImpl.getInstance()
    );

    public final GetVolunteerCenterByIdUseCase getVolunteerCenterByIdUseCase = new GetVolunteerCenterByIdUseCase(
            UserRepositoryImpl.getInstance()
    );

    private final IsUserExistUseCase isUserExistUseCase = new IsUserExistUseCase(
            UserRepositoryImpl.getInstance()
    );

    public void load(@NonNull String username) {
        mutableStateLiveData.setValue(new State(null, null, null, true));
        postUserByUsername(username);
        /*getUserByIdUseCase.execute(username, (status) -> {
            if (status.getStatusCode() == 200 && status.getErrors() == null) {
                if (status.getValue().getStatus() != null) {
                    getVolunteerCenterByIdUseCase.execute(status.getValue().getStatus(), (status2) -> {
                        if (status2.getStatusCode() == 200 && status2.getErrors() == null) {
                            mutableStateLiveData.postValue(new State(
                                    status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                                    status.getValue(),
                                    status2.getValue(),
                                    false
                            ));
                        } else {
                            //mutableErrorLiveData.postValue("Something wrong");
                        }
                    });
                } else {
                    mutableStateLiveData.postValue(new State(
                            status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                            status.getValue(),
                            null,
                            false
                    ));
                }
            } else {
                //mutableErrorLiveData.postValue("Something wrong");
            }
        });*/
    }

    private void postUserByUsername(String username) {
        final String currentUsername = username;
        if (currentUsername == null || currentUsername.isEmpty()) {
            //mutableErrorLiveData.postValue("Login cannot be null");
            return;
        }
        isUserExistUseCase.execute(currentUsername, status -> {
            if (status.getValue() == null || status.getErrors() != null) {
                Log.d("tagg", status.getErrors().toString());
                //mutableErrorLiveData.postValue("Something wrong. Try later =(" + status.getErrors());
                return;
            }
            //userCheckCompleted = true;
            if (status.getStatusCode() == 200) {
                if (status.getValue().getStatus() != null) {
                    getVolunteerCenterByIdUseCase.execute(status.getValue().getStatus(), status2 -> {
                        Log.d("taggis", status.getStatusCode() + " " + status.getErrors());
                        if (status.getStatusCode() == 200 && status.getErrors() == null) {
                            mutableStateLiveData.postValue(new ProfileViewModel.State(
                                    status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                                    status.getValue(),
                                    status2.getValue(),
                                    false
                            ));
                        } else {
                            //mutableErrorLiveData.postValue("Something wrong");
                        }
                    });
                } else {
                    mutableStateLiveData.postValue(new State(
                            status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                            status.getValue(),
                            null,
                            false
                    ));
                }
            } else {

            }
            //isNewAccount = !status.getValue();
            //checkAuth();
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

    /*private ProfileViewModel.State fromStatus(Status<List<ItemVolunteerCenterEntity>> status, Status<List<ItemVolunteerCenterEntity>> status2) {
        return new ProfileViewModel.State(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                false
        );
    }*/


    public class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final FullUserEntity user;
        @Nullable
        private final FullVolunteerCenterEntity volunteerCenter;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, @Nullable FullUserEntity user, @Nullable FullVolunteerCenterEntity volunteerCenter, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.user = user;
            this.volunteerCenter = volunteerCenter;
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

        @Nullable
        public FullVolunteerCenterEntity getVolunteerCenter() {
            return volunteerCenter;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }
}
