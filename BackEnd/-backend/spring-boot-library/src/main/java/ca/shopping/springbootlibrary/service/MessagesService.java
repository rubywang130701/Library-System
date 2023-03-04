package ca.shopping.springbootlibrary.service;

import ca.shopping.springbootlibrary.dao.MessageRepository;
import ca.shopping.springbootlibrary.entity.Message;
import ca.shopping.springbootlibrary.requestmodels.AdminQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MessagesService {

    private MessageRepository messageRepository;

    @Autowired
    public MessagesService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void postMessage(Message messageRequest, String userEmail) {
        Message message = new Message(messageRequest.getTitle(), messageRequest.getQuestion());
        message.setUserEmail(userEmail);
        messageRepository.save(message);
    }
// TODO
//  This code is updating a message in response to an admin question.
//  It first retrieves the message from the message repository using the message ID provided in the adminQuestionRequest object.
//  If the message is not found, it throws an exception with a message indicating that the message was not found.
//  If the message is found, it updates the message with the admin's email,
//  the response provided in the adminQuestionRequest object, and sets the closed flag to true,
//  indicating that the message has been responded to and is now closed.
//  Finally, it saves the updated message in the message repository.
    public void putMessage(AdminQuestionRequest adminQuestionRequest, String userEmail) throws Exception {
        Optional<Message> message = messageRepository.findById(adminQuestionRequest.getId());
        if (!message.isPresent()) {
            throw new Exception("Message not found");
        }

        message.get().setAdminEmail(userEmail);
        message.get().setResponse(adminQuestionRequest.getResponse());
        message.get().setClosed(true);
        messageRepository.save(message.get());
    }

}
