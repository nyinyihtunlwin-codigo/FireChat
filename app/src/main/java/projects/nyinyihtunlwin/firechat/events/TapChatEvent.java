package projects.nyinyihtunlwin.firechat.events;

/**
 * Created by Dell on 12/2/2017.
 */

public class TapChatEvent {
    String partnerId;

    public TapChatEvent(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
}
