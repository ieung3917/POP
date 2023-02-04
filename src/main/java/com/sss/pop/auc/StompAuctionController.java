package com.sss.pop.auc;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompAuctionController {

    private final SimpMessagingTemplate template;



    @MessageMapping(value = "/auction/enter")
    public void enter(AuctionMessageDTO message){
        template.convertAndSend("/sub/auction/auView/" + message.getAuNum(), message);
    }

    @MessageMapping(value = "/auction/message")
    public void message(AuctionMessageDTO message){
        template.convertAndSend("/sub/auction/auView/" + message.getAuNum(), message);
    }
}
