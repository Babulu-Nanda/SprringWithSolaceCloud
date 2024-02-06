package com.example.demo;


import com.solacesystems.jcsmp.JCSMPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private SolaceDirectService solaceDirectService;

    @PostMapping("/send")
    public void sendMessageToTopic(@RequestBody RequesTPOJO requestData) throws JCSMPException {
        solaceDirectService.sendMessageToTopic("test12345", requestData.getMessageBody());
    	//solaceDirectService.anotherWay("testSolace", requestData.getMessageBody());
    
    }
    

    @GetMapping("/subscribe")
    public void subscribeToTopic() throws JCSMPException {
        solaceDirectService.subscribeToTopic("testSolace");
    }
}
