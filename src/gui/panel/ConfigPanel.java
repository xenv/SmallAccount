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
 
public class ConfigPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();

 
    JLabel lBudget = new JLabel("本月预算(￥)");
    public JTextField tfBudget = new JTextField();

    JButton bSubmit = new JButton("更新");
 
    public ConfigPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lBudget);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
         
        JPanel pInput =new JPanel();
        JPanel pSubmit = new JPanel();
        int gap =40;
        pInput.setLayout(new GridLayout(4,1,gap,gap));
         
        pInput.add(lBudget);
        pInput.add(tfBudget);
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
         
        pSubmit.add(bSubmit);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }
    public void addListener(){
        bSubmit.addActionListener(new ConfigListener());
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