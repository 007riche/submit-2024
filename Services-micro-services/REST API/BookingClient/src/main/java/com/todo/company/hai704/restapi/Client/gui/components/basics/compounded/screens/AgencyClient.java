package com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.screens;

import com.todo.company.hai704.restapi.Client.entity.Agency;
import com.todo.company.hai704.restapi.Client.entity.Client;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.LabeledIcon;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.ScrollablePanel;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.table.component.Table;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.table.models.ClientTableModel;
import com.todo.company.hai704.restapi.Client.gui.components.utils.ResourceLoader;
import com.todo.company.hai704.restapi.Client.gui.components.utils.Theme;
import com.todo.company.hai704.restapi.Client.services.persistence.PersistenecService;

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
    private PersistenecService persistenecService;

    public AgencyClient(PersistenecService persistenecService,
                        Color backGroundColor, Color foreGroundColor) {
        this.persistenecService = persistenecService;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;

        clients = this.persistenecService.findAllClients();
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
        clients = this.persistenecService.findAllClients();
        List<Client> test = new ArrayList<>();

//        for (int i = 0; i < 10; i++) {
////           List<Client> recup = Agency.getInstance().getAgencyClients();
//           for (Client client: recup) {
//               test.add(client);
//           }
//        }

//        test.remove(0); /// Test To remove
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
