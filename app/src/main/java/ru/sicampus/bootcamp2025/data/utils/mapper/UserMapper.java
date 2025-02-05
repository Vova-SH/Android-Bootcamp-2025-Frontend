package ru.sicampus.bootcamp2025.data.utils.mapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.sicampus.bootcamp2025.data.dto.UserDto;
import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;

public class UserMapper {

    @Nullable
    public static ItemUserEntity toItemUserEntity(@NonNull UserDto userDto) {
        final String id = userDto.id;
        final String name = userDto.name;
        final String email = userDto.email;

        if (id != null && name != null && email != null) {
            return new ItemUserEntity(
                    id,
                    name,
                    email,
                    userDto.photoUrl
                    );
        }
        return null;
    }


    @Nullable
    public static FullUserEntity toFullUserEntity(@NonNull UserDto userDto) {
        final String id = userDto.id;
        final String name = userDto.name;
        final String nickname = userDto.nickname;
        final String email = userDto.email;

        if (id != null && name != null && nickname != null && email != null) {
            return new FullUserEntity(
                    id,
                    name,
                    nickname,
                    email,
                    userDto.phone,
                    userDto.photoUrl
            );
        }
        return null;
    }
}
