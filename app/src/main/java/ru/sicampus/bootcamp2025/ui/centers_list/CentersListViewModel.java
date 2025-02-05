package ru.sicampus.bootcamp2025.ui.centers_list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class CentersListViewModel extends ViewModel {

    private final MutableLiveData<CentersListViewModel.State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<CentersListViewModel.State> stateLiveData = mutableStateLiveData;


    public CentersListViewModel() {
        update();
    }

    public void update() {
        mutableStateLiveData.setValue(new CentersListViewModel.State(null,  true));
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
