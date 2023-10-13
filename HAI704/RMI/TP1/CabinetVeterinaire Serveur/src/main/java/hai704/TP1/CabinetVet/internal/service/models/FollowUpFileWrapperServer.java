package hai704.TP1.CabinetVet.internal.service.models;

import hai704.TP1.CabinetVeterinaire.Classes.FollowUpFile;

public class FollowUpFileWrapperServer extends FollowUpFile {
    public FollowUpFileWrapperServer() {
    }

    public FollowUpFileWrapperServer(String content) {
        super(content);
    }

    public String getContent() {
        return super.getContent();
    }

    public void setContent(String content) {
        super.setContent(content);
    }
}
