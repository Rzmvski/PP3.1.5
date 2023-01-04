package ru.kata.spring.boot_security.demo.Security;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
