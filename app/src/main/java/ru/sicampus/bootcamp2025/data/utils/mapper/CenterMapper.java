package ru.sicampus.bootcamp2025.data.utils.mapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.sicampus.bootcamp2025.data.dto.CenterDto;
import ru.sicampus.bootcamp2025.domain.entities.FullCenterEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemCenterEntity;

public class CenterMapper {

    @Nullable
    public static ItemCenterEntity toItemCenterEntity(@NonNull CenterDto centerDto) {
        final String id = centerDto.id;
        final String centerName = centerDto.centreName;
        final String address = centerDto.address;

        if (id != null && centerName != null && address != null) {
            return new ItemCenterEntity(id, centerName, address);
        }
        return null;
    }


    @Nullable
    public static FullCenterEntity toFullCenterEntity(@NonNull CenterDto centerDto) {
        final String id = centerDto.id;
        final String centerName = centerDto.centreName;
        final String address = centerDto.address;
        final String phone = centerDto.phone;

        if (id != null && centerName != null && address != null && phone != null) {
            return new FullCenterEntity(
                    id,
                    centerName,
                    address,
                    phone,
                    centerDto.activeVolunteers
            );
        }
        return null;
    }
}

