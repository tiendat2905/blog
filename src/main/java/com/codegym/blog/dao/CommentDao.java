package com.codegym.blog.dao;

import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDao {

    private static final String FINDALL_SQL = "SELECT comments.*, blogs.title as blogName FROM comments INNER JOIN blogs ON comments.idBlog = blogs.id WHERE blogs.id = ? ";

    private static final String SAVE_SQL = "INSERT INTO `blog`.`comments` (`idBlog`, `username`, `email`, `comment`) VALUES (?, ?, ?, ?);";

    public List<Comment> findAll(int idBlog) {
        List<Comment> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(FINDALL_SQL)) {
            st.setInt(1,idBlog);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");

                String blogName = rs.getString("blogName");
                String username = rs.getString("username");
                String email = rs.getString("email");
                Date pushDate = rs.getDate("pushDate");
                String comment = rs.getString("comment");

                Comment temp = new Comment(id, idBlog,blogName,username,email,pushDate,comment);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rt;
    }

    public void save(Comment object) {
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(SAVE_SQL)
        ) {

            statement.setInt(1, object.getIdBlog());
            statement.setString(2, object.getUsername());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getComment());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
