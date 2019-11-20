package jni.example.p2pinvest.manager;

public class VersionsManager {
    private static VersionsManager versionsManager;

    private VersionsManager() {
        init();
    }

    public VersionsManager getVersionsManager(){
        if(versionsManager==null){
            versionsManager = new VersionsManager();
        }
        return versionsManager;
    }

    private void init() {

    }

}
