package cn.chat.ai.api.domain.zsxq;

import cn.chat.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;

import java.io.IOException;

/**
 * @author 86188
 * @version 1.0
 * @description 知识星球 API 接口
 * @data 2023/4/3 23:16
 */
public interface IZsxqApi {
    UnAnsweredQuestionAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupID, String cookie, String topicID, String text, boolean silenced) throws IOException;
}
