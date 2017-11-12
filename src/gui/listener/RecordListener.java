package gui.listener;

import entity.Category;
import gui.panel.*;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * RecordPanel 的监听器，按钮后会 增改Record
 * 增改由 RecordPanel.instance.updateId 决定 ，默认为 -1 ，可能被 HistoryListPanel 修改
 *
 * @author xenv
 * @see RecordPanel
 * @see HistoryListPanel
 * @see HistoryListListener
 */

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;
        //判断数据
        if (p.cbModel.cs.size() == 0) {
            JOptionPane.showMessageDialog(p, "请先添加分类");
        }
        String spend = p.tfSpend.getText();
        if (!GUIUtil.checkNumber(p.tfSpend, "输入的金额")) {
            return;
        }
        if (spend.equals("0")) {
            JOptionPane.showMessageDialog(p, "输入金额有误");
        }
        Category c = p.getSelectedCategory();
        String comment = p.tfComment.getText();
        Date d = p.datepick.getDate();
        //根据 updateId 确定模式
        if (p.updateId < 0) { //默认的添加模式
            new RecordService().add(Integer.parseInt(spend), c.getId(), comment, d);
            JOptionPane.showMessageDialog(p, "添加成功");

            MainPanel.instance.workingPanel.show(SpendPanel.instance);
        } else { //修改模式
            new RecordService().update(p.updateId, Integer.parseInt(spend), c.getId(), comment, d);
            JOptionPane.showMessageDialog(p, "修改成功");
            MainPanel.instance.workingPanel.show(HistoryPanel.instance);
            //重置p.updateId
            p.updateId = -1;
            //刷新 HistoryListPanel
            HistoryListPanel.instance.updateData();
        }

    }
}
