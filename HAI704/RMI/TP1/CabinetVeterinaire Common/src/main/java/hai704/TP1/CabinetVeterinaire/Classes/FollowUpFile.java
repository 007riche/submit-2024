package hai704.TP1.CabinetVeterinaire.Classes;

import java.io.Serializable;

public class FollowUpFile implements Serializable {
    private String ID; //Auto-generated, but not used
    private String content;

    public FollowUpFile() {
    }

    public FollowUpFile(String content) {
        this.content = content;
    }

    protected String getContent() {
        return content;
    }

    protected void setContent(String content) {
        this.content = content;
    }
}
