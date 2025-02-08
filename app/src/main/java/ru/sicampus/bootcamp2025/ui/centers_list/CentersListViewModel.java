package ru.sicampus.bootcamp2025.ui.centers_list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.sicampus.bootcamp2025.data.CenterRepositoryImpl;
import ru.sicampus.bootcamp2025.domain.center.GetCentersListCase;
import ru.sicampus.bootcamp2025.domain.entities.ItemCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class CentersListViewModel extends ViewModel {

    private final MutableLiveData<CentersListViewModel.State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<CentersListViewModel.State> stateLiveData = mutableStateLiveData;

    private final GetCentersListCase getCentersListCase = new GetCentersListCase(
            CenterRepositoryImpl.getInstance()
    );


    public CentersListViewModel() {
        update();
    }

    public void update() {
        mutableStateLiveData.setValue(new CentersListViewModel.State(null,  null, true));
        getCentersListCase.execute(status -> {
            mutableStateLiveData.postValue(fromStatus(status));
        });
    }


    private State fromStatus(Status<List<ItemCenterEntity>> status) {
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
        private final List<ItemCenterEntity> centersList;

        private final boolean isLoading;


        public State(@Nullable String errorMessage, @Nullable List<ItemCenterEntity> centersList, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.centersList = centersList;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<ItemCenterEntity> getCentersList() {
            return centersList;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }
}
