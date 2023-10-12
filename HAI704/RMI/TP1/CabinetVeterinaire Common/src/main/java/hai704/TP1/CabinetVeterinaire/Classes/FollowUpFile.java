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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
