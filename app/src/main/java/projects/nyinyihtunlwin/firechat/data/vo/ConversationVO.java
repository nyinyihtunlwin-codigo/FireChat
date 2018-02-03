package projects.nyinyihtunlwin.firechat.data.vo;

/**
 * Created by Dell on 1/31/2018.
 */

public class ConversationVO {

    private String byUser;
    private String conversationId;
    private String msg;
    private String photoMsg;
    private String timestamp;

    public ConversationVO(){}

    public ConversationVO(String byUser, String conversationId, String msg, String photoMsg, String timestamp) {
        this.byUser = byUser;
        this.conversationId = conversationId;
        this.msg = msg;
        this.photoMsg = photoMsg;
        this.timestamp = timestamp;
    }

    public String getByUser() {
        return byUser;
    }

    public String getConversationId() {
        return conversationId;
    }

    public String getMsg() {
        return msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPhotoMsg() {
        return photoMsg;
    }


}
