package com.codegym.blog.dao;

import com.codegym.blog.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICategoryDao implements IBaseDao<Category> {
    private static final String FINDALL_SQL = "SELECT * FROM categories";
    private static final String FINDBYID_SQL = "SELECT * FROM categories WHERE id=?";


    private static final String SAVE_SQL = "INSERT INTO categories(nameCategory,description) VALUES(?,?)";
    private static final String UPDATE_SQL = "UPDATE categories SET nameCategory=?,description=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM categories WHERE id=?";

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(FINDALL_SQL)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nameCategory");
                String description = resultSet.getString("description");
                list.add(new Category(id, name, description));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(FINDBYID_SQL)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("nameCategory");
                String description = resultSet.getString("description");
                category = new Category(id, name, description);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    @Override
    public void save(Category object) {
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(SAVE_SQL)
        ) {

            statement.setString(1, object.getNameCategory());
            statement.setString(2, object.getDescription());
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Category object) {
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(UPDATE_SQL)
        ) {

            statement.setString(1, object.getNameCategory());
            statement.setString(2, object.getDescription());
            statement.setInt(3,object.getId());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(DELETE_SQL)
        ) {
            statement.setInt(1,id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
