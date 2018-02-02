package projects.nyinyihtunlwin.firechat.data.vo;

import java.util.Map;

/**
 * Created by Dell on 1/30/2018.
 */

public class ChatVO {

    private String chatId;
    private String startedAt;
    private Map<String,ConversationVO> conversations;
    private Map<String,UserVO> userList;

    public String getChatId() {
        return chatId;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public Map<String,ConversationVO> getConversations() {
        return conversations;
    }

    public Map<String,UserVO> getUserList() {
        return userList;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public void setConversations(Map<String, ConversationVO> conversations) {
        this.conversations = conversations;
    }

    public void setUserList(Map<String, UserVO> userList) {
        this.userList = userList;
    }
}
