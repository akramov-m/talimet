package com.example.talimet.message.service.impl;

import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.message.dto.request.SendAnswer;
import com.example.talimet.message.dto.request.SendMessage;
import com.example.talimet.message.entity.Message;
import com.example.talimet.message.mapper.MessageMapper;
import com.example.talimet.message.repository.MessageRepository;
import com.example.talimet.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public Message sendMessage(SendMessage dto) {
        Message question = MessageMapper.dtoToQuestionEntity(dto);
        Message savedQuestion= messageRepository.save(question);
        return savedQuestion;
    }

    @Override
    public Message sendAnswer(SendAnswer dto) {
        Message message = messageRepository.findMessageById(dto.messageId())
                .orElseThrow(()->new NotFoundException("Message not found!"));
        message.setAnswer(dto.answer());
        message.setResponderFullName(dto.responderFullName());
        return message;
    }

    @Override
    public List<Message> getQuestions() {
        List<Message> questions = messageRepository.getQuestions();
        return questions;
    }

    @Override
    public List<Message> getLastQuestions() {
        List<Message> lastQuestions = messageRepository.getLastQuestion();
        return lastQuestions;
    }
}
