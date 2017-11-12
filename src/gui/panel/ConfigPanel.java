package gui.panel;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

/**
 * 界面类 ConfigPanel 设置页
 */

public class ConfigPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();

 
    private JLabel lBudget = new JLabel("本月预算(￥)");
    public JTextField tfBudget = new JTextField();
    public JButton bTruncate = new JButton("重置数据库");
    public JButton bSubmit = new JButton("更新");
 
    public ConfigPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lBudget);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
         
        JPanel pNorth =new JPanel();
        JPanel pSouth = new JPanel();
        int gap =40;
        pNorth.setLayout(new GridLayout(3,1,gap,gap));

        pNorth.add(lBudget);
        pNorth.add(tfBudget);
        pNorth.add(bSubmit);
        this.setLayout(new BorderLayout());
        this.add(pNorth,BorderLayout.NORTH);

         
        pSouth.add(bTruncate);
        this.add(pSouth,BorderLayout.SOUTH);

        addListener();
    }
    public void addListener(){
        ConfigListener l =new ConfigListener();
        bSubmit.addActionListener(l);
        bTruncate.addActionListener(l);
    }
    public void updateData(){
        ConfigService service = new ConfigService();
        tfBudget.setText(service.get(ConfigService.budget));
        tfBudget.grabFocus();
    }
    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }
     
}