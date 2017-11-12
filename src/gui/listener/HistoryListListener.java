package gui.listener;

import entity.Category;
import entity.Record;
import gui.panel.*;
import service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * HistoryListPanel的监听器，监听按钮后进行record的删操作，增改操作交给RecordPanel
 *
 * @author xenv
 * @see HistoryListPanel
 * @see RecordPanel
 */
public class HistoryListListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordService service = new RecordService();
        HistoryListPanel p = HistoryListPanel.instance;
        JButton b = (JButton) e.getSource();

        if (p.bAdd == b) {
            //交给RecordPanel处理
            MainPanel.instance.workingPanel.show(RecordPanel.instance);
            RecordPanel.instance.datepick.setDate(MonthPickerPanel.instance.date);
        }
        if (p.bEdit == b) {
            //交给RecordPanel处理
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行 ");
            } else {
                Record r = p.getSelectedRecord();
                //修改 updateId 设置，在 RecordPanel 提交的时候就会使用修改入库而不是直接入库
                RecordPanel.instance.updateId = r.getId();
                //切换到 RecordPanel
                MainPanel.instance.workingPanel.show(RecordPanel.instance);
                //把旧数据读出来，写到 RecordPanel 待用户修改完直接更新入库即可
                RecordPanel.instance.tfSpend.setText(String.valueOf(r.getSpend()));
                RecordPanel.instance.tfComment.setText(r.getComment());
                //获取模型里的id而不是数据库的id，即帮用户选择分类
                RecordPanel.instance.cbCategory.setSelectedIndex(getModelId(r.getCid()));
                RecordPanel.instance.datepick.setDate(r.getDate());
            }
        }
        if (p.bDelete == b) {
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行 ");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实删除？")) {
                return;
            }
            service.delete(p.getSelectedRecord().getId());
            p.updateData();
        }
    }


    /**
     * @param cid 分类在数据库中的cid
     * @return 分类在 CategoryComboBoxModel 模型列表中的实际序数id
     */
    private int getModelId(int cid) {
        List<Category> categoryComBoxList = RecordPanel.instance.cbModel.cs;
        for (int i = 0; i < categoryComBoxList.size(); i++) {
            if (categoryComBoxList.get(i).getId() == cid) {
                return i;
            }
        }
        return 0; //若分类已经删除则返回第0个分类
    }
}
