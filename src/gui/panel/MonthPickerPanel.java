package gui.panel;

import gui.listener.MonthPickerListener;
import util.DateUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * MonthPickerPanel 是 HistoryPanel 中的月份选择器
 * 外部通过调用 MonthPickerPanel.instance.date 来获取选择器的时间
 * 本类写死了起始年份并保证了一定的年份使用的扩展性
 *
 * @author xenv
 * @see HistoryPanel
 */

public class MonthPickerPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static MonthPickerPanel instance = new MonthPickerPanel();
    //写死起始年份
    private final int startYear = 2017;
    //当前面板实例的时间
    public Date date = DateUtil.monthBegin();
    public JComboBox<Integer> cbMonth = new JComboBox<>(makeMonths());
    public JComboBox<Integer> cbYear = new JComboBox<>(makeYears());
    private JButton bSubmit = new JButton("查询");

    private MonthPickerPanel() {
        this.setLayout(new GridLayout(1, 3, 8, 8));
        //调整到当前月
        cbYear.setSelectedIndex(DateUtil.thisYear() - startYear);
        cbMonth.setSelectedIndex(DateUtil.thisMonth());
        this.add(cbYear);
        this.add(cbMonth);
        this.add(bSubmit);
        addListener();
    }

    /**
     * @return 2017 - 今年的Integer数组
     */
    private Integer[] makeYears() {
        int thisYear = DateUtil.thisYear();
        Integer[] result = new Integer[thisYear - startYear + 1];
        for (int i = 0; i <= thisYear - startYear; i++) {
            result[i] = startYear + i;
        }
        return result;
    }

    /**
     * @return 1-12的Integer数组
     */
    private Integer[] makeMonths() {
        Integer[] result = new Integer[12];
        for (int i = 0; i < 12; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        bSubmit.addActionListener(new MonthPickerListener());
    }
}
