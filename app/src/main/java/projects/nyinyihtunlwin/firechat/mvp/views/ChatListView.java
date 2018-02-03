package projects.nyinyihtunlwin.firechat.mvp.views;

import android.content.Context;

import java.util.List;

import projects.nyinyihtunlwin.firechat.data.vo.ChatVO;
import projects.nyinyihtunlwin.firechat.data.vo.UserVO;

/**
 * Created by Dell on 2/1/2018.
 */

public interface ChatListView {

    void displayChatList(List<ChatVO> chatList);

    void showLoding();

    Context getContext();

    void navigateToConversationScreen(UserVO partner);

    void showAuthenticationDialog();

    void showErrorInfo(String message);
}
