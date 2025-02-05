package ru.sicampus.bootcamp2025.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.data.dto.CenterDto;
import ru.sicampus.bootcamp2025.data.network.RetrofitFactory;
import ru.sicampus.bootcamp2025.data.source.CenterApi;
import ru.sicampus.bootcamp2025.data.utils.CallToConsumer;
import ru.sicampus.bootcamp2025.data.utils.mapper.CenterMapper;
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
                        ItemCenterEntity entity = CenterMapper.toItemCenterEntity(center);
                        if (entity != null) {
                            result.add(entity);
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
                CenterMapper::toFullCenterEntity
        ));
    }
}