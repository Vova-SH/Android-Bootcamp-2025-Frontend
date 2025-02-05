package ru.sicampus.bootcamp2025.domain.volunteer;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class GetVolunteerByIdUseCase {

    private final VolunteerRepository repo;

    public GetVolunteerByIdUseCase(VolunteerRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<FullVolunteerEntity>> callback){
        repo.getVolunteer(id, callback);
    }
}
