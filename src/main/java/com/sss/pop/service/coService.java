package com.sss.pop.service;

import com.sss.pop.dto.coDTO;

import java.util.List;

public interface coService {

    List<coDTO> coList(int cbNum);

    List<coDTO> coWrite(coDTO comments);

    List<coDTO> coDelete(coDTO comments);

    List<coDTO> coModify(coDTO comments);
}
