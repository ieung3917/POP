package com.sss.pop.service;

import com.sss.pop.dto.quDTO;
import org.springframework.web.servlet.ModelAndView;

public interface quService {
    ModelAndView quList(int page, String category, String keyword, String userId);

    ModelAndView quView(int quNum);

    ModelAndView quWrite(quDTO quest);

    ModelAndView quDelete(int quNum);

    ModelAndView quModiForm(int quNum);

    ModelAndView quModify(quDTO quest);

    ModelAndView quAWrite(quDTO quest);

    ModelAndView quNAList(int page, String category, String keyword, String userId);

    ModelAndView quAList(int page, String category, String keyword, String userId);
}
