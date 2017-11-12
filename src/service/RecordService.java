package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * 业务类 RecordService 对 record 数据库的业务进行封装
 *
 * @author xenv
 */

public class RecordService {
    private static RecordDAO dao = new RecordDAO();

    public void add(int spend, int cid, String comment, Date date) {
        Record r = new Record();
        r.setSpend(spend);
        r.setCid(cid);
        r.setComment(comment);
        r.setDate(date);
        dao.add(r);
    }

    public void update(int id, int spend, int cid, String comment, Date date) {
        Record r = new Record();
        r.setSpend(spend);
        r.setId(id);
        r.setCid(cid);
        r.setComment(comment);
        r.setDate(date);
        dao.update(r);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    /**
     * 获取指定月的 record 数据
     *
     * @param startDay 指定月的第一天的 Date
     * @return record 的列表
     */
    public List<Record> listMonth(Date startDay) {
        Date endDay = DateUtil.monthEnd(startDay);
        return dao.list(startDay, endDay);
    }
}
