package adapter.gateway;

import adapter.gateway.DatabaseDto.DatabaseDto;
import core.entity.Course;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase implements Database {

    private Connection sqlDatabase;


    @Override
    public void connectDB() {
        SQLiteConfig config = new SQLiteConfig();
        // config.setReadOnly(true);
        config.setSharedCache(true);
        config.enableRecursiveTriggers(true);
        SQLiteDataSource ds = new SQLiteDataSource(config);
        ds.setUrl("jdbc:sqlite:test.db");
        try {
            sqlDatabase = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        String sql = "DROP TABLE IF EXISTS test ;create table test (courseName STRING PRIMARY KEY NOT NULL,courseDescription string,courseTarget string,coursePrice interge,courseAttentionNote string,courseNote string); ";
        Statement stat = null;
        try {
            stat = sqlDatabase.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int insert(DatabaseDto course) {
        connectDB();
        String sql = "insert into test (courseName,courseDescription,courseTarget,coursePrice,courseAttentionNote,courseNote) values(?,?,?,?,?,?)";
        PreparedStatement pst ;
        try {
            pst = sqlDatabase.prepareStatement(sql);
            int idx = 1;
            pst.setString(idx++, course.getCourseName());
            pst.setString(idx++, course.getCourseDescription());
            pst.setString(idx++, course.getCourseTarget());
            pst.setInt(idx++, course.getCoursePrice());
            pst.setString(idx++, course.getCourseAttentionNote());
            pst.setString(idx++, course.getCourseNote());
            return pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Primary Key 重複");
        }
        return 0;
    }

    @Override
    public List<DatabaseDto> read() {
        this.connectDB();
        String sql = "select * from test ";
        Statement stat = null;
        ResultSet rs = null;
        List<DatabaseDto> courseList = new ArrayList<>();
        try {
            stat = sqlDatabase.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("courseName") + "\t" + rs.getString("courseDescription") + "\t" + rs.getString("courseTarget") + "\t" + rs.getInt("coursePrice") + "\t" + rs.getString("courseAttentionNote") + "\t" + rs.getString("courseNote"));
                courseList.add(new DatabaseDto(rs.getString("courseName")
                        , rs.getString("courseDescription")
                        , rs.getString("courseTarget")
                        , rs.getInt("coursePrice")
                        , rs.getString("courseAttentionNote")
                        , rs.getString("courseNote")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    @Override
    public int delete(String courseName) {
        String sql = "delete from test where courseName = ?";
        if (select(courseName) == null) {
            throw new NullPointerException();
        }
        PreparedStatement pst = null;
        try {
            pst = sqlDatabase.prepareStatement(sql);
            int idx = 1;
            pst.setString(idx++, courseName);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(DatabaseDto course) {
        String sql = "update test set courseDescription = ? , courseTarget = ? ,coursePrice= ?  ,courseAttentionNote = ? , courseNote =?   WHERE courseName = ?";
        PreparedStatement pst = null;
        try {
            pst = sqlDatabase.prepareStatement(sql);
            int idx = 1;
            pst.setString(idx++, course.getCourseDescription());
            pst.setString(idx++, course.getCourseTarget());
            pst.setInt(idx++, course.getCoursePrice());
            pst.setString(idx++, course.getCourseAttentionNote());
            pst.setString(idx++, course.getCourseNote());
            pst.setString(idx++, course.getCourseName());
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DatabaseDto select(String courseName) {
        this.connectDB();
        String sql = "select courseDescription ,courseTarget ,coursePrice ,courseAttentionNote , courseNote   from test WHERE  courseName = ?";
        ResultSet rs = null;
        PreparedStatement pst = null;
        DatabaseDto courseTemp = null;
        try {
            pst = sqlDatabase.prepareStatement(sql);
            pst.setString(1, courseName);
            rs = pst.executeQuery();
            while (rs.next()) {
                courseTemp = new DatabaseDto(courseName
                        , rs.getString("courseDescription")
                        , rs.getString("courseTarget")
                        , rs.getInt("coursePrice")
                        , rs.getString("courseAttentionNote")
                        , rs.getString("courseNote"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseTemp;
    }
}
