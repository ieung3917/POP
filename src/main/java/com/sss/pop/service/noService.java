package com.sss.pop.service;

import com.sss.pop.dto.noDTO;
import org.springframework.web.servlet.ModelAndView;

public interface noService {


    ModelAndView noWrite(noDTO notice);

    ModelAndView noList(int page,String category,String keyword);

    ModelAndView noView(int noNum);

    ModelAndView noDelete(int noNum);

    ModelAndView noModiForm(int noNum);

    ModelAndView noModify(noDTO notice);


}
