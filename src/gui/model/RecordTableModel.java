package gui.model;

import dao.CategoryDAO;
import entity.Record;
import gui.panel.MonthPickerPanel;
import service.RecordService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * HistoryListPanel 的 Table 的数据模型，实现了 TableModel 接口
 *
 * @author xenv
 * @see gui.panel.HistoryListPanel
 */

public class RecordTableModel implements TableModel {
    private String[] columnNames = new String[]{"ID", "花费", "分类", "备注", "日期"};
    public List<Record> rs = new RecordService().listMonth(MonthPickerPanel.instance.date);

    @Override
    public int getRowCount() {
        return rs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (0 == columnIndex)
            return rs.get(rowIndex).getId();
        if (1 == columnIndex)
            return rs.get(rowIndex).getSpend();

        if (2 == columnIndex) {
            int cid = rs.get(rowIndex).getCid();
            return new CategoryDAO().get(cid).getName();
        }
        if (3 == columnIndex)
            return rs.get(rowIndex).getComment();
        if (4 == columnIndex)
            return rs.get(rowIndex).getDate();

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
