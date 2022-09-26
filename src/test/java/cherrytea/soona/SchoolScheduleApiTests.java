package cherrytea.soona;

import cherrytea.soona.common.CallSchoolApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolScheduleApiTests {

    @Test
    public void CallApiTest() throws IOException {

        CallSchoolApi callSchoolApi = new CallSchoolApi();
        String xxx = callSchoolApi.callSchoolApi("B10", "7010057");

    }
}
