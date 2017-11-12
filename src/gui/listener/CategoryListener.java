package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CategoryPanel的监听器，监听按钮后进行增改删操作
 *
 * @see CategoryPanel
 */

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryService categoryService = new CategoryService();
        CategoryPanel p = CategoryPanel.instance;
        //拉回按钮比对看用户按了哪个
        JButton b = (JButton) e.getSource();
        if (p.bAdd == b) {
            //检查数据
            String name = JOptionPane.showInputDialog(null);
            if (null == name) {
                return;
            }
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "名称不为空");
                return;
            }
            categoryService.add(name);
        }
        if (p.bEdit == b) {
            //检查数据
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行");
                return;
            }
            Category c = p.getSelectedCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("修改名称", c.getName());
            if (null == name) {
                return;
            }
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "名称不为空");
                return;
            }
            categoryService.update(id, name);
        }
        if (p.bDelete == b) {
            //检查数据
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行");
                return;
            }
            Category c = p.getSelectedCategory();
            //外键约束所以不能直接删
            if (0 != c.getRecordNumber()) {
                JOptionPane.showMessageDialog(p, "分类下还有数据");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实删除？")) {
                return;
            }
            int id = c.getId();
            categoryService.delete(id);
        }
        p.updateData();
    }
}
