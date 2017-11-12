package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.SQLUtil;

/**
 * RecoverPanel 的监听器，监听按钮动作后进行导入操作
 */

public class RecoverListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("xiaoxiao.db"));
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

        int returnVal = fc.showOpenDialog(p);
        File file = fc.getSelectedFile();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                SQLUtil.recover(file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "恢复成功");
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "恢复失败\r\n,错误:\r\n" + e1.getMessage());
            }

        }
    }

}