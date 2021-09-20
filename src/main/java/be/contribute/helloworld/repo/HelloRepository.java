package be.contribute.helloworld.repo;

import be.contribute.helloworld.model.HelloMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Getter
@Setter
public class HelloRepository {
    private List<HelloMessage> helloMessages;

    public HelloRepository() {
        this.helloMessages = new ArrayList<>() {{
            add(HelloMessage.builder().id(1).greeting("Hi everybody, this is dr. Nick!").build());
            add(HelloMessage.builder().id(2).greeting("Say hello to my little friend.").build());
            add(HelloMessage.builder().id(3).greeting("I am your father!!!!").build());
            add(HelloMessage.builder().id(4).greeting("Bond, James Bond.").build());
        }};
    }

    public List<HelloMessage> findAll() {
        return helloMessages;
    }

    public Optional<HelloMessage> findById(int id) {
        return helloMessages
                .stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }

    public HelloMessage saveMessage(HelloMessage helloMessage) {
        helloMessages.add(helloMessage);

        return helloMessage;
    }

    public HelloMessage updateMessage(HelloMessage helloMessage) {
        var messageToUpdate = helloMessages
                .stream()
                .filter(m -> m.getId() == helloMessage.getId())
                .findFirst();

        messageToUpdate.ifPresent(message -> message.setGreeting(helloMessage.getGreeting()));

        return helloMessage;
    }
}
