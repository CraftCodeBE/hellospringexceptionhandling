package be.contribute.helloworld.service.interfaces;

import be.contribute.helloworld.model.HelloMessage;

import java.util.List;

public interface IHelloService {
    /**
     * Searches for all messages.
     *
     * @return List of Messages
     */
    List<HelloMessage> getAllMessages();

    /**
     * Creates a new Message.
     *
     * @param message
     * @return the message param but enriched witch the actual message
     */
    HelloMessage createMessage(HelloMessage message);

    /**
     * Changes the message
     *
     * @param message
     * @return Message
     */
    HelloMessage updateMessage(HelloMessage message);
}
