package com.prelex20.prelex20.servicios;

public interface SeguridadServicio {
    String findLoggedInUsername();

void autologin(String username, String password);
}
