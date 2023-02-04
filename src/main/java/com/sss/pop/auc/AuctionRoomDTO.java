package com.sss.pop.auc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AuctionRoomDTO {

    private String auNum;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static AuctionRoomDTO create(String name){
        AuctionRoomDTO room = new AuctionRoomDTO();

        room.auNum = String.valueOf(name);
        room.name = String.valueOf(name);
        return room;
    }
}
