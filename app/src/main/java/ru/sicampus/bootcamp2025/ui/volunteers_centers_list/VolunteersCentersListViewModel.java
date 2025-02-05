package ru.sicampus.bootcamp2025.ui.volunteers_centers_list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class VolunteersCentersListViewModel extends ViewModel {

    private final MutableLiveData<VolunteersCentersListViewModel.State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<VolunteersCentersListViewModel.State> stateLiveData = mutableStateLiveData;


    public VolunteersCentersListViewModel() {
        update();
    }

    public void update() {
        mutableStateLiveData.setValue(new VolunteersCentersListViewModel.State(null,  true));
    }

    public class State {
        @Nullable
        private final String errorMessage;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }
}
