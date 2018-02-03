package projects.nyinyihtunlwin.firechat.events;

import android.content.Context;

import java.util.List;

import projects.nyinyihtunlwin.firechat.data.vo.ChatVO;
import projects.nyinyihtunlwin.firechat.data.vo.ConversationVO;
import projects.nyinyihtunlwin.firechat.data.vo.UserVO;

/**
 * Created by Dell on 12/2/2017.
 */

public class RestApiEvents {
    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class ChatDataLoadedEvent {
        private List<ConversationVO> loadedConversations;

        public ChatDataLoadedEvent(List<ConversationVO> loadedConversations) {
            this.loadedConversations = loadedConversations;
        }

        public List<ConversationVO> getLoadedConversations() {
            return loadedConversations;
        }
    }

    public static class UserDataLoadedEvent {
        private List<UserVO> loadedUser;

        public UserDataLoadedEvent(List<UserVO> loadedUser) {
            this.loadedUser = loadedUser;
        }

        public List<UserVO> getLoadedUser() {
            return loadedUser;
        }
    }
}
