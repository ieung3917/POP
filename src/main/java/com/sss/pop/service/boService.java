package com.sss.pop.service;

import com.sss.pop.dto.boDTO;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface boService {

    // boWrite : 게시물 작성
    ModelAndView boWrite(boDTO board) throws IOException;

    ModelAndView boList(int page, int limit, String category, String keyword);

    ModelAndView boView(int boNum);

    ModelAndView boModifyForm(int boNum);

    ModelAndView boModify(boDTO board) throws IOException;

    ModelAndView boDelete(int boNum);

    boDTO checkAjax(int boNum);
}
