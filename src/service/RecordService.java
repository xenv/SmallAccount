package service;

import dao.RecordDAO;
import entity.Record;
import util.GUIUtil;

import java.util.Date;

public class RecordService  {
    public void add(int spend,int cid,String comment,Date date){
        RecordDAO dao = new RecordDAO();
        Record r = new Record();
        r.setSpend(spend);
        r.setCid(cid);
        r.setComment(comment);
        r.setDate(date);
        dao.add(r);
    }
}
