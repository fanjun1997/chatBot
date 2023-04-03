package cn.chat.ai.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author 86188
 * @version 1.0
 * @data 2023/4/3 16:27
 */
public class ApiTest {
    @Test
    public void query_unanswered_question() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88885824448182/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie","zsxq_access_token=D779482E-FB3F-B9F3-0E72-C85F5B039E3E_35DE15DC136A90F2; abtest_env=product; zsxqsessionid=6ada0bb2af31f8cc695f3fab61873000");
        get.addHeader("content-Type","application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/181454588811482/answer");
        post.addHeader("cookie","zsxq_access_token=D779482E-FB3F-B9F3-0E72-C85F5B039E3E_35DE15DC136A90F2; abtest_env=product; zsxqsessionid=6ada0bb2af31f8cc695f3fab61873000");
        post.addHeader("content-Type","application/json;charset=utf8");

        String paramJSON = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"测试1\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJSON, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
