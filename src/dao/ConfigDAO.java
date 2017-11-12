package dao;

import java.sql.*;
import java.util.*;

import entity.Config;
import util.DBUtil;

/**
 * DAO类-Config，对config表进行增改删查操作，还做了一些冗余的方法，不再过多注释
 */

public class ConfigDAO {
    public void add(Config config) {
        String sql = "insert into config (`key_`,`value`) values (?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                config.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int update(Config config) {
        String sql = "update config set key_ = ? , value = ? where id = ?";
        int result = 0;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.setInt(3, config.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(int id) {
        String sql = "delete from config where id = ?";
        int result = 0;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Config get(int id) {
        Config config = null;
        String sql = "select * from config where id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                config = new Config(result.getInt("id"),
                        result.getString("key_"),
                        result.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }

    public Config getByKey(String key) {
        Config config = null;
        String sql = "select * from config where key_ = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, key);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                config = new Config(result.getInt("id"),
                        result.getString("key_"),
                        result.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }

    public List<Config> list(int start, int count) {
        String sql = "select * from config order by id desc limit ?,?";
        List<Config> configs = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Config config = new Config(rs.getInt("id"),
                        rs.getString("key_"),
                        rs.getString("value"));
                configs.add(config);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return configs;
    }

    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    public int getTotal() {
        String sql = "select count(*) from config";
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
