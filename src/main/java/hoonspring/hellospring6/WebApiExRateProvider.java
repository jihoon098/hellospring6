package hoonspring.hellospring6;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider{

    @Override
    public BigDecimal getExRate(String currency) throws IOException {

        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /*
         * 1. InputStream : 파일 or 네트워크에서 넘어오는 데이터를 Byte 형태로 리턴.
         * 2. InputStreamReader : Byte 데이터를 사람이 읽을 수 있는 문자(Character)로 변환하여 리턴.
         * 3. BufferedReader : Character는 단순한 문자의 나열이기 때문에, 읽기 편하게 텍스트로 변환하여 리턴.
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
