package gui.listener;

import gui.panel.HistoryListPanel;
import gui.panel.MonthPickerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * MonthPickerPanel 的监听器，按钮后会修改 MonthPickerPanel.instance.date 为 选择月份的月初
 *
 * @author xenv
 * @see MonthPickerPanel
 */

public class MonthPickerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MonthPickerPanel p = MonthPickerPanel.instance;
        Integer year = (Integer) p.cbYear.getSelectedItem();
        Integer month = (Integer) p.cbMonth.getSelectedItem();
        //用SimpleDateFormat获取所选月月初的 Date
        try {
            p.date = new SimpleDateFormat("yyyy-MM").parse(String.format("%4d-%02d", year, month));
            //更新 HistoryListPanel ,重新获取数据
            HistoryListPanel.instance.updateData();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

    }
}
