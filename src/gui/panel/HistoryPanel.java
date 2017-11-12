package gui.panel;

import util.GUIUtil;

import java.awt.*;

/**
 * 界面类 HistoryPanel 历史消费页
 * 调用了 MonthPickerPanel 和 HistoryListPanel 两个子面板
 *
 * @author xenv
 * @see MonthPickerPanel
 * @see HistoryListPanel
 */
public class HistoryPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static HistoryPanel instance = new HistoryPanel();

    private HistoryPanel() {
        this.setLayout(new BorderLayout());
        this.add(MonthPickerPanel.instance, BorderLayout.NORTH);
        this.add(HistoryListPanel.instance, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(HistoryPanel.instance);
    }

    @Override
    public void updateData() {
        HistoryListPanel.instance.updateData();
    }

    @Override
    public void addListener() {

    }
}
