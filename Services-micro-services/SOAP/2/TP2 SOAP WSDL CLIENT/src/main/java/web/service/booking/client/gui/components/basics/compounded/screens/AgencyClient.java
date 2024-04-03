package web.service.booking.client.gui.components.basics.compounded.screens;

import web.service.booking.client.gui.components.basics.simple.LabeledIcon;
import web.service.booking.client.gui.components.basics.simple.ScrollablePanel;
import web.service.booking.client.gui.components.basics.simple.table.component.Table;
import web.service.booking.client.gui.components.basics.simple.table.models.ClientTableModel;
import web.service.booking.client.gui.components.utils.ResourceLoader;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.models.Agency;
import web.service.booking.client.models.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AgencyClient extends JPanel {
    private Color backGroundColor;
    private Color foreGroundColor;
    private ResourceLoader resourceLoader = new  ResourceLoader();
    private Table bookingClientsTable;
    private List<Client> clients= new ArrayList<>();
    private Theme theme= new Theme();

    public AgencyClient(Color backGroundColor, Color foreGroundColor) {
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;

        clients = Agency.getInstance().getAgencyClients();
        initializeComponent();
    }

    private void initializeComponent() {
        setLayout(null);
        setBackground(getBackGroundColor());
        setForeground(getForeGroundColor());

        LabeledIcon agencyClientListPromptLabel = new LabeledIcon("Liste de vos clients",
                SwingConstants.CENTER,
                "application/icons/32x32/silhouette-dutilisateurs-multiples.png",
                75, 5, 400, 40,
                theme.getBlack(), theme.getWhite(),
                new Font("Tahoma", Font.BOLD, 16)
        );
        add(agencyClientListPromptLabel);


        JButton reloadClientListHist = null;
        try {
            reloadClientListHist = new JButton(new ImageIcon(new File(resourceLoader
                    .getResource("application/icons/32x32/refresh.png").toURI()).toURL()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        reloadClientListHist.setBounds(50, 50, 50, 50);
        reloadClientListHist.setBackground(theme.getBlack());
        reloadClientListHist.setBorder(new LineBorder(theme.getYelloowAccent(), 2));
        reloadClientListHist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadTable();
            }
        });
        add(reloadClientListHist);


        ScrollablePanel agencyBookingScrollPane = new ScrollablePanel(20, 125, 550, 475,
                theme.getBlack(), theme.getWhite());


        ClientTableModel tm = new ClientTableModel(clients);
        bookingClientsTable = new Table(
                tm,  new Dimension(500, 375),
                getForeGroundColor(),  getBackGroundColor(),
                theme.getBlack(), theme.getGrey(), theme.getWhite(), "");

        agencyBookingScrollPane.setViewportView(bookingClientsTable);

        add(agencyBookingScrollPane);


    }


    private void loadTable() {
        clients = Agency.getInstance().getAgencyClients();
        List<Client> test = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
           List<Client> recup = Agency.getInstance().getAgencyClients();
           for (Client client: recup) {
               test.add(client);
           }
        }

//        test.remove(0);
//        System.out.println("Reload clients size="+clients.size());
        ((ClientTableModel) bookingClientsTable.getModel()).updateClients(clients);
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public Color getForeGroundColor() {
        return foreGroundColor;
    }

    public void setForeGroundColor(Color foreGroundColor) {
        this.foreGroundColor = foreGroundColor;
    }
}
