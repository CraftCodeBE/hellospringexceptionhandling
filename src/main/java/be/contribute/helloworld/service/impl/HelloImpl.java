package be.contribute.helloworld.service.impl;

import be.contribute.helloworld.model.HelloMessage;
import be.contribute.helloworld.repo.HelloRepository;
import be.contribute.helloworld.service.MessageBusinessException;
import be.contribute.helloworld.service.interfaces.IHelloService;
import org.springframework.stereotype.Service;

import java.util.List;

import static be.contribute.helloworld.service.MessageBusinessException.ID_ALREADY_EXISTS;
import static be.contribute.helloworld.service.MessageBusinessException.MESSAGE_NOT_FOUND;

@Service
public class HelloImpl implements IHelloService {
    private final HelloRepository helloRepository;

    public HelloImpl() {
        this.helloRepository = new HelloRepository();
    }

    @Override
    public List<HelloMessage> getAllMessages() {
        return helloRepository.findAll();
    }

    @Override
    public HelloMessage createMessage(HelloMessage message) {
        var messageOptional = helloRepository.findById(message.getId());

        if (messageOptional.isPresent()) {
            throw new MessageBusinessException(ID_ALREADY_EXISTS);
        }

        return helloRepository.saveMessage(message);
    }

    @Override
    public HelloMessage updateMessage(HelloMessage message) {
        var messageOptional = helloRepository.findById(message.getId());
        if (messageOptional.isEmpty()) {
            throw new MessageBusinessException(MESSAGE_NOT_FOUND);
        }

        return helloRepository.updateMessage(message);
    }
}
