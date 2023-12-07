package hello.springmvc.basic.request;

import static org.springframework.util.StreamUtils.copyToString;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}", helloData.getUsername(),helloData.getAge());
    }

    @ResponseBody
    @PostMapping("/request-body-string-v2")
    public String requestBodyStringV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}", helloData.getUsername(),helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-string-v3")
    public String requestBodyStringV3(@RequestBody HelloData helloData) {
        log.info("helloData={}", helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(HttpEntity<HelloData> helloData) {
        HelloData data = helloData.getBody();
        log.info("helloData={}", data.getUsername(),data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public HelloData requestBodyStringV5(@RequestBody HelloData helloData) {
        log.info("helloData={}", helloData.getUsername(),helloData.getAge());
        return helloData;
    }
}
