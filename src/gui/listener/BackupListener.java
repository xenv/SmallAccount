package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.BackupPanel;
import util.SQLUtil;

/**
 * BackupPanel的监听器，监听按钮动作后进行导出操作
 *
 * @author xenv
 * @see BackupPanel
 */

public class BackupListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        //拉起选择器
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("xiaoxiao.db"));
        //定义过滤器
        fc.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return ".db";
            }

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".db");
            }
        });

        int returnVal = fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        //捕捉是否点击保存
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //如果保存的文件名没有以.db结尾，自动加上.db
            System.out.println(file);
            if (!file.getName().toLowerCase().endsWith(".db"))
                file = new File(file.getParent(), file.getName() + ".db");
            System.out.println(file);

            try {
                SQLUtil.backup(file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "备份成功,备份文件位于:\r\n" + file.getAbsolutePath());
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "备份失败\r\n,错误:\r\n" + e1.getMessage());
            }

        }
    }

}