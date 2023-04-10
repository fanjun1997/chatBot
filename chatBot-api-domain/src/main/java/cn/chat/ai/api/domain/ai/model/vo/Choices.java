package cn.chat.ai.api.domain.ai.model.vo;

/**
 * @author 86188
 * @version 1.0
 * @description 选择
 * @data 2023/4/10 9:46
 */
public class Choices {
    private String text;

    private int index;

    private String logprobs;

    private String finish_reason;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }
}
