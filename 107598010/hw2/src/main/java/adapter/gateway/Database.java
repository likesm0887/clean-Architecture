package adapter.gateway;

import adapter.gateway.DatabaseDto.DatabaseDto;
import core.entity.Course;

import java.util.List;

public interface Database {

    void connectDB();

    int insert(DatabaseDto course);

    void createTable();

    List<DatabaseDto> read();

    int delete(String courseName);

    int update(DatabaseDto course);

    DatabaseDto select(String courseName);
}
