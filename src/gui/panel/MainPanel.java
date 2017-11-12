package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

/**
 * 界面类 MainPanel 主界面
 * 主要有一个 Toolbar 的工具栏 和 一个  workingPanel 的 CenterPanel 的 工作区
 * workingPanel工作区内为本包中的各个页面，它们都是抽象类 WorkingPanel 的 子类
 * 各个页面有它们独自的监听器负责用户操作的监听
 *
 * @see CenterPanel
 */

public class MainPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static MainPanel instance = new MainPanel();
    private JToolBar tb = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bHistory = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel;

    private MainPanel() {

        GUIUtil.setImageIcon(bSpend, "home.png", "本月一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bHistory, "history.png", "历史消费");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bCategory, "category.png", "消费分类");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bHistory);
        tb.add(bReport);
        tb.add(bCategory);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);

        workingPanel = new CenterPanel(0.85);

        this.setLayout(new BorderLayout());
        this.add(tb, BorderLayout.NORTH);
        this.add(workingPanel, BorderLayout.CENTER);

        addListeners();
    }

    private void addListeners() {
        ToolBarListener l = new ToolBarListener();
        bSpend.addActionListener(l);
        bHistory.addActionListener(l);
        bBackup.addActionListener(l);
        bCategory.addActionListener(l);
        bConfig.addActionListener(l);
        bRecord.addActionListener(l);
        bRecover.addActionListener(l);
        bReport.addActionListener(l);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}
