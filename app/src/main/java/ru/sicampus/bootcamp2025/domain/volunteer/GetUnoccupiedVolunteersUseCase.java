package ru.sicampus.bootcamp2025.domain.volunteer;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.ItemVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class GetUnoccupiedVolunteersUseCase {

    public final VolunteerRepository repo;

    public GetUnoccupiedVolunteersUseCase(VolunteerRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull Consumer<Status<List<ItemVolunteerEntity>>> callback) {
        repo.getUnoccupiedVolunteers(callback);
    }
}
