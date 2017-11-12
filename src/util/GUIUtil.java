package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 工具类 GUIUtil GUI的各种常用工具
 */
public class GUIUtil {
    //图片的文件夹
    private static final String imgFolder = "resources/img/";

    //定义皮肤
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param textField 文本框组件
     * @param name      文本框名字
     * @return 文本框输入是否正确
     */
    public static boolean checkNumber(JTextField textField, String name) {
        String text = textField.getText().trim();
        if (text.length() <= 0) {
            JOptionPane.showMessageDialog(null, "输入有误，" + name + "不能为空");
            textField.grabFocus();
            return false;
        }
        //直接用try检查
        try {
            Integer.parseInt(text);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "输入有误，" + name + "不是数字");
            return false;
        }
        return true;
    }

    /**
     * 测试用，直接把面板放进去拉起一个窗体
     *
     * @param p       待测试的面板
     * @param stretch 缩放比例
     */
    public static void showPanel(JPanel p, double stretch) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);
        f.setSize(500, 500);
        CenterPanel cp = new CenterPanel(stretch);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

    /**
     * 把一个按钮设置成图文形式
     *
     * @param b        按钮
     * @param fileName 图片名
     * @param tip      按钮下文字
     */
    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon((new File(imgFolder, fileName)).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    /**
     * 把若干个组件设置成指定颜色
     *
     * @param color 颜色
     * @param cs    组件
     */
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }
}
