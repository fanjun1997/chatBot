package cn.chat.ai.api.domain.zsxq.model.res;

import cn.chat.ai.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @author 86188
 * @version 1.0
 * @description
 * @data 2023/4/3 23:46
 */
public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics(){
        return topics;
    }

    public void setTopics(List<Topics> topics){
        this.topics = topics;
    }
}
