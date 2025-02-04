package ru.sicampus.bootcamp2025.domain.center;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.ItemCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class GetCentersListCase {

    public final CenterRepository repo;

    public GetCentersListCase(CenterRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull Consumer<Status<List<ItemCenterEntity>>> callback) {
        repo.getAllCenters(callback);
    }
}
