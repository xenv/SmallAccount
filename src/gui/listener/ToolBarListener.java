package gui.listener;

import gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainPanel 的监听器，监听 Toolbar 的按钮操作并切换面板
 * workingPanel有方法 .show(WorkingPanel p) 可以居中显示子面板 并 更新数据
 * @see MainPanel
 */

public class ToolBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel p = MainPanel.instance;
        JButton b = (JButton) e.getSource();
        if (b == p.bBackup) {
            p.workingPanel.show(BackupPanel.instance);
        }
        if (b == p.bCategory) {
            p.workingPanel.show(CategoryPanel.instance);
        }
        if (b == p.bRecover) {
            p.workingPanel.show(RecoverPanel.instance);
        }
        if (b == p.bConfig) {
            p.workingPanel.show(ConfigPanel.instance);
        }
        if (b == p.bRecord) {
            p.workingPanel.show(RecordPanel.instance);
        }
        if (b == p.bReport) {
            p.workingPanel.show(ReportPanel.instance);
        }
        if (b == p.bSpend) {
            p.workingPanel.show(SpendPanel.instance);
        }
        if (b == p.bHistory) {
            p.workingPanel.show(HistoryPanel.instance);
        }

    }
}
