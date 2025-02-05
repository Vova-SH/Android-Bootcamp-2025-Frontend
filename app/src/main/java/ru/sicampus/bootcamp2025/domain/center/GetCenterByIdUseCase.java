package ru.sicampus.bootcamp2025.domain.center;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class GetCenterByIdUseCase {

    public final CenterRepository repo;

    public GetCenterByIdUseCase(CenterRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<FullCenterEntity>> callback) {
        repo.getCenter(id, callback);
    }
}
