package com.sss.pop.serviceImpl;

import com.sss.pop.dao.coDAO;
import com.sss.pop.dto.coDTO;
import com.sss.pop.service.coService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class coServiceImpl implements coService {

    private final coDAO cdao;

    @Override
    public List<coDTO> coList(int cbNum) {

//		System.out.println("[2] controller->service\n cbNum : "+cbNum);

        List<coDTO> coList = cdao.coList(cbNum);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

        for(int i = 0 ; i<coList.size();i++){
            coList.get(i).setComDate(dateFormat.format(coList.get(i).getCoDate()));
        }

//		System.out.println("[4] dao->service\n commentList : "+commentList);

        return coList;
    }

    @Override
    public List<coDTO> coWrite(coDTO comments) {
//		System.out.println("[2] jsp->controller\n comment : "+comment);

        List<coDTO> coList;

        int result = cdao.coWrite(comments);

        if(result>0) {
            coList = cdao.coList(comments.getCbNum());
        } else {
            coList = null;
        }

        return coList;
    }

    @Override
    public List<coDTO> coDelete(coDTO comments) {
//		System.out.println("[2] jsp->controller\n comment : "+comment);

        List<coDTO> coList;

        int result = cdao.coDelete(comments);

        if(result>0) {
            coList = cdao.coList(comments.getCbNum());
        } else {
            coList = null;
        }

        return coList;
    }

    @Override
    public List<coDTO> coModify(coDTO comments) {
//		System.out.println("[2] jsp->controller\n comment : "+comment);

        List<coDTO> coList;

        int result = cdao.coModify(comments);


        if(result>0) {
            coList = cdao.coList(comments.getCbNum());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

            for(int i = 0 ; i<coList.size();i++){
                coList.get(i).setComDate(dateFormat.format(coList.get(i).getCoDate()));
            }
        } else {
            coList = null;
        }

        return coList;
    }

}

