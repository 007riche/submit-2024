package hai704.TP1.CabinetVetetrinaire.Serveur.models;

import hai704.TP1.CabinetVeterinaire.Common.Classes.FollowUpFile;

public class FollowUpFileWrapperServer extends FollowUpFile {
    public FollowUpFileWrapperServer() {
    }

    public FollowUpFileWrapperServer(String Id, String content) {
        super(content);
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
