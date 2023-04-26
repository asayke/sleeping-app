package ru.asayke.jwtappdemo.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}