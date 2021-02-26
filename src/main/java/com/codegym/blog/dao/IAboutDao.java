package com.codegym.blog.dao;

import com.codegym.blog.model.About;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class IAboutDao implements IBaseDao<About> {

    private static final String GETABOUT_SQL="SELECT * FROM about LIMIT 1";

    @Override
    public List<About> findAll() {
        return null;
    }

    @Override
    public About findById(int id) {
        return null;
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

            while (rs.next()) {
                String title = rs.getString("title");
                String shortContent = rs.getString("shortcontent");
                String fullContent = rs.getString("content");
                String imageUrl= rs.getString("imageurl");

                temp = new About(0, title, shortContent,fullContent,imageUrl);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return temp;
    }
}
