package cn.chat.ai.api.domain.ai;

import java.io.IOException;

/**
 * @author 86188
 * @version 1.0
 * @description chatGPT OpenAI接口
 * @data 2023/4/10 9:42
 */
public interface IOpenAI {

    String doChatGPT(String question) throws IOException;
}
