package gui.model;

import entity.Category;
import service.CategoryService;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 * CategoryPanel 的 ComboBox 的数据模型，实现了 ComboBoxModel<> 接口
 *
 * @see gui.panel.CategoryPanel
 */

public class CategoryComboBoxModel implements ComboBoxModel<Category> {
    //从service层获取数据
    public List<Category> cs = new CategoryService().list();
    public Category c;

    public CategoryComboBoxModel() {
        if (!cs.isEmpty())
            c = cs.get(0);
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
    //被选中就把选中的对象存到模型
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    //获取选中的时候从模型读出
    public Object getSelectedItem() {
        return c;
    }

}