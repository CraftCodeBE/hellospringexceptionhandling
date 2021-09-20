package be.contribute.helloworld.controller;

import be.contribute.helloworld.model.HelloMessage;
import be.contribute.helloworld.service.interfaces.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class HelloWorldController {
    private final IHelloService helloService;

    @Autowired
    public HelloWorldController(IHelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/hello",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HelloMessage>> getHello() {
        return new ResponseEntity<>(helloService.getAllMessages(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloMessage> postHello(@RequestBody HelloMessage helloMessage) {
        return new ResponseEntity<>(helloService.createMessage(helloMessage), HttpStatus.CREATED);
    }


    /**
     * TODO: methods need work. you need to implement an id before you can update a certain message
     * @param helloMessage
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloMessage> putHello(@RequestBody HelloMessage helloMessage) {
        return new ResponseEntity<>(helloService.updateMessage(helloMessage), HttpStatus.ACCEPTED);
    }
}
