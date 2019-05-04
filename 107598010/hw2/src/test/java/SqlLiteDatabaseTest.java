
import adapter.gateway.Database;
import adapter.gateway.DatabaseDto.DatabaseDto;
import adapter.gateway.SqliteDatabase;
import core.entity.Course;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.io.FileNotFoundException;


public class SqlLiteDatabaseTest {
    protected DatabaseDto course ;
    Database sql;

    public void createTestCourse() {
        course= new DatabaseDto( "SA",
                "很難喔",
                "學生",
                100,
                "無",
                "要修過ooad"
               );

    }

    @Before
    public void setup() {
        sql = new SqliteDatabase();
        createTestCourse();
    }

    @Test
    public void testConnect() {
        sql.connectDB();
    }

    @Test
    public void testRead() {
        sql.connectDB();
        sql.read();
    }

    @Test
    public void testInsert()  {

        sql.connectDB();
        System.out.println(sql.insert(course));
        System.out.println(sql.insert(course));
        sql.read();
        sql.delete(course.getCourseName());
    }

    @Test
    public void testDelete()  {

        sql.connectDB();
        sql.createTable();
        sql.insert(course);
        sql.delete("SA");
        sql.read();
    }

    @Test
    public void testSelect() throws FileNotFoundException {
        sql.connectDB();
        sql.insert(course);
        sql.read();
        //sql.delete("SA");
        System.out.println(sql.select("SA").getCourseName());
    }

    @Test
    public void testUpdate() throws FileNotFoundException, ClassNotFoundException, SQLException, UnsupportedEncodingException {

        sql.connectDB();
        sql.read();
        course= new DatabaseDto( "SA",
                "很難喔",
                "學生",
                100,
                "無",
                "要修過ooad"
        );
        sql.update(course);
        sql.read();
    }


}
