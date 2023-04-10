package cn.chat.ai.api.test;

import cn.chat.ai.api.domain.ai.IOpenAI;
import cn.chat.ai.api.domain.zsxq.IZsxqApi;
import cn.chat.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.chat.ai.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * @author 86188
 * @version 1.0
 * @description
 * @data 2023/4/4 21:42
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);
    @Value("${chatBot-api.groupId}")
    private String groupId;

    @Value("${chatBot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionAggregates));

        List<Topics> topics = unAnsweredQuestionAggregates.getResp_data().getTopics();
        for (Topics topic:topics
             ) {
            String topic_id = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId: {} text: {}",topic_id, text);

            zsxqApi.answer(groupId, cookie, topic_id, text, false);

        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response  = openAI.doChatGPT("say hello");
        logger.info("测试结果：{}",response);
    }
}
