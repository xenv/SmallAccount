package gui.listener;

import dao.ConfigDAO;
import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if(!GUIUtil.checkNumber(p.tfBudget,"本月预算")){
            return;
        }
        ConfigService service = new ConfigService();
        service.update(ConfigService.budget,p.tfBudget.getText());

        JOptionPane.showMessageDialog(null,"设置成功");
    }
}
