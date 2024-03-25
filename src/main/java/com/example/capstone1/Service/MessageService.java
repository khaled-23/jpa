package com.example.capstone1.Service;

import com.example.capstone1.Model.Message;
import com.example.capstone1.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void sendMessage(Message message){
        message.setDateTime(LocalDateTime.now());
        messageRepository.save(message);
    }

    public ArrayList<Message> getMessages(Integer receiverId, Integer senderId) {
        List<Message> messages = messageRepository.findAll();
        ArrayList<Message> receiverMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getReceiverId().equals(receiverId)
                    && message.getSenderId().equals(senderId)) {
                receiverMessages.add(message);
            }
        }
        return receiverMessages;
    }
}
