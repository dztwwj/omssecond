package com.aaa.oms.service;

import com.aaa.oms.dao.ResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:ResultServiceImpl
 * discription:
 * author:zl
 * createTime:2018-12-21 20:48
 */
@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultDao resultDao;

    @Override
    public List<Map> getList(Map map) {

        return resultDao.getList(map);
    }

    @Override
    public int update(Map map) {
        return resultDao.update(map);
    }

    @Override
    public int delete(int rid) {
        return resultDao.delete(rid);
    }

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");// [1,2,3,4]
        boolean isDel =true;
        List list = new ArrayList();
        for (String s : idsArray) {
            int i = resultDao.delete(Integer.valueOf(s));
            if(i<1){
                isDel=false;
            }
        }
        if(isDel)
            return 1;
        else
            return 0;
    }

}
