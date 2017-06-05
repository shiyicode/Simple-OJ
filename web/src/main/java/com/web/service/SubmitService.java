package com.web.service;

import com.web.dao.SubmitDAO;
import com.web.entity.BasicVo;
import com.web.entity.Submit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
@Service("SubmitService")
public class SubmitService {

    @Autowired
    private SubmitDAO submitDAO;

    public int addSubmit(int problem_id,int user_id,int language,String code){
        int res = -1;
        if(problem_id>0 && user_id>0 && language>0 && code!=null) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formats.format(cal.getTime());

            Submit submit = new Submit();
            submit.setProblem_id(problem_id);
            submit.setUser_id(user_id);
            submit.setSubmit_time(date);
            submit.setLanguage(language);
            submit.setResult_description("暂无");
            submit.setCode(code);

            res = submitDAO.save(submit);
        }
        return res;
    }

    public List<BasicVo> getSubmit(int problem_id, int user_id){
        List<BasicVo> submitList = null;
        if(problem_id>0 && user_id>0){
            Submit submit = new Submit();
            submit.setProblem_id(problem_id);
            submit.setUser_id(user_id);

            submitList = submitDAO.listBatch(submit,0,3);
        }
        return submitList;
    }
}
