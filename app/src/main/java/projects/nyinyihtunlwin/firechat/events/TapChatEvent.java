package projects.nyinyihtunlwin.firechat.events;

import projects.nyinyihtunlwin.firechat.data.vo.UserVO;

/**
 * Created by Dell on 12/2/2017.
 */

public class TapChatEvent {
    UserVO userVO;

    public TapChatEvent(UserVO userVO) {
        this.userVO = userVO;
    }

    public UserVO getUser() {
        return userVO;
    }

    public void setUser(UserVO userVO) {
        this.userVO = userVO;
    }
}
