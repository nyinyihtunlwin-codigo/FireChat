package projects.nyinyihtunlwin.firechat.mvp.presenters;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import projects.nyinyihtunlwin.firechat.data.models.FireChatModel;
import projects.nyinyihtunlwin.firechat.data.vo.UserVO;
import projects.nyinyihtunlwin.firechat.mvp.views.ChatListView;

/**
 * Created by Dell on 2/1/2018.
 */

public class ChatListPresenter extends BasePresenter<ChatListView> {

    private static ChatListPresenter objectInstance;

    private UserVO mPartner;


    private ChatListPresenter() {
    }

    public static ChatListPresenter getInstance() {
        if (objectInstance == null) {
            objectInstance = new ChatListPresenter();
        }
        return objectInstance;
    }

    @Override
    public void onCreate(ChatListView view) {
        super.onCreate(view);
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void onTapConversation(UserVO partner) {

        mPartner= partner;

        if (FireChatModel.getInstance().isUserAuthenticate()) {
            mView.navigateToConversationScreen(partner);
        } else {
            mView.showAuthenticationDialog();
        }
    }

    public void onSuccessGoogleSignIn(GoogleSignInAccount signInAccount) {
        FireChatModel.getInstance().authenticateUserWithGoogleAccount(signInAccount, new FireChatModel.UserAuthenticateDelegate() {
            @Override
            public void onSuccessAuthenticate(GoogleSignInAccount signInAccount) {
                mView.navigateToConversationScreen(mPartner);
            }

            @Override
            public void onFailureAuthenticate(String errrorMsg) {
                mView.showErrorInfo(errrorMsg);
            }
        });
    }
}
