package cn.chat.ai.api.domain.zsxq.model.aggregates;

import cn.chat.ai.api.domain.zsxq.model.res.RespData;

/**
 * @author 86188
 * @version 1.0
 * @description 未回答问题的聚合信息
 * @data 2023/4/3 23:44
 */
public class UnAnsweredQuestionAggregates {

    private boolean succeeded;
    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }
}
