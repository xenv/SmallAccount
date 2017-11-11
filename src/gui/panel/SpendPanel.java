package gui.panel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class SpendPanel extends WorkingPanel{
    static{
        GUIUtil.useLNF();
    }
    public static SpendPanel instance = new SpendPanel();

    public JLabel lMonthSpend = new JLabel("本月消费");
    public JLabel lTodaySpend = new JLabel("今日消费");
    public JLabel lAvgSpendPerDay = new JLabel("日均消费");
    public JLabel lMonthLeft = new JLabel("本月剩余");
    public JLabel lDayAvgAvailable = new JLabel("日均可用");
    public JLabel lMonthLeftDay = new JLabel("距离月末");

    public JLabel vMonthSpend = new JLabel("￥2300");
    public JLabel vTodaySpend = new JLabel("￥25");
    public JLabel vAvgSpendPerDay = new JLabel("￥120");
    public JLabel vMonthAvailable = new JLabel("￥2084");
    public JLabel vDayAvgAvailable = new JLabel("￥389");
    public JLabel vMonthLeftDay = new JLabel("15天");

    CircleProgressBar bar;

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
        p.add(east(),BorderLayout.CENTER);
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
        if(spend.isOverSpend){
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        }else{
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
