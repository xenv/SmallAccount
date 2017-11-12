package gui.panel;
 
import java.awt.BorderLayout;
 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

/**
 * 界面类 CategoryPanel 分类页
 */

public class CategoryPanel extends WorkingPanel{
    static{
        GUIUtil.useLNF();
    }
    public static CategoryPanel instance = new CategoryPanel();
 
    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    private CategoryTableModel ctm = new CategoryTableModel();
    private JTable t =new JTable(ctm);
     
    public CategoryPanel() {
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
        GUIUtil.showPanel(CategoryPanel.instance);
    }
    public boolean checkSelected(){
        return t.getSelectedRow()>=0;
    }
    public Category getSelectedCategory(){
        int index = t.getSelectedRow();
        return ctm.cs.get(index>0?index:0);
    }
    public void updateData(){
        ctm.cs = new CategoryService().list();
        t.updateUI();
        if(0==ctm.cs.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }
    public void addListener(){
        CategoryListener categoryListener = new CategoryListener();
        bDelete.addActionListener(categoryListener);
        bEdit.addActionListener(categoryListener);
        bAdd.addActionListener(categoryListener);
    }
}