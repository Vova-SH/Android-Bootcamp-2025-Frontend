package ru.sicampus.bootcamp2025.domain.center;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.FullVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public interface CenterRepository {

    void getAllCenters(@NonNull Consumer<Status<List<ItemCenterEntity>>> callback);

    void getCenter(@NonNull String id, @NonNull Consumer<Status<FullCenterEntity>> callback);

    //void addVolunteer(@NonNull String centerId, @NonNull String volunteerId, @NonNull Consumer<Status<FullVolunteerEntity>> callback);

    //void  deleteVolunteer(@NonNull String centerId, @NonNull String volunteerId, @NonNull Consumer<Status<FullVolunteerEntity>> callback);
}
