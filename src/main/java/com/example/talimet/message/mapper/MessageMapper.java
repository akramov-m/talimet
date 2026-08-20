package com.example.talimet.message.mapper;

import com.example.talimet.message.dto.request.SendAnswer;
import com.example.talimet.message.dto.request.SendMessage;
import com.example.talimet.message.dto.response.MessageBodyResponse;
import com.example.talimet.message.dto.response.MessageResponse;
import com.example.talimet.message.entity.Message;

public class MessageMapper {
    public static MessageResponse messageDto(String message){
        return new MessageResponse(
                message
        );
    }

    public static Message dtoToQuestionEntity(SendMessage question){
        Message message = new Message();
        message.setSenderFullName(question.fullName());
        message.setSubject(question.subject());
        message.setQuestion(question.message());
        message.setPhoneNumber(question.phoneNumber());
        message.setAnswer(null);
        return message;
    };

    public static MessageBodyResponse entityToDto(Message message){
        return new MessageBodyResponse(
                message.getId(),
                message.getSenderFullName(),
                message.getSubject(),
                message.getQuestion(),
                message.getPhoneNumber()
        );
    }


}
