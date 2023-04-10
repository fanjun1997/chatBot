package cn.chat.ai.api.test;

import cn.chat.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
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
            // UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = JSON.parseObject(res, UnAnsweredQuestionsAggregates.class);
            // System.out.println(unAnsweredQuestionsAggregates.toString());
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/584515525858114/answer");
        post.addHeader("cookie","zsxq_access_token=D779482E-FB3F-B9F3-0E72-C85F5B039E3E_35DE15DC136A90F2; abtest_env=product; zsxqsessionid=6ada0bb2af31f8cc695f3fab61873000");
        post.addHeader("content-Type","application/json;charset=utf8");

        String paramJSON = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"测试2\\n\",\n" +
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

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");//密钥key

        String paramJSON = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";
        // NSDictionary *params = @{@"model":@"text-davinci-003", @"prompt": prompt, @"max_tokens": @(64), @"temperature": @(0.5)};

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

    @Test
    public void test_GPT() throws IOException {

                String apiKey = "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";

                String prompt = "say hello";

                try {

                    String result = generateText(apiKey, prompt);

                    System.out.println(result);

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

            private static String generateText(String apiKey, String prompt) throws IOException {

                HttpClient httpClient = HttpClients.createDefault();

                HttpPost httpPost = new HttpPost("https://api.openai.com/v1/engines/davinci-codex/completions");

                httpPost.setHeader("Content-Type", "application/json");

                httpPost.setHeader("Authorization", "Bearer " + apiKey);

                String jsonInput = "{"

                        + "\"prompt\": \"" + prompt + "\","

                        + "\"max_tokens\": 50,"

                        + "\"n\": 1,"

                        + "\"stop\": null,"

                        + "\"temperature\": 1.0"

                        + "}";

                httpPost.setEntity(new StringEntity(jsonInput));

                HttpResponse response = httpClient.execute(httpPost);

                String jsonResponse = EntityUtils.toString(response.getEntity());
                System.out.println(jsonResponse.toString());

                return jsonResponse;

            }

}
