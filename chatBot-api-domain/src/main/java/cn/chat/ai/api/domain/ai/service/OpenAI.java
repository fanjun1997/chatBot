package cn.chat.ai.api.domain.ai.service;

import cn.chat.ai.api.domain.ai.IOpenAI;
import cn.chat.ai.api.domain.ai.model.aggregates.AIAnswer;
import cn.chat.ai.api.domain.ai.model.vo.Choices;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author 86188
 * @version 1.0
 * @description IOpenAI 接口实现
 * @data 2023/4/10 9:43
 */
@Service
public class OpenAI implements IOpenAI {
    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Value("${chatBot-api.OpenAiKey}")
    private String OpenAiKey;

    @Override
    public String doChatGPT(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+OpenAiKey);//密钥key

        String paramJSON = "{\"model\":\"text-davinci-003\",\"prompt\":\""+question+"\",\"temperature\":0,\"max_tokens\":1024}";
        // NSDictionary *params = @{@"model":@"text-davinci-003", @"prompt": prompt, @"max_tokens": @(64), @"temperature": @(0.5)};

        StringEntity stringEntity = new StringEntity(paramJSON, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for(Choices choice : choices){
                answers.append(choice.getText());
            }
            return answers.toString();
        }else{
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }

    }
}
