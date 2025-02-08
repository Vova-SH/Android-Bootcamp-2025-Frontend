package ru.sicampus.bootcamp2025.domain.profile

class LogoutUserCase (
    private val profileRepo: ProfileRepo
){
    operator suspend fun invoke() {
        return profileRepo.logout()
    }

}