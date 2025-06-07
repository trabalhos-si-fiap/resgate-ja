package com.resgateja.interfaces;

import com.resgateja.dtos.EmailMessage;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface EmailSender {
    HttpResponse<String> sendEmail(EmailMessage message) throws IOException, InterruptedException;
}
