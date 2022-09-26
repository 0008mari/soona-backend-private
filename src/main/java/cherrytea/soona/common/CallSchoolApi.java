package cherrytea.soona.common;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Setter
@RequiredArgsConstructor
public class CallSchoolApi {

    public String callSchoolApi(String schoolDistrictCode, String schoolId) throws IOException {

        StringBuilder urlBuilder = new StringBuilder("https://open.neis.go.kr/hub/SchoolSchedule");

//        @Value("${spring.env.neisKey}")
//        String neisKey; 왜.. 안돼

        urlBuilder.append("?" + URLEncoder.encode("KEY", "UTF-8") + "=" + "96cabd43e65946f1aaf6d7bd9173f493");
        urlBuilder.append("&" + URLEncoder.encode("Type", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pIndex", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // default
        urlBuilder.append("&" + URLEncoder.encode("pSize", "UTF-8") +  "=" + URLEncoder.encode("100", "UTF-8")); // default
        urlBuilder.append("&" + URLEncoder.encode("ATPT_OFCDC_SC_CODE", "UTF-8") +  "=" + URLEncoder.encode(schoolDistrictCode, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("SD_SCHUL_CODE", "UTF-8") + "=" + URLEncoder.encode(schoolId, "UTF-8"));

        // URL 객체
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response Code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        System.out.println(sb.toString());
        return sb.toString();

    }

}
