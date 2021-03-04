package com.codegym.blog.dao;

import com.codegym.blog.model.User;
import com.codegym.blog.dao.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IBaseDao<User> {

    private static final String FINDALL_SQL = "SELECT * FROM users;";

    private static final String FINDBYID_SQL = "SELECT * FROM users where id = ?;";

    private static final String SAVE_SQL = "INSERT INTO users(username,password,fullName,status) VALUES (?,?,?,?);";

    private static final String UPDATE_SQL = "UPDATE users SET username = ?,fullname = ? WHERE id = ?;";

    private static final String DELETE_SQL = "DELETE FROM users where id = ?;";

    private static final String FINDBYUP_SQL = "SELECT * FROM users WHERE username=? AND password=?";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(FINDALL_SQL)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                String username = resultSet.getString("username");
                users.add(new User(id, username, fullName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(FINDBYID_SQL)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String username = resultSet.getString("username");
                user = new User(id, username, fullName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User findByUsernamePassword(String username, String password) {
        User user = null;
        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(FINDBYUP_SQL)
        ) {
            //THIET LAP THAM SO
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString("fullname");
                int id = rs.getInt("id");
                user = new User(id, username, fullname);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public void save(User object) {
        try (
                Connection connection = DaoUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(SAVE_SQL)
        ) {
            statement.setString(1, object.getUsername());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getUsername());
            statement.setInt(4, object.getStatus());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User object) {
        try (
                Connection connection = DaoUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)
        ) {
            statement.setString(1, object.getUsername());
            statement.setString(2, object.getFullName());
            statement.setInt(3, object.getId());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (
                Connection connection = DaoUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_SQL)
        ) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
