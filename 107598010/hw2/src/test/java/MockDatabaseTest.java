import adapter.gateway.MemoryDatabase;
import org.junit.Before;

public class MockDatabaseTest extends SqlLiteDatabaseTest {

    @Before
    @Override
    public void setup() {
        sql = new MemoryDatabase();
        createTestCourse();
    }
}
