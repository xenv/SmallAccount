package gui.panel;
 
import java.awt.BorderLayout;
import java.awt.Image;
 
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;
 
public class ReportPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }
 
    public static ReportPanel instance = new ReportPanel();
 
    public JLabel l = new JLabel();
 
    public ReportPanel() {
        this.setLayout(new BorderLayout());

        Image i = ChartUtil.getImage(new ReportService().listThisMonthRecords(),400, 300);
        ImageIcon icon= new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

    @Override
    public void updateData() {
        Image i = ChartUtil.getImage(new ReportService().listThisMonthRecords(),400, 300);
        ImageIcon icon= new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}