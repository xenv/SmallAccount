import dao.CategoryDAO;
import dao.ConfigDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Config;
import entity.Record;
import util.DBUtil;
import util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test2 {
    public static void main(String[] args){
        System.out.println(new RecordDAO().getTotal());
    }

}
