package gui.panel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 界面类 SpendPanel 本月一览页
 */

public class SpendPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static SpendPanel instance = new SpendPanel();

    private JLabel lMonthSpend = new JLabel("本月消费");
    private JLabel lTodaySpend = new JLabel("今日消费");
    private JLabel lAvgSpendPerDay = new JLabel("日均消费");
    private JLabel lMonthLeft = new JLabel("本月剩余");
    private JLabel lDayAvgAvailable = new JLabel("日均可用");
    private JLabel lMonthLeftDay = new JLabel("距离月末");

    private JLabel vMonthSpend = new JLabel("￥2300");
    private JLabel vTodaySpend = new JLabel("￥25");
    private JLabel vAvgSpendPerDay = new JLabel("￥120");
    private JLabel vMonthAvailable = new JLabel("￥2084");
    private JLabel vDayAvgAvailable = new JLabel("￥389");
    private JLabel vMonthLeftDay = new JLabel("15天");

    private CircleProgressBar bar;

    private SpendPanel() {
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);

        GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);

        vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);

    }

    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
        return p;
    }

    private JPanel center() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(east(), BorderLayout.CENTER);
        return p;
    }

    private Component west() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }

    private Component east() {
        return bar;
    }

    public static void main(String[] args) {

        GUIUtil.showPanel(SpendPanel.instance);
    }


    @Override
    public void updateData() {
        SpendPage spend = new SpendService().getSpendPage();
        vMonthSpend.setText(spend.monthSpend);
        vTodaySpend.setText(spend.todaySpend);
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);
        vDayAvgAvailable.setText(spend.dayAvgAvailable);
        vMonthAvailable.setText(spend.monthAvailable);
        vMonthLeftDay.setText(spend.monthLeftDay);
        bar.setProgress(spend.usagePercentage);
        if (spend.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
        addListener();
    }

    @Override
    public void addListener() {

    }
}
