package projects.nyinyihtunlwin.firechat.data.models;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.firechat.FireChatApp;
import projects.nyinyihtunlwin.firechat.data.vo.ChatVO;
import projects.nyinyihtunlwin.firechat.data.vo.UserVO;
import projects.nyinyihtunlwin.firechat.events.RestApiEvents;

/**
 * Created by Dell on 1/29/2018.
 */

public class FireChatModel {

    private static final String CONVERSATIONS = "chats";
    private static final String REGISTERED_USER = "registeredUsers";

    private static FireChatModel objectInstance;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private List<ChatVO> mChatList;
    private List<UserVO> mUserList;

    private FireChatModel() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mChatList = new ArrayList<>();
        mUserList=new ArrayList<>();
    }

    public static FireChatModel getInstance() {
        if (objectInstance == null) {
            objectInstance = new FireChatModel();
        }
        return objectInstance;
    }

    public void startLoadingRegisteredUser(final Context context) {

        DatabaseReference fireChatDBR = FirebaseDatabase.getInstance().getReference();
        DatabaseReference registeredUserDBR = fireChatDBR.child(REGISTERED_USER);
        registeredUserDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<UserVO> userList = new ArrayList<>();
                for (DataSnapshot userDSS : dataSnapshot.getChildren()) {
                    UserVO user = userDSS.getValue(UserVO.class);
                    userList.add(user);
                }
                mUserList.addAll(userList);
                RestApiEvents.UserDataLoadedEvent event = new RestApiEvents.UserDataLoadedEvent(mUserList);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void startLoadingConversations(final Context context) {

        DatabaseReference fireChatDBR = FirebaseDatabase.getInstance().getReference();
        DatabaseReference conversationDBR = fireChatDBR.child(CONVERSATIONS);
        conversationDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ChatVO> chatList = new ArrayList<>();
                for (DataSnapshot chatDSS : dataSnapshot.getChildren()) {
                    ChatVO news = chatDSS.getValue(ChatVO.class);
                    chatList.add(news);
                }
                mChatList.addAll(chatList);
                RestApiEvents.ChatDataLoadedEvent event = new RestApiEvents.ChatDataLoadedEvent(mChatList);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public List<ChatVO> getmChatList() {
        return mChatList;
    }

    public List<UserVO> getmUserList() {
        return mUserList;
    }
}
