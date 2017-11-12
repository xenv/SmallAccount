package service;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

import java.util.List;

/**
 * 业务类 SpendService 对 多个 数据库的业务进行封装 供 SpendPanel 使用
 *
 * @see gui.panel.SpendPanel
 */

public class SpendService {
    public SpendPage getSpendPage() {
        RecordDAO dao = new RecordDAO();

        List<Record> thisMonthRecords = dao.listThisMonth();
        List<Record> todayRecords = dao.listToday();
        int thisMonthTotalday = DateUtil.thisMonthTotalDay();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        int monthBudget = new ConfigService().getIntBudget();

        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }

        for (Record record : todayRecords) {
            todaySpend += record.getSpend();
        }
        avgSpendPerDay = monthSpend / thisMonthTotalday;
        monthAvailable = monthBudget - monthSpend;
        monthLeftDay = DateUtil.thisMonthLeftDay();
        dayAvgAvailable = monthAvailable / monthLeftDay;
        usagePercentage = monthSpend * 100 / monthBudget;

        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay, usagePercentage);
    }


}
