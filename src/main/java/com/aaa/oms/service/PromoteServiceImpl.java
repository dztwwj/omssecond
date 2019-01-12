package com.aaa.oms.service;

import com.aaa.oms.dao.PromoteDao;
import com.aaa.oms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * className:PromoteServiceImpl
 * discription:
 * author:LiuQian
 * createTime:2018-12-18 10:34:37
 */
@Service
public class PromoteServiceImpl implements PromoteService {


    @Autowired
    private PromoteDao promoteDao;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Override
    public List<Map> getPageParam(Map map) {
        List<Map> maps = promoteDao.selectID();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sysdate = formatter.format(currentTime);

        for (Map map1 : maps) {
            Integer id = Integer.valueOf(map1.get("ID")+"");
//            System.out.println("id"+id);
//            System.out.println("dddd"+promoteDao.selectCount(id));
            Integer count = Integer.valueOf(promoteDao.selectCount(id).get("COUNT")+"");
            Integer demandnum = Integer.valueOf(promoteDao.selectDemandnum(id).get("DEMANDNUM")+"");
//            Object endapply = promoteDao.selectDemandnum(id).get("ENDAPPLY");
            String endapply = (String)promoteDao.selectDemandnum(id).get("ENDAPPLY");
            if(count >= demandnum ){
                promoteDao.updateToOK(id);
            }
            try {
                Date a = formatter.parse(sysdate);
                Date b = formatter.parse(endapply);
                if(a.after(b)){
                    promoteDao.updateToOK(id);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return promoteDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return promoteDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        return promoteDao.add(map);
    }

    @Override
    public int update(Map map) {
        return promoteDao.update(map);
    }

    @Override
    public int delete(int id) {
        return promoteDao.delete(id);
    }

    @Override
    public List<Map> selectPosition() {
        return promoteDao.selectPosition();
    }
    @Override
    public List<Map> auditPromote(Map map) {
        return promoteDao.auditPromote(map);
    }

    @Override
    public int auditPromoteCount(Map map) {
        return promoteDao.auditPromoteCount(map);
    }

    @Override
    public int updateTG(Map map) {
        promoteDao.updateToPosition(map);
        return promoteDao.updateTG(map);
    }

    @Override
    public int updateNoTG(Map map) {
        return promoteDao.updateNoTG(map);
    }

    @Override
    public int apply(Map map) {

        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());
        //System.out.println(map+"map");
        // System.out.println(userService.selectEvery(map).get(0).get("POSITION")+"ssssss");
        Object pid = (userService.selectEvery(map)).get(0).get("POSITION");
        map.put("pid",pid);
        System.out.println("sss"+pid+"ddd"+map);
        return promoteDao.apply(map);
    }

    @Override
    public List<Map> selectPromote(Map map) {
        return promoteDao.selectPromote(map);
    }
}
