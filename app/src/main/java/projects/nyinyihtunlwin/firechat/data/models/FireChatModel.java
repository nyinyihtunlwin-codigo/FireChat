package projects.nyinyihtunlwin.firechat.data.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private String currentUserId;
    private int i = 0;

    private FireChatModel() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mChatList = new ArrayList<>();
        mUserList = new ArrayList<>();
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


    public void startConversation(final UserVO userVO) {

        final DatabaseReference fireChatDBR = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference conversationDBR = fireChatDBR.child(CONVERSATIONS);
        conversationDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ChatVO> chatList = new ArrayList<>();
                for (DataSnapshot chatDSS : dataSnapshot.getChildren()) {
                    ChatVO chat = chatDSS.getValue(ChatVO.class);
                    chatList.add(chat);
                }
                for (ChatVO chatVO : chatList) {
                    for (UserVO user : chatVO.getUserList().values()) {
                        if (user.getUserId() == userVO.getUserId()) {
                            i++;
                        }
                        if (user.getUserId() == currentUserId) {
                            i++;
                        }
                    }
                }
                DatabaseReference userDBR = fireChatDBR.child(REGISTERED_USER).child(currentUserId);
                userDBR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final Map<String, UserVO> userList = new HashMap<>();
                        userList.put(userVO.getUserId(), userVO);

                        UserVO currentUser = dataSnapshot.getValue(UserVO.class);
                        userList.put(currentUser.getUserId(), currentUser);

                        Log.e("UID", userList.size() + "");


                        if (i == 0) {
                            ChatVO chatVO = new ChatVO();
                            chatVO.setChatId(userVO.getUserId() + userVO.getUserId());
                            chatVO.setStartedAt(new Date().toString());
                            chatVO.setUserList(userList);
                            conversationDBR.child(chatVO.getChatId()).setValue(chatVO);
                        } else if (i == 2) {

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public boolean isUserAuthenticate() {
        return mFirebaseUser != null;
    }


    public void authenticateUserWithGoogleAccount(final GoogleSignInAccount signInAccount, final UserAuthenticateDelegate delegate) {
        Log.d(FireChatApp.LOG, "signInAccount Id :" + signInAccount.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(FireChatApp.LOG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(FireChatApp.LOG, "signInWithCredential", task.getException());
                            delegate.onFailureAuthenticate(task.getException().getMessage());
                        } else {
                            mFirebaseUser = mFirebaseAuth.getCurrentUser();
                            Log.d(FireChatApp.LOG, "signInWithCredential - successful");
                            delegate.onSuccessAuthenticate(signInAccount);

                            UserVO userVO = new UserVO();
                            userVO.setEmail(signInAccount.getEmail());
                            userVO.setCoverUrl(signInAccount.getPhotoUrl().toString());
                            userVO.setName(signInAccount.getDisplayName());
                            userVO.setProfileUrl(signInAccount.getPhotoUrl().toString());
                            userVO.setUserId(signInAccount.getId());
                            DatabaseReference fireChatDBR = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference registeredUserDBR = fireChatDBR.child(REGISTERED_USER);
                            registeredUserDBR.child(userVO.getUserId()).setValue(userVO);

                            currentUserId = signInAccount.getId();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(FireChatApp.LOG, "OnFailureListener : " + e.getMessage());
                        delegate.onFailureAuthenticate(e.getMessage());
                    }
                });
    }

    public interface UserAuthenticateDelegate {
        void onSuccessAuthenticate(GoogleSignInAccount signInAccount);

        void onFailureAuthenticate(String errrorMsg);
    }

    public List<ChatVO> getmChatList() {
        return mChatList;
    }

    public List<UserVO> getmUserList() {
        return mUserList;
    }
}
