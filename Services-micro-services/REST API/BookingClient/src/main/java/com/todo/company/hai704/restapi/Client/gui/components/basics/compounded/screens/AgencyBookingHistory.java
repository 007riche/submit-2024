package com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.screens;


import com.todo.company.hai704.restapi.Client.entity.Agency;
import com.todo.company.hai704.restapi.Client.entity.BookingAgency;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.LabeledIcon;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.ScrollablePanel;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.table.component.Table;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.table.models.BookingHistoryTableModel;
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

public class AgencyBookingHistory extends JPanel {

    private Color backGroundColor;
    private Color foreGroundColor;
    private ResourceLoader resourceLoader = new ResourceLoader();
    private Table bookingHistoryTable;
    private List<BookingAgency> bookings= new ArrayList<>();
    private Theme theme= new Theme();
    private PersistenecService persistenecService;

    public AgencyBookingHistory(PersistenecService persistenecService,
                                Color backGroundColor, Color foreGroundColor) {
        this.persistenecService = persistenecService;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        bookings = this.persistenecService.findAllBookingAgencies();
        initializeComponent();
    }

    private void initializeComponent() {
        setLayout(null);
        setBackground(getBackGroundColor());
        setForeground(getForeGroundColor());

        LabeledIcon agencyBookingHistoryPromptLabel = new LabeledIcon("Historiques des réservations",
                SwingConstants.CENTER,
                "application/icons/32x32/reserved.png",
                75, 5, 400, 40,
                theme.getBlack(), theme.getWhite(),
                new Font("Tahoma", Font.BOLD, 16)
        );
        add(agencyBookingHistoryPromptLabel);


        JButton reloadBookingHist = null;
        try {
            reloadBookingHist = new JButton(new ImageIcon(new File(resourceLoader
                    .getResource("application/icons/32x32/refresh.png").toURI()).toURL()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        reloadBookingHist.setBounds(50, 50, 50, 50);
        reloadBookingHist.setBackground(theme.getBlack());
        reloadBookingHist.setBorder(new LineBorder(theme.getYelloowAccent(), 2));
        reloadBookingHist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadTable();
            }
        });
        add(reloadBookingHist);


        ScrollablePanel agencyBookingScrollPane = new ScrollablePanel(20, 125, 550, 475,
                theme.getBlack(), theme.getWhite());

        BookingHistoryTableModel tm = new BookingHistoryTableModel(bookings);
        bookingHistoryTable = new Table(
                tm,  new Dimension(500, 375),
                getForeGroundColor(),  getBackGroundColor(),
                theme.getBlack(), theme.getGrey(), theme.getWhite(), "");

        agencyBookingScrollPane.setViewportView(bookingHistoryTable);

        add(agencyBookingScrollPane);


    }


    private void loadTable() {
        bookings = this.persistenecService.findAllBookingAgencies();
//        System.out.println("Reload booking size="+bookings.size());
        ((BookingHistoryTableModel) bookingHistoryTable.getModel()).updateBooking(bookings);

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
