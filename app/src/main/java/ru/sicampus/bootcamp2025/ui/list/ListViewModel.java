package ru.sicampus.bootcamp2025.ui.list;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.sicampus.bootcamp2025.data.UserRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.GetUsersListUseCase;
import ru.sicampus.bootcamp2025.domain.GetVolunteerCenterByIdUseCase;
import ru.sicampus.bootcamp2025.domain.GetVolunteerCentersListUseCase;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;

public class ListViewModel extends ViewModel {
    private final MutableLiveData<State> mutableStateCentersLiveData = new MutableLiveData<>();

    public final LiveData<State> stateCentersLiveData = mutableStateCentersLiveData;

    private final MutableLiveData<State> mutableStateVolunteerCenterLiveData = new MutableLiveData<>();

    public final LiveData<State> stateVolunteerCenterLiveData = mutableStateVolunteerCenterLiveData;

    public final GetVolunteerCentersListUseCase getVolunteerCentersListUseCase = new GetVolunteerCentersListUseCase(
            UserRepositoryImpl.getInstance()
    );

    public final GetVolunteerCenterByIdUseCase getVolunteerCenterByIdUseCase = new GetVolunteerCenterByIdUseCase(
            UserRepositoryImpl.getInstance()
    );

    private final GetUsersListUseCase getUsersListUseCase = new GetUsersListUseCase(
            UserRepositoryImpl.getInstance()
    );

    /*public ListViewModel() {
        update();
    }*/

    private Integer volunteerCenterId;

    public void changeSelectedVCId(@NonNull Integer volunteerCenterId) {
        this.volunteerCenterId = volunteerCenterId;
    }

    public void load() {
        mutableStateCentersLiveData.setValue(new ListViewModel.State(null, null, null, true));
        getVolunteerCentersListUseCase.execute(status -> {
            mutableStateCentersLiveData.postValue(fromStatus(status));
        });
    }

    public void getVCById() {
        getVolunteerCenterByIdUseCase.execute(volunteerCenterId, status -> {
            Log.d("taggis", status.getStatusCode() + " " + status.getErrors());
            if (status.getStatusCode() == 200 && status.getErrors() == null) {
                mutableStateVolunteerCenterLiveData.postValue(new ListViewModel.State(
                        status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                        null,
                        status.getValue(),
                        false
                ));
            } else {
                //mutableErrorLiveData.postValue("Something wrong");
            }
        });
    }

    private ListViewModel.State fromStatus(Status<List<ItemVolunteerCenterEntity>> status) {
        return new ListViewModel.State(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                null,
                false
        );
    }

    public class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final List<ItemVolunteerCenterEntity> itemsVolunteerCenters;

        @Nullable
        private final FullVolunteerCenterEntity volunteerCenter;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, @Nullable List<ItemVolunteerCenterEntity> itemsVolunteerCenters, FullVolunteerCenterEntity volunteerCenter, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.itemsVolunteerCenters = itemsVolunteerCenters;
            this.volunteerCenter = volunteerCenter;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<ItemVolunteerCenterEntity> getItemsVolunteerCenters() {
            return itemsVolunteerCenters;
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
