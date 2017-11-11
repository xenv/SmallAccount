package gui.model;
 
import entity.Category;
import service.CategoryService;

import java.util.ArrayList;
import java.util.List;
 
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
 
public class CategoryTableModel implements TableModel{
 
    String[] columnNames = new String[]{"分类名称","消费次数"};
    public List<Category> cs = new CategoryService().list();

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return cs.size();
    }
 
    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
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