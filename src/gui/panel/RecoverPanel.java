package gui.panel;
 
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

/**
 * 界面类 RecoverPanel 恢复页
 */

public class RecoverPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static RecoverPanel instance = new RecoverPanel();
 
    private JButton bRecover =new JButton("恢复");
 
    public RecoverPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);
        addListener();
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }
    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        bRecover.addActionListener(listener);
    }
}