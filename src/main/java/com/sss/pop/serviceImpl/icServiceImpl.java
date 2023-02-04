package com.sss.pop.serviceImpl;

import com.sss.pop.dao.icDAO;
import com.sss.pop.dto.icDTO;
import com.sss.pop.service.icService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class icServiceImpl implements icService {

    private final icDAO icdao;

    private final HttpSession session;

    private ModelAndView mav;

    // 중고품 댓글 작성
    @Override
    public List<icDTO> itemCommentWrite(int icItemNum, String icContent, String icWriter) {
        icDTO ic = new icDTO();

        ic.setIcContent(icContent);
        ic.setIcItemNum(icItemNum);
        ic.setIcWriter(icWriter);

        System.out.println(ic);

        int result = icdao.itemCommentWrite(ic);

        if(result > 0){
            return icdao.itemCommentList(ic.getIcItemNum());
        } else {
            return null;
        }
    }

    // 중고품 댓글 불러오기
    @Override
    public List<icDTO> itemCommentList(int itemNum) {
        List<icDTO> commentList = icdao.itemCommentList(itemNum);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /*for(int i = 0 ; i<commentList.size();i++){
            commentList.get(i).setIcmtDate(dateFormat.format(commentList.get(i).getIcDate()));

            System.out.println(commentList.get(i));
        }*/

        System.out.println(commentList);

        return commentList;
    }

    // 중고품 댓글 삭제
    @Override
    public List<icDTO> itemCommentDelete(int icNum,int icItemNum) {
        List<icDTO> commentList;
        icdao.itemCommentDelete(icNum);
        return commentList = icdao.itemCommentList(icItemNum);
    }

}
