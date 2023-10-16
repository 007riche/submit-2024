package hai704.TP1.CabinetVetetrinaire.Serveur;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy 'at' HH:mm")));
    }
}