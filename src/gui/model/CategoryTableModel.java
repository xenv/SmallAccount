package gui.model;
 
import entity.Category;
import service.CategoryService;

import java.util.ArrayList;
import java.util.List;
 
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * CategoryPanel 的 Table 的数据模型，实现了 TableModel 接口
 *
 * @see gui.panel.CategoryPanel
 */

public class CategoryTableModel implements TableModel{
    //列
    private String[] columnNames = new String[]{"分类名称","消费次数"};
    //行数据
    public List<Category> cs = new CategoryService().list();

    @Override
    public int getRowCount() {
        return cs.size();
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
        if(0==columnIndex)
            return cs.get(rowIndex).getName();
        if(1==columnIndex)
            return cs.get(rowIndex).getRecordNumber();
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