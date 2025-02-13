package ru.sicampus.bootcamp2025.domain.entites;

import androidx.annotation.NonNull;

public class FullRoleEntity {
    @NonNull
    private final Integer id;
    @NonNull
    private final String roleName;

    public FullRoleEntity(@NonNull Integer id, @NonNull String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getRoleName() {
        return roleName;
    }
}
