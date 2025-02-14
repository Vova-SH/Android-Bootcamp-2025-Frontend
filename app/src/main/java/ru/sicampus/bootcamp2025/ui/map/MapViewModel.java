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
import ru.sicampus.bootcamp2025.domain.entites.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;
import ru.sicampus.bootcamp2025.ui.list.ListViewModel;

public class MapViewModel extends ViewModel {
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<State> stateLiveData = mutableStateLiveData;

    public final GetVolunteerCentersListUseCase getVolunteerCentersListUseCase = new GetVolunteerCentersListUseCase(
            UserRepositoryImpl.getInstance()
    );



    public void load() {
        mutableStateLiveData.setValue(new MapViewModel.State(null, null, true));
        getVolunteerCentersListUseCase.execute(status -> {
            mutableStateLiveData.postValue(fromStatus(status));
        });
    }

    private MapViewModel.State fromStatus(Status<List<ItemVolunteerCenterEntity>> status) {
        return new MapViewModel.State(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                false
        );
    }


    public class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final List<ItemVolunteerCenterEntity> items;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, @Nullable List<ItemVolunteerCenterEntity> items, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.items = items;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<ItemVolunteerCenterEntity> getItems() {
            return items;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }
}
