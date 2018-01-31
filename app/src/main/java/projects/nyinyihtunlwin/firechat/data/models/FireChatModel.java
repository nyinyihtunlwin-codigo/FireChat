package projects.nyinyihtunlwin.firechat.data.models;

/**
 * Created by Dell on 1/29/2018.
 */

public class FireChatModel {

    private static FireChatModel objectInstance;


    private FireChatModel() {
    }

    public static FireChatModel getInstance() {
        if (objectInstance == null) {
            objectInstance = new FireChatModel();
        }
        return objectInstance;
    }

}
