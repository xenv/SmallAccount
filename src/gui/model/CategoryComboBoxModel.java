package gui.model;
 
import entity.Category;
import service.CategoryService;

import java.util.ArrayList;
import java.util.List;
 
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
 
public class CategoryComboBoxModel implements ComboBoxModel<Category>{
    public List<Category> cs = new CategoryService().list();
    public Category c;
    public CategoryComboBoxModel(){
        if(!cs.isEmpty())
            c=cs.get(0);
    }
     
    @Override
    public int getSize() {
        return cs.size();
    }
 
    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }
 
    @Override
    public void addListDataListener(ListDataListener l) {


    }
 
    @Override
    public void removeListDataListener(ListDataListener l) {

         
    }
 
    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
         
    }
 
    @Override
    public Object getSelectedItem() {

        return c;
    }
 
}