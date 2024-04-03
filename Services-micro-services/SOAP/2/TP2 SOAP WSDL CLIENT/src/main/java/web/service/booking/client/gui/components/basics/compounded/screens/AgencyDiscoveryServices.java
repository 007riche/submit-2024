package web.service.booking.client.gui.components.basics.compounded.screens;

import web.service.booking.client.gui.components.basics.simple.LabeledIcon;
import web.service.booking.client.gui.components.basics.simple.ScrollablePanel;
import web.service.booking.client.gui.components.basics.simple.SimplePanel;
import web.service.booking.client.gui.components.utils.ResourceLoader;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.gui.events.ScreenSwitchEvent;
import web.service.booking.client.models.Agency;
import web.service.booking.client.models.Service;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AgencyDiscoveryServices extends JPanel {

    private Color backGroundColor;
    private Color foreGroundColor;
    private List<ScreenSwitchEvent> screenSwitchObservers = new ArrayList<ScreenSwitchEvent>();
    ResourceLoader resourceLoader = new  ResourceLoader();
    private JComponent target;
    private static SimplePanel agenceServicePanel;
    private List<Service> services= new ArrayList<>();
    private Theme theme= new Theme();

    public AgencyDiscoveryServices(Color backGroundColor, Color foreGroundColor, JComponent target) {
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.target = target;
        initializeComponent();
    }

    private void initializeComponent() {
        setBackground(getBackGroundColor());
        setForeground(getForeGroundColor());

        LabeledIcon agenceServiceCredPromptLable = new LabeledIcon("Vos informations d'utilisation des services des hôtels partnaires",
                SwingConstants.CENTER,
                "application/icons/32x32/online-reservation.png",
                25, 5, 550, 40,
                theme.getBlack(), theme.getWhite(),
                new Font("Tahoma", Font.BOLD, 16)
                );
        add(agenceServiceCredPromptLable);


        JButton reloadCredPartner = null;
        try {
            reloadCredPartner = new JButton(new ImageIcon(new File(resourceLoader.getResource("application/icons/32x32/refresh.png").toURI()).toURL()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        reloadCredPartner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadTable();
            }
        });


        reloadCredPartner.setBounds(50, 100, 50, 50);
        reloadCredPartner.setBackground(theme.getBlack());
        reloadCredPartner.setBorder(new LineBorder(theme.getYelloowAccent(), 2));
        add(reloadCredPartner);

        agenceServicePanel = new SimplePanel(theme.getBlack(), theme.getWhite());
        agenceServicePanel.setLayout(new GridLayout(0, 1, 10, 15));

        ScrollablePanel agenceServiceScrollPane = new ScrollablePanel(50, 175, 500, 375,
                theme.getBlack(), theme.getWhite());

        agenceServiceScrollPane.setViewportView(agenceServicePanel);

        // Load on initialization
        loadTable();

        add(agenceServiceScrollPane);

        JButton exitAgencySapceButton = new JButton("Quitter l'espace Agence");

        exitAgencySapceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Object[] confirmOptions = { "Oui", "Annuler"};                int exitConfirmationResult = JOptionPane.showOptionDialog(getTopLevelAncestor(), "Etes-vous sur de quitter l'espace Agence?", "Quitter Agence",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, confirmOptions, confirmOptions[1]);

                if(exitConfirmationResult == JOptionPane.YES_OPTION) {
                    System.err.println("Choice yes");
//                    System.out.println("Target  null: "+String.valueOf( target == null));
                    notifySearchEventObserver();

                } else ;
            }
        });
        exitAgencySapceButton.setForeground(theme.getWhite());
        exitAgencySapceButton.setBackground(theme.getBlack());
        exitAgencySapceButton.setBounds(350, 586, 220, 25);
        exitAgencySapceButton.setBorder(new LineBorder(theme.getYelloowAccent(), 2));
        add(exitAgencySapceButton);

    }


    private void loadTable() {
        services = Agency.getInstance().getAllServices();
        agenceServicePanel.removeAll();
        System.out.println("Reload size="+services.size());
        for (int i = 0; i < services.size(); i++) {
            Service service = services.get(i);

			JTable current = new JTable();
			current.setCellSelectionEnabled(true);
            current.setForeground(theme.getWhite());
            current.setBackground(theme.getBlack());
			current.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			current.setRowHeight(25);
			current.setModel(new DefaultTableModel(
					new Object[][] {
							{"Hotel Name", service.getHotelName()},
							{"Login", service.getLoginId()},
							{"Mot de passe", service.getPassword()},
							{"Booking URL", service.getHotelBookingServiceURL()},
							{"Browsing URL", service.getHotelBrowsingServiceURL()},
							{"Partnership URL", service.getHotelPartnersServiceURL()},
					},
					new String[] {
							"SNo.", String.valueOf(i)
					}
			) {
				boolean[][] columnEditables = new boolean[][] {
						new boolean [] {false, false},
						new boolean [] {false, true},
						new boolean [] {false, true},
						new boolean [] {false, false},
						new boolean [] {false, false},
						new boolean [] {false, false},
				};


				public boolean isCellEditable(int row, int column) {
					return columnEditables[row][column];
				}
			});
			current.getColumnModel().getColumn(0).setResizable(false);
			current.getColumnModel().getColumn(0).setPreferredWidth(100);
			current.getColumnModel().getColumn(1).setPreferredWidth(300);
			agenceServicePanel.add(current);
            revalidate();
            repaint();
		}
    }

    public void attachObserver(ScreenSwitchEvent observer) {
        screenSwitchObservers.add(observer);
    }

    public void detachObserver(ScreenSwitchEvent observer) {
        screenSwitchObservers.add(observer);
    }

    public void notifySearchEventObserver() {
        for(ScreenSwitchEvent observer : screenSwitchObservers) {
            observer.onScreenSwitch(this.target);
        }
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
