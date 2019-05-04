package core;

import adapter.gateway.DatabaseDto.DatabaseDto;
import core.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class DataMapper {
    public DataMapper() {
    }
    public Course convertDataMapperToCourse(DatabaseDto databaseDto)
    {

        return  new Course(databaseDto.getCourseName(),
                databaseDto.getCourseDescription(),
                databaseDto.getCourseTarget(),
                databaseDto.getCoursePrice(),
                databaseDto.getCourseAttentionNote(),
                databaseDto.getCourseNote());
    }
    public List<Course>  convertDataMapperToCourse(List<DatabaseDto> databaseDtos)
    {
        List<Course> courses = new ArrayList<>();
        for (DatabaseDto databaseDto: databaseDtos)
        {
            courses.add(convertDataMapperToCourse(databaseDto));
        }
        return courses;
    }
    public List<DatabaseDto>  convertCourseToDatabaseDto(List<Course> courses)
    {
        List<DatabaseDto> databaseDtos = new ArrayList<>();
        for (Course course: courses)
        {
            databaseDtos.add(convertCourseToDatabaseDto(course));
        }
        return databaseDtos;
    }
    public DatabaseDto convertCourseToDatabaseDto(Course course)
    {
        return  new DatabaseDto(course.getCourseName(),
                course.getCourseDescription(),
                course.getCourseTarget(),
                course.getCoursePrice(),
                course.getCourseAttentionNote(),
                course.getCourseNote());
    }


}
