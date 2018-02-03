package projects.nyinyihtunlwin.firechat.events;

import projects.nyinyihtunlwin.firechat.data.vo.UserVO;

/**
 * Created by Dell on 12/2/2017.
 */

public class TapChatEvent {
    UserVO mPartner;

    public TapChatEvent(UserVO mPartner) {
        this.mPartner = mPartner;
    }

    public UserVO getPartner() {
        return mPartner;
    }

    public void setPartner(UserVO partner) {
        this.mPartner = partner;
    }
}
