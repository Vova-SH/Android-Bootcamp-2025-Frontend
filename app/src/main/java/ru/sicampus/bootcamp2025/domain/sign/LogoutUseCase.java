package ru.sicampus.bootcamp2025.domain.sign;


public class LogoutUseCase {

    private final SignUserRepository repo;

    public LogoutUseCase(SignUserRepository repo) {
        this.repo = repo;
    }

    public void execute(){
        repo.logout();
    }
}
