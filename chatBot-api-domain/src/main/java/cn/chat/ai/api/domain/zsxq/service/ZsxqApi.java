package cn.chat.ai.api.domain.zsxq.service;

import cn.chat.ai.api.domain.zsxq.IZsxqApi;
import cn.chat.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.chat.ai.api.domain.zsxq.model.req.AnswerReq;
import cn.chat.ai.api.domain.zsxq.model.req.ReqData;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author 86188
 * @version 1.0
 * @description
 * @data 2023/4/3 23:53
 */
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);
    @Override
    public UnAnsweredQuestionAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie",cookie);
        get.addHeader("content-Type","application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(jsonStr, UnAnsweredQuestionAggregates.class);
        }else{
            throw new RuntimeException("queryUnAnswerQuestionsTopic Err Code is" + response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public boolean answer(String groupID, String cookie, String topicID, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topicID+"/answer");
        post.addHeader("cookie",cookie);
        post.addHeader("content-Type","application/json;charset=utf8");
        post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.62");

        // String paramJSON = "{\n" +
        //         "  \"req_data\": {\n" +
        //         "    \"text\": \"测试2\\n\",\n" +
        //         "    \"image_ids\": [],\n" +
        //         "    \"silenced\": false\n" +
        //         "  }\n" +
        //         "}";

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJSON = JSONObject.fromObject(answerReq).toString();

        StringEntity stringEntity = new StringEntity(paramJSON, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

        return ;
    }
}
