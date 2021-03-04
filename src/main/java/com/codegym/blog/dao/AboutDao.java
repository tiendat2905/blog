package com.codegym.blog.dao;

import com.codegym.blog.model.About;
import com.codegym.blog.model.Category;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AboutDao implements IBaseDao<About> {

    private static final String FINDALL_SQL = "SELECT * FROM about";

    private static final String FINDBYID_SQL = "SELECT * FROM about WHERE id = ?";

    private static final String GETABOUT_SQL="SELECT * FROM about LIMIT 1";

    @Override
    public List<About> findAll() {
        List<About> list = new ArrayList<>();
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(FINDALL_SQL)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String shortContent = resultSet.getString("shortContent");
                String content = resultSet.getString("content");
                String imageUrl = resultSet.getString("imageUrl");
                list.add(new About(id, title, shortContent,content,imageUrl));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public About findById(int id) {
        About about = null;
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(FINDBYID_SQL)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String shortContent = resultSet.getString("shortContent");
                String content = resultSet.getString("content");
                String imageUrl = resultSet.getString("imageUrl");
                about = new About(id, title, shortContent,content,imageUrl);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return about;
    }

    @Override
    public void save(About object) {

    }

    @Override
    public void update(About object) {

    }

    @Override
    public void delete(int id) {

    }

    public About getAbout() {
        About temp = null;

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(GETABOUT_SQL)) {
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("shortcontent");
                String fullContent = rs.getString("content");
                String imageUrl= rs.getString("imageurl");

                temp = new About(id, title, shortContent,fullContent,imageUrl);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return temp;
    }
}
