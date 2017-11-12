package util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 工具类 SQLUtil 数据库导入，导出和清空
 *
 * @author xenv
 */
public class SQLUtil {
    /**
     * 数据库源文件对象
     */
    private static final File SQLFile = new File(DBUtil.database);

    /**
     * 直接复制数据库文件到指定文件
     *
     * @param filePath : 导出数据库的文件路径，是以.db结尾的绝对路径
     * @throws IOException : 导出文件中可能遇到IO错误
     */
    public static void backup(String filePath) throws IOException {
        //获取目标文件对象
        File toFile = new File(filePath);
        //新建字节流
        try (FileInputStream is = new FileInputStream(SQLFile);
             FileOutputStream os = new FileOutputStream(toFile)) {
            byte[] bytes = new byte[(int) SQLFile.length()];
            //读取字节数组
            int result = is.read(bytes);
            //写出字节数组
            os.write(bytes);
            //清理
            os.flush();
        }
    }

    /**
     * 从指定文件导入到数据库文件
     *
     * @param filePath 指定备份文件的路径
     * @throws IOException : 导出文件中可能遇到IO错误
     */
    public static void recover(String filePath) throws IOException {
        //获取来源文件对象
        File fromFile = new File(filePath);
        //字节流复制文件
        try (FileInputStream is = new FileInputStream(fromFile);
             FileOutputStream os = new FileOutputStream(SQLFile)) {
            byte[] bytes = new byte[(int) fromFile.length()];
            //读取字节数组
            int result = is.read(bytes);
            //写出字节数组
            os.write(bytes);
            //清理
            os.flush();
        }
    }

    /**
     * 清空数据库，重置自增值
     */
    public static void truncate(){
        try(Connection c = DBUtil.getConnection();
            Statement s = c.createStatement()){
            s.execute("delete from config;");
            s.execute("delete from record;");
            s.execute("delete from category;");
            s.execute("delete from sqlite_sequence;");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
