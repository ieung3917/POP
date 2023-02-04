package com.sss.pop.auc;

import com.sss.pop.dto.noteDTO;
import com.sss.pop.service.noteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/auction")
@Log4j2
public class AuctionRoomController {

    private final AuctionRoomRepository repository;

    private final auService ausvc;

    private final noteService notesvc;

    private final HttpSession session;

    //채팅방 개설
    @PostMapping(value = "/auView")
    public String create(@RequestParam String name, RedirectAttributes rttr){

        log.info("# Create Chat Room , name: " + name);
        rttr.addFlashAttribute("roomName", repository.createAuctionRoomDTO(Integer.parseInt(name)));
        return "redirect:/auction/auView";
    }

    /*// auList : 경매품 리스트
    @GetMapping("/auList")
    public ModelAndView auList() {
        return ausvc.auList();
    }*/
    // auList : 경매품 리스트
   /* @GetMapping("/auList")
    public ModelAndView auList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "category", required = false, defaultValue = "0") int category,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        session.removeAttribute("category");
        session.removeAttribute("search");

        return ausvc.auList(page,category,search);
    }*/



    // 경매품 상세보기
    @GetMapping("/auView")
    public ModelAndView getRoom(String auNum, Model model){

        log.info("# get Chat Room, roomID : " + auNum);

        model.addAttribute("auNum", repository.findRoomById(auNum));
        return ausvc.auView(Integer.parseInt(auNum));
    }

    // 경매 입찰
    @PostMapping(value = "/auPropose")
    public @ResponseBody int auPropose(@RequestParam("userId") String userId, @RequestParam("price") int price, @RequestParam("auNum") int auNum) throws ParseException {
        int result = ausvc.auPropose(userId,price,auNum);

        return result;
    }

    // 경매 끝날 시
    @PostMapping(value = "/auComplete")
    public @ResponseBody int auComplete(@RequestParam("auNum") int auNum){
        int result = ausvc.auComplete(auNum);

        return result;
    }

    // 경매남은 시간
    @PostMapping(value = "/auRemain")
    public @ResponseBody String auRemain(@RequestParam("auNum") int auNum){

        return ausvc.auRemain(auNum);
    }


    // 내쪽지함
    @PostMapping("/noteMynote")
    public @ResponseBody List<noteDTO> noteMynote(@RequestParam("userId") String userId) {


        List<noteDTO> mynoteList = notesvc.noteMynote(userId);

        return mynoteList;
    }
}
