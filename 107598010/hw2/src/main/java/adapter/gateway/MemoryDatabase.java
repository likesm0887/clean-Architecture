package adapter.gateway;

import adapter.gateway.DatabaseDto.DatabaseDto;
import core.entity.Course;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MemoryDatabase implements Database {
    private Connection sqlDatabase;
    List<DatabaseDto> mockCourseDatabase = new ArrayList<>();


    @Override
    public void connectDB() {

    }

    @Override
    public DatabaseDto select(String courseName) {
        for (DatabaseDto databaseDto : mockCourseDatabase) {
            if (databaseDto.getCourseName().equals(courseName)) {
                    return new DatabaseDto(databaseDto.getCourseName(),
                        databaseDto.getCourseDescription(),
                        databaseDto.getCourseTarget(),
                        databaseDto.getCoursePrice(),
                        databaseDto.getCourseAttentionNote(),
                        databaseDto.getCourseNote());
            }
        }
        return null;
    }

    @Override
    public int insert(DatabaseDto course) {
        int databaseRecordsLengthTemp =  mockCourseDatabase.size();
        mockCourseDatabase.add(course);
        databaseRecordsLengthTemp -= mockCourseDatabase.size();
        return databaseRecordsLengthTemp;
    }

    @Override
    public void createTable() {

    }

    @Override
    public List<DatabaseDto> read() {
        List<DatabaseDto> courseList = new ArrayList<>();
        for (DatabaseDto course : mockCourseDatabase) {
            courseList.add(new DatabaseDto(course.getCourseName(),
                    course.getCourseDescription(),
                    course.getCourseTarget(),
                    course.getCoursePrice(),
                    course.getCourseAttentionNote(),
                    course.getCourseNote()));

        }
        return courseList;
    }

    @Override
    public int delete(String courseName) {
        for (int i = 0; i < mockCourseDatabase.size(); i++) {
            if (mockCourseDatabase.get(i).equals(courseName)) {
                mockCourseDatabase.remove(i);
            }
        }
        return 1;
    }

    @Override
    public int update(DatabaseDto course) {
        for (int i = 0; i < mockCourseDatabase.size(); i++) {
            if (mockCourseDatabase.get(i).equals(course.getCourseName())) {
                mockCourseDatabase.get(i).setCourseDescription(course.getCourseDescription());
                mockCourseDatabase.get(i).setCourseTarget(course.getCourseTarget());
                mockCourseDatabase.get(i).setCoursePrice(course.getCoursePrice());
                mockCourseDatabase.get(i).setCourseAttentionNote(course.getCourseAttentionNote());
                mockCourseDatabase.get(i).setCourseNote(course.getCourseName());
            }
        }
        return 0;
    }
}
