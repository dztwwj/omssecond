package com.aaa.oms.service;

import com.aaa.oms.dao.PlanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:PlanServiceImpl
 * discription:
 * author:zl
 * createTime:2018-12-24 09:27
 */
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;

    @Override
    public List<Map> getList(Map map) {
        return planDao.getList(map);
    }

    @Override
    public int add(Map map) {
        return planDao.add(map);
    }

    @Override
    public int update(Map map) {
        return planDao.update(map);
    }

    @Override
    public int delete(int p_id) {
        return planDao.delete(p_id);
    }

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");// [1,2,3,4]
        boolean isDel =true;
        List list = new ArrayList();
        for (String s : idsArray) {
            int i = planDao.delete(Integer.valueOf(s));
            if(i<1){
                isDel=false;
            }
        }
        if(isDel)
            return 1;
        else
            return 0;
    }

    @Override
    public List<Map> courseList1() {
        return planDao.courseList1();
    }

    @Override
    public List<Map> courseList(int pid) {
        return planDao.courseList(pid);
    }

    @Override
    public List<Map> plan(Map map) {
        return planDao.planList();
    }
}
