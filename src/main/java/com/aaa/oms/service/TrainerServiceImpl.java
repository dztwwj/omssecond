package com.aaa.oms.service;

import com.aaa.oms.dao.TrainerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:TrainerServiceImpl
 * discription:
 * author:zl
 * createTime:2018-12-20 09:11
 */
@Service
public class TrainerServiceImpl implements TrainerService{

    @Autowired
    private TrainerDao trainerDao;

    @Override
    public List<Map> getList(Map map) {
        return trainerDao.getList(map);
    }

    @Override
    public int add(Map map) {
        return trainerDao.add(map);
    }

    @Override
    public int update(Map map) {
        return trainerDao.update(map);
    }

    @Override
    public int delete(int id) {
        return trainerDao.delete(id);
    }

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");// [1,2,3,4]
        boolean isDel =true;
        List list = new ArrayList();
        for (String s : idsArray) {
            int i = trainerDao.delete(Integer.valueOf(s));
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
    public List<Map> courseList(Map map) {
        return trainerDao.courseList();
    }
}
