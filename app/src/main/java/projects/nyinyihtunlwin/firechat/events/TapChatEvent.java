package projects.nyinyihtunlwin.firechat.events;

/**
 * Created by Dell on 12/2/2017.
 */

public class TapChatEvent {
    String conversationId;

    public TapChatEvent(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
