package gui.listener;

import entity.Category;
import entity.Record;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.CategoryService;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;
        if(p.cbModel.cs.size()==0){
            JOptionPane.showMessageDialog(p,"请先添加分类");
        }
        String spend = p.tfSpend.getText();
        if(!GUIUtil.checkNumber(p.tfSpend,"输入的金额")){
            return;
        }
        if(spend.equals("0")){
            JOptionPane.showMessageDialog(p,"输入金额有误");
        }
        Category c =p.getSelectedCategory();
        String comment = p.tfComment.getText();
        Date d = p.datepick.getDate();
        new RecordService().add(Integer.parseInt(spend),c.getId(),comment,d);
        JOptionPane.showMessageDialog(p,"添加成功");

        MainPanel.instance.workingPanel.show(SpendPanel.instance);


    }
}
