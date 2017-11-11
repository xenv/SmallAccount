package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportService {
    public int getDaySpend(Date d, List<Record> monthRawData){
        int daySpend = 0;
        for(Record record:monthRawData){
            if(record.date.equals(d))
            daySpend+=record.getSpend();
        }
        return daySpend;
    }

    public List<Record> listThisMonthRecords(){
        RecordDAO dao = new RecordDAO();
        List<Record> monthRawData = dao.listThisMonth();
        List<Record> result = new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for(int i=0;i<monthTotalDay;i++){
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE,i);
            Date theDayOfThisMonth = c.getTime();
            int daySpend = getDaySpend(theDayOfThisMonth,monthRawData);
            r.setSpend(daySpend);
            result.add(r);
        }
        return result;
    }
}
