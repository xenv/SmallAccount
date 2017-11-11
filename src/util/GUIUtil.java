package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {
    public static String imgFolder = "resources/img/";

    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkNumber(JTextField textField, String name) {
        String text = textField.getText().trim();
        if (text.length() <= 0) {
            JOptionPane.showMessageDialog(null, "输入有误，" + name + "不能为空");
            textField.grabFocus();
            return false;
        }
        try{
            Integer.parseInt(text);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "输入有误，" + name + "不是数字");
            return false;
        }
        return true;
    }

    public static void showPanel(JPanel p, double strech) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);
        f.setSize(500, 500);
        CenterPanel cp = new CenterPanel(strech);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon((new File(imgFolder, fileName)).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }
}
