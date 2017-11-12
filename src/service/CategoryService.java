package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;


import java.util.List;

/**
 * 业务类 CategoryService 对category数据库的业务进行封装
 */

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();
    private RecordDAO recordDAO = new RecordDAO();

    public List<Category> list() {
        List<Category> cs = categoryDAO.list();
        for (Category c : cs) {
            List<Record> rs = recordDAO.list(c.getId());
            c.setRecordNumber(rs.size());
        }
        //lambda表达式实现comparator接口
        cs.sort((c1, c2) -> c2.getRecordNumber() - c1.getRecordNumber());
        return cs;
    }

    public void add(String name) {
        Category c = new Category();
        c.setName(name);
        categoryDAO.add(c);
    }

    public void update(int id, String name) {
        Category c = new Category();
        c.setId(id);
        c.setName(name);
        categoryDAO.update(c);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }
}
