package com.example.talimet.message.service;

import com.example.talimet.message.dto.request.SendAnswer;
import com.example.talimet.message.dto.request.SendMessage;
import com.example.talimet.message.entity.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(SendMessage dto);
    Message sendAnswer(SendAnswer dto);

    List<Message> getQuestions();

    List<Message> getLastQuestions();
}
