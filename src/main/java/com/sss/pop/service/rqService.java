package com.sss.pop.service;

import com.sss.pop.dto.rqDTO;
import org.springframework.web.servlet.ModelAndView;

public interface rqService {

    ModelAndView rqList(int page, String category, String keyword);

    ModelAndView rqView(int rqNum);

    ModelAndView rqWrite(rqDTO repque);

    ModelAndView rqDelete(int rqNum);

    ModelAndView rqModiForm(int rqNum);

    ModelAndView rqModify(rqDTO repque);

}
