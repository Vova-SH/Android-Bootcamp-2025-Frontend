package ru.sicampus.bootcamp2025.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.data.dto.CenterDto;
import ru.sicampus.bootcamp2025.data.network.RetrofitFactory;
import ru.sicampus.bootcamp2025.data.source.CenterApi;
import ru.sicampus.bootcamp2025.data.utils.CallToConsumer;
import ru.sicampus.bootcamp2025.domain.center.CenterRepository;
import ru.sicampus.bootcamp2025.domain.entities.FullCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class CenterRepositoryImpl implements CenterRepository {

    private static CenterRepositoryImpl INSTANCE;
    private final CenterApi centerApi = RetrofitFactory.getInstance().getCenterApi();

    public static synchronized CenterRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CenterRepositoryImpl();
        }
        return INSTANCE;
    }

    private CenterRepositoryImpl() {
    }

    @Override
    public void getAllCenters(@NonNull Consumer<Status<List<ItemCenterEntity>>> callback) {
        centerApi.getAll().enqueue(new CallToConsumer<>(
                callback,
                centerDtos -> {
                    ArrayList<ItemCenterEntity> result = new ArrayList<>(centerDtos.size());
                    for (CenterDto center : centerDtos) {
                        final String id = center.id;
                        final String centerName = center.centreName;
                        final String address = center.address;
                        if (id != null && centerName != null && address != null) {
                            result.add(new ItemCenterEntity(id, centerName, address));
                        }
                    }
                    return result;
                }
        ));
    }

    @Override
    public void getCenter(@NonNull String id, @NonNull Consumer<Status<FullCenterEntity>> callback) {
        centerApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                center -> {
                    final String resultId = center.id;
                    final String centerName = center.centreName;
                    final String address = center.address;
                    final String phone = center.phone;
                    if (resultId != null && centerName != null && address != null && phone != null) {
                        return new FullCenterEntity(
                                resultId,
                                centerName,
                                address,
                                phone,
                                center.activeVolunteers
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }
}
