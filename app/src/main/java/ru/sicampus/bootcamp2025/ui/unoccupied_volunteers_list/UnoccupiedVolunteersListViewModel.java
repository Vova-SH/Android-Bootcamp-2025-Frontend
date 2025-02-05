package ru.sicampus.bootcamp2025.ui.unoccupied_volunteers_list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class UnoccupiedVolunteersListViewModel extends ViewModel {

    private final MutableLiveData<UnoccupiedVolunteersListViewModel.State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<UnoccupiedVolunteersListViewModel.State> stateLiveData = mutableStateLiveData;

    public UnoccupiedVolunteersListViewModel() {
        update();
    }

    public void update() {
        mutableStateLiveData.setValue(new UnoccupiedVolunteersListViewModel.State(null,  true));
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


