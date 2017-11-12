package gui.listener;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.DBUtil;
import util.GUIUtil;
import util.SQLUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ConfigPanel的监听器，监听按钮后进行config的改操作 和 调用工具类进行重置操作
 *
 * @author xenv
 * @see ConfigPanel
 */

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if (p.bSubmit == e.getSource()) {
            if (!GUIUtil.checkNumber(p.tfBudget, "本月预算")) {
                return;
            }
            ConfigService service = new ConfigService();
            //改操作
            service.update(ConfigService.budget, p.tfBudget.getText());

            JOptionPane.showMessageDialog(p, "设置成功");
        }

        if (p.bTruncate == e.getSource()) {
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实清空所有数据？")) {
                return;
            }
            SQLUtil.truncate();
            //重新初始化数据库
            ConfigService.init();
            JOptionPane.showMessageDialog(p, "重置成功");
        }
    }
}
