package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryService categoryService = new CategoryService();
        CategoryPanel p = CategoryPanel.instance;
        JButton b = (JButton)e.getSource();
        if(p.bAdd == b){
            String name = JOptionPane.showInputDialog(null);
            if(null == name){
                return;
            }
            if(0 == name.length()){
                JOptionPane.showMessageDialog(p,"名称不为空");
                return;
            }
            categoryService.add(name);
        }
        if(p.bEdit == b){
            Category c = p.getSelectedCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("修改名称",c.getName());
            if(null == name){
                return;
            }
            if(0 == name.length()){
                JOptionPane.showMessageDialog(p,"名称不为空");
                return;
            }
            categoryService.update(id,name);
        }
        if(p.bDelete == b){
            Category c = p.getSelectedCategory();
            if(0!=c.getRecordNumber()){
                JOptionPane.showMessageDialog(p,"分类下还有数据");
                return;
            }
            if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p,"确实删除？")){
                return;
            }
            int id = c.getId();
            categoryService.delete(id);
        }
        p.updateData();
    }
}
