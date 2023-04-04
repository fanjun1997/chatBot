package cn.chat.ai.api.domain.zsxq.model.req;

/**
 * @author 86188
 * @version 1.0
 * @description
 * @data 2023/4/4 0:40
 */
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }
}
