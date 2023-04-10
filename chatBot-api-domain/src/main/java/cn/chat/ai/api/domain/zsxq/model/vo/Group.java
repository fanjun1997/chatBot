package cn.chat.ai.api.domain.zsxq.model.vo;

/**
 * @author 86188
 * @version 1.0
 * @description 基础类
 * @data 2023/4/3 23:22
 */
public class Group
{
    private String group_id;

    private String name;

    private String type;

    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }
    public String getGroup_id(){
        return this.group_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
