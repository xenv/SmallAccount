package gui.panel;

import entity.Record;
import gui.listener.HistoryListListener;
import gui.model.RecordTableModel;
import service.RecordService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * HistoryListPanel 是 HistoryPanel 中的历史记录面板
 * @see HistoryPanel
 *
 * @author xenv
 */
public class HistoryListPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static HistoryListPanel instance = new HistoryListPanel();
    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    private RecordTableModel rtm = new RecordTableModel();
    private JTable t =new JTable(rtm);

    private HistoryListPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bAdd,bEdit,bDelete);
        JScrollPane sp =new JScrollPane(t);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp,BorderLayout.CENTER);
        this.add(pSubmit,BorderLayout.SOUTH);

        this.addListener();
    }
    public static void main(String[] args) {
        GUIUtil.showPanel(HistoryListPanel.instance);
    }
    public boolean checkSelected(){
        return t.getSelectedRow()>=0;
    }
    public Record getSelectedRecord(){
        int index = t.getSelectedRow();
        return rtm.rs.get(index>0?index:0);
    }
    @Override
    public void updateData() {
        rtm.rs = new RecordService().listMonth(MonthPickerPanel.instance.date);
        t.updateUI();
    }

    @Override
    public void addListener() {
        HistoryListListener l = new HistoryListListener();
        bDelete.addActionListener(l);
        bEdit.addActionListener(l);
        bAdd.addActionListener(l);
    }
}
