package ru.sicampus.bootcamp2025.ui.inactive_volunteers_list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.entities.ItemCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;
import ru.sicampus.bootcamp2025.domain.user.GetInactiveUsersUseCase;
import ru.sicampus.bootcamp2025.ui.centers_list.CentersListViewModel;
import ru.sicampus.bootcamp2025.ui.volunteer_profile.VolunteerProfileViewModel;


public class InactiveVolunteersListViewModel extends ViewModel {

    private final MutableLiveData<InactiveVolunteersListViewModel.State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<InactiveVolunteersListViewModel.State> stateLiveData = mutableStateLiveData;

    public InactiveVolunteersListViewModel() {
        update();
    }

    private final GetInactiveUsersUseCase getInactiveUsersUseCase = new GetInactiveUsersUseCase(
            UserRepositoryImpl.getInstance()
    );

    public void update() {
        mutableStateLiveData.setValue(new InactiveVolunteersListViewModel.State(null,  null, true));
        getInactiveUsersUseCase.execute(status -> {
            mutableStateLiveData.postValue(fromStatus(status));
        });
    }


    private State fromStatus(Status<List<ItemUserEntity>> status) {
        return new State(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                false
        );
    }


    public class State {

        @Nullable
        private final String errorMessage;

        @Nullable
        private final List<ItemUserEntity> inactiveUserList;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, @Nullable List<ItemUserEntity> inactiveUserList, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.inactiveUserList = inactiveUserList;
            this.isLoading = isLoading;
        }


        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        public boolean isLoading() {
            return isLoading;
        }

        @Nullable
        public List<ItemUserEntity> getInactiveUserList() {
            return inactiveUserList;
        }
    }
}


