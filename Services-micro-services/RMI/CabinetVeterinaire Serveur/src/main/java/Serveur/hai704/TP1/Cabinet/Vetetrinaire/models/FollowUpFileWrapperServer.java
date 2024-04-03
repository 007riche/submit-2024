package Serveur.hai704.TP1.Cabinet.Vetetrinaire.models;


import Common.hai704.TP1.Cabinet.Veterinaire.FollowUpFile;

public class FollowUpFileWrapperServer extends FollowUpFile {
    public FollowUpFileWrapperServer() {
    }

    public FollowUpFileWrapperServer(String Id, String content) {
        super(content);
    }

    public FollowUpFileWrapperServer(FollowUpFile followUpFile) {
        super(followUpFile);
    }

    public String getID() {
        return super.getID();
    }

    public void setID(String ID) {
        super.setID(ID);
    }

    public String getContent() {
        return super.getContent();
    }

    public void setContent(String content) {
        super.setContent(content);
    }
}
