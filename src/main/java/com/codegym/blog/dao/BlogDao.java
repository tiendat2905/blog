package com.codegym.blog.dao;

import com.codegym.blog.model.Blog;
import com.codegym.blog.dao.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogDao implements IBaseDao<Blog> {

    private static final String FINDALL_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id order by publishDate desc";

    private static final String FINDBYID_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id WHERE blogs.id=?";

    private static final String FINDBYCATEGORYID_SQL = "select blogs.*,categories.nameCategory as categoryName from blogs INNER JOIN categories ON blogs.`idCategory` = categories.id WHERE categories.id=? order by publishDate desc limit 8 offset ?";

    private static final String SELECT_TOP3_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id ORDER BY blogs.`publishDate` DESC LIMIT 3";

    private static final String SELECT_TOP8_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id ORDER BY blogs.`publishDate` DESC LIMIT 8";

    private static final String SELECT_TOP5_LASTEST_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id ORDER BY blogs.`publishDate` DESC LIMIT 5";

    private static final String SELECT_TOP3_RELATED_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id WHERE categories.id=? ORDER BY blogs.`publishDate` DESC LIMIT 3";

    private static final String SELECT_TOP3_RAMDOM_SQL = "select blogs.*, categories.nameCategory as categoryName from blogs INNER JOIN categories ON blogs.`idCategory` = categories.id order by rand() limit 3";

    private static final String SELECT_TOP5_POPULAR_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id ORDER BY blogs.views DESC LIMIT 5";

    private static final String SAVE_SQL = "INSERT INTO `blog`.`blogs` (`title`, `shortContent`, `fullContent`, `imageUrl`, `idCategory`) VALUES (?, ?, ?, ?, ?);";

    private static final String UPDATE_SQL = "UPDATE blogs SET title=?,shortContent=?,fullContent=?,imageUrl=?,idCategory=? WHERE id=?";

    private static final String DELETE_SQL = "DELETE FROM blogs WHERE id=?";

    private static final String INCVIEW_SQL = "UPDATE blogs SET views = views + 1 WHERE id = ?";

    private static final String INCLIKE_SQL = "UPDATE blogs SET likes = likes + 1 WHERE id = ?";

    private static final String FINDBYNAME_SQL = "SELECT blogs.*, categories.nameCategory as categoryName FROM blogs INNER JOIN categories ON blogs.`idCategory` = categories.id WHERE blogs.title LIKE concat ('%',?,'%')";

    private static final String LIST_PAGE = "select blogs.*,categories.nameCategory as categoryName from blogs INNER JOIN categories ON blogs.`idCategory` = categories.id order by publishDate desc limit 8 offset ?;";


    public List<Blog> findAll() {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(FINDALL_SQL)) {
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }

    @Override
    public Blog findById(int id) {
        // TODO Auto-generated method stub
        Blog temp = null;

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(FINDBYID_SQL)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");


                String categoryName = rs.getString("categoryName");

                temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return temp;
    }

    public List<Blog> findByCate(int page,int idCategory) {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(FINDBYCATEGORYID_SQL)) {
            st.setInt(1, idCategory);
            st.setInt(2, (page - 1) * 8);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");

                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }

    public int getNumberPageCat(int idCategory) throws SQLException {
        String query = "select count(id) from blogs WHERE blogs.idCategory = ?";
        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setInt(1,idCategory);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int total = rs.getInt(1);

                int countPage = total / 8;
                if (total % 8 != 0) {
                    countPage++;
                }
                return countPage;
            }
            return 0;
        }
    }

    public List<Blog> findTop3() {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_TOP3_SQL)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }

    public List<Blog> findTop3Random() {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_TOP3_RAMDOM_SQL)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }


    public List<Blog> findTop5() {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_TOP5_LASTEST_SQL)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }

    public List<Blog> findTop3Related(int categoryId) {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_TOP3_RELATED_SQL)) {
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }


    public List<Blog> findTop8() {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_TOP8_SQL)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }


    public List<Blog> findTop5Popular() {
        List<Blog> rt = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_TOP5_POPULAR_SQL)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");

                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                rt.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rt;
    }

    @Override
    public void save(Blog object) {
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(SAVE_SQL)
        ) {

            statement.setString(1, object.getTitle());
            statement.setString(2, object.getShortContent());
            statement.setString(3, object.getFullContent());
            statement.setString(4, object.getImageUrl());
            statement.setInt(5, object.getIdCategory());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(Blog object) {
        try (
                Connection con = DaoUtils.getConnection();
                PreparedStatement statement = con.prepareStatement(UPDATE_SQL)
        ) {

            statement.setString(1, object.getTitle());
            statement.setString(2, object.getShortContent());
            statement.setString(3, object.getFullContent());
            statement.setString(4, object.getImageUrl());
            statement.setInt(5, object.getIdCategory());
            statement.setInt(6, object.getId());

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
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void incViews(int id) {
        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(INCVIEW_SQL)) {

            st.setInt(1, id);

            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void incLikes(int id) {
        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(INCLIKE_SQL)) {

            st.setInt(1, id);

            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Blog> findByName(String keyword) {
        List<Blog> blogList = new ArrayList<>();

        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(FINDBYNAME_SQL)) {
            st.setString(1, keyword);
            ResultSet rs = st.executeQuery();


            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("shortContent");
                String fullContent = rs.getString("fullContent");
                Date publishDate = rs.getDate("publishDate");
                String imageUrl = rs.getString("imageUrl");
                int views = rs.getInt("views");
                int likes = rs.getInt("likes");
                int idCategory = rs.getInt("idCategory");


                String categoryName = rs.getString("categoryName");

                Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                blogList.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return blogList;
    }

    public List<Blog> listPage(int page) throws SQLException {
        List<Blog> listPage = new ArrayList<>();
        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(LIST_PAGE)) {
            st.setInt(1, (page - 1) * 8);
            try {
                System.out.println(st);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    String title = resultSet.getString("title");
                    String shortContent = resultSet.getString("shortContent");
                    String fullContent = resultSet.getString("fullContent");
                    Date publishDate = resultSet.getDate("publishDate");
                    String imageUrl = resultSet.getString("imageUrl");
                    int views = resultSet.getInt("views");
                    int likes = resultSet.getInt("likes");
                    int idCategory = resultSet.getInt("idCategory");

                    String categoryName = resultSet.getString("categoryName");

                    Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
                    listPage.add(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listPage;
        }
    }

    public int getNumberPage() throws SQLException {
        String query = "select count(id) from blogs;";
        try (Connection connection = DaoUtils.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int total = rs.getInt(1);

                int countPage = total / 8;
                if (total % 8 != 0) {
                    countPage++;
                }
                return countPage;
            }
            return 0;
        }
    }
}

