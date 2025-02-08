package ru.sicampus.bootcamp2025.ui.center;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.sicampus.bootcamp2025.data.CenterRepositoryImpl;
import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.center.GetCenterByIdUseCase;
import ru.sicampus.bootcamp2025.domain.entities.FullCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.user.GetActiveUsersInCenter;

public class CenterViewModel extends ViewModel {

    private final MutableLiveData<CenterState> mutableLiveDataCenterState = new MutableLiveData<>();

    public final LiveData<CenterState> liveDataCenterState = mutableLiveDataCenterState;

    private final MutableLiveData<VolunteersState> mutableLiveDataUserListState = new MutableLiveData<>();

    public final LiveData<VolunteersState> liveDataUserListState = mutableLiveDataUserListState;

    /* UseCases */

    private final GetCenterByIdUseCase getCenterByIdUseCase = new GetCenterByIdUseCase(
            CenterRepositoryImpl.getInstance()
    );

    private final GetActiveUsersInCenter getActiveUsersInCenter = new GetActiveUsersInCenter(
            UserRepositoryImpl.getInstance()
    );

    /* UseCases */

    public CenterViewModel(@NonNull String centerId) {
        loadVolunteers(centerId);
    }

    public void loadCenter(@NonNull String centerId) {
        mutableLiveDataCenterState.setValue(new CenterState(null, null, true));
        getCenterByIdUseCase.execute(centerId, (status) -> {
            mutableLiveDataCenterState.postValue(new CenterState(
                    status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                    status.getValue(),
                    false
            ));
        });
    }

    public void loadVolunteers(@NonNull String centerId) {
        getActiveUsersInCenter.execute(centerId, status -> {
            mutableLiveDataUserListState.postValue(new VolunteersState(
                    status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                    status.getValue(),
                    false
            ));
        });
    }

    private VolunteersState fromStatus(VolunteersState status) {
        return new VolunteersState(
                status.getErrorMessage() != null ? status.getErrorMessage() : null,
                status.getUserList(),
                false
        );
    }


    public class CenterState {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final FullCenterEntity center;

        private final boolean isLoading;

        public CenterState(@Nullable String errorMessage, @Nullable FullCenterEntity center, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.center = center;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public FullCenterEntity getCenter() {
            return center;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }

    public class VolunteersState {

        @Nullable
        private final String errorMessage;

        @Nullable
        private final List<ItemUserEntity> userList;

        private final boolean isLoading;

        public VolunteersState(@Nullable String errorMessage, @Nullable List<ItemUserEntity> userList, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.userList = userList;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<ItemUserEntity> getUserList() {
            return userList;
        }

        public boolean isLoading() {
            return isLoading;
        }

    }
}
