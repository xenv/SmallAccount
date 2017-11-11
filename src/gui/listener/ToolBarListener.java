package gui.listener;

import entity.Record;
import gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    }
}
