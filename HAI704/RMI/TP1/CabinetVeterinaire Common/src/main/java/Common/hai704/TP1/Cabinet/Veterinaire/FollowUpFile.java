package Common.hai704.TP1.Cabinet.Veterinaire;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FollowUpFile implements Serializable {
    private String ID; //Auto-generated,
    private String content;

    public FollowUpFile() {
        this.ID=String.valueOf(System.currentTimeMillis());
        this.content = "New patient of Cabinet added at: "+
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy 'At' HH:mm"));
    }

    public FollowUpFile(String content) {
        this.ID=String.valueOf(System.currentTimeMillis());
        this.content = content;
    }

    protected FollowUpFile(String Id, String content) {
        this.ID=Id;
        this.content = content;
    }

    protected FollowUpFile(FollowUpFile followUpFile) {
        this.ID= followUpFile.getID();
        this.content = followUpFile.getContent();
        System.out.println("Un-wrapped Followup ID: "+this.ID);
    }

    protected String getID() {
        return ID;
    }

    protected void setID(String ID) {
        this.ID = ID;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
