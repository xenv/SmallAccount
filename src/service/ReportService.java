package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 业务类 ReportService 对 record 数据库的业务进行封装 供 ReportPanel 使用
 *
 * @see gui.panel.ReportPanel
 */
public class ReportService {
    /**
     * @param d            指定的一天
     * @param monthRawData 这个月的所有数据的 record了列表
     * @return 统计这一天花了多少钱
     */
    private int getDaySpend(Date d, List<Record> monthRawData) {
        int daySpend = 0;
        for (Record record : monthRawData) {
            if (record.getDate().equals(d))
                daySpend += record.getSpend();
        }
        return daySpend;
    }

    /**
     * @return 一个当前月每天的 record 列表
     */
    public List<Record> listThisMonthRecords() {
        RecordDAO dao = new RecordDAO();
        List<Record> monthRawData = dao.listThisMonth();
        List<Record> result = new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date theDayOfThisMonth = c.getTime();
            int daySpend = getDaySpend(theDayOfThisMonth, monthRawData);
            r.setSpend(daySpend);
            result.add(r);
        }
        return result;
    }
}
