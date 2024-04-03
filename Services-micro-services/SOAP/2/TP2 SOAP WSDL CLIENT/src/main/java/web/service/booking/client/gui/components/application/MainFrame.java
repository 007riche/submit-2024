package web.service.booking.client.gui.components.application;

import web.service.booking.client.gui.components.basics.compounded.screens.AgencyBookingHistory;
import web.service.booking.client.gui.components.basics.compounded.screens.AgencyClient;
import web.service.booking.client.gui.components.basics.compounded.screens.AgencyDiscoveryServices;
import web.service.booking.client.gui.components.basics.compounded.screens.Connexion;
import web.service.booking.client.gui.components.basics.compounded.subsection.BookingClientInformationSection;
import web.service.booking.client.gui.components.basics.compounded.subsection.HotelsDisplaySection;
import web.service.booking.client.gui.components.basics.compounded.subsection.OfferDisplaySection;
import web.service.booking.client.gui.components.basics.compounded.subsection.SearchSection;
import web.service.booking.client.gui.components.basics.simple.BoundedPanel;
import web.service.booking.client.gui.components.basics.simple.ScrollablePanel;
import web.service.booking.client.gui.components.basics.simple.SimplePanel;
import web.service.booking.client.gui.components.basics.simple.TabbedPane;
import web.service.booking.client.gui.components.utils.ResourceLoader;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.gui.events.ScreenSwitchEvent;
import web.service.booking.client.gui.events.TabEvent;
import web.service.booking.client.models.Agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class MainFrame  extends JFrame  implements ScreenSwitchEvent, TabEvent {

	static Agency running = Agency.getInstance();
	final int FRAME_WIDTH = 900;
    final int FRAME_HEIGHT = 700;
	static Container rootPane;
	private static JLayeredPane mainLayeredPane;
	private static HotelsDisplaySection resultHotelAvailabilityGridPanel;
	private JPanel espaceClient;
	private Connexion connexionAgence;
	private JPanel tabbedEspaceAgence;
	private static Theme theme = new Theme();

	private static JTabbedPane tabbedPane;
	private static OfferDisplaySection roomOfferGridPanel;

	ResourceLoader resourceLoader = new  ResourceLoader();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Agency running = Agency.getInstance();
		String agencyName = running.getAgencyName();
		MainFrame GUI = new MainFrame(agencyName);
		GUI.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public MainFrame(String title) {

		UIManager.put("OptionPane.background", theme.getBlack());
		UIManager.put("Panel.background", theme.getBlack());
		UIManager.put("OptionPane.messageForeground", theme.getWhite());
		UIManager.put("Button.background", theme.getBlack());
		UIManager.put("Button.foreground", theme.getWhite());
		UIManager.put("Button.font", new Font("Tahoma", Font.BOLD, 14));

		setTitle(title);
		InputStream inputStream = resourceLoader.getResourceAsStream("application/logo/agence-de-voyage.png");
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		// Set the icon
        try {
			byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
			Image icon = Toolkit.getDefaultToolkit().createImage(buffer);
			this.setIconImage(icon);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		int posWith = (device.getDisplayMode().getWidth() / 2) - (FRAME_WIDTH/2);
        int posHeight = (device.getDisplayMode().getHeight()/ 2) - (FRAME_HEIGHT/2);
		setBackground(theme.getBlack());
		setBounds(posWith, posHeight, FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(theme.getBlack()); // new Color(26, 95, 180)
		menuBar.setBounds(0, 0, 900, 30);

		rootPane = getContentPane();
		rootPane.setBackground(theme.getBlack());

		this.getContentPane().add(menuBar);

		JMenu espaceMenu = new JMenu("Espaces");
		espaceMenu.setBackground(theme.getBlack());
		espaceMenu.setForeground(theme.getWhite());
		menuBar.add(espaceMenu);

		JMenuItem agenceMenuItem = new JMenuItem("Espace Agence");
		try {
			agenceMenuItem.setIcon(new ImageIcon(new File(resourceLoader.getResource("img/homeIcon.png").toURI()).toURL()));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		agenceMenuItem.setBackground(theme.getBlack());
		agenceMenuItem.setForeground(theme.getWhite());
		agenceMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchScreen(connexionAgence);
			}
		});
		espaceMenu.add(agenceMenuItem);

		JMenuItem clientMenuItem = new JMenuItem("Espace Client");
		clientMenuItem.setBackground(theme.getBlack());
		clientMenuItem.setForeground(theme.getWhite());
		File file = null;
		try {
			file = new File(resourceLoader.getResource("img/user.png").toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		clientMenuItem.setIcon(new ImageIcon(file.getPath()));
		clientMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchScreen(espaceClient);
			}
		});
		espaceMenu.add(clientMenuItem);

		JSeparator separator = new JSeparator();
		espaceMenu.add(separator);

		JMenuItem exitMenuItem = new JMenuItem("Quitter");
		exitMenuItem.setBackground(theme.getBlack());
		exitMenuItem.setForeground(theme.getWhite());
		File exitFile = null;
		try {
			exitFile = new File(new File(resourceLoader.getResource("img/exitIcon.png").toURI()).toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		exitMenuItem.setIcon(new ImageIcon(exitFile.getPath()));
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] confirmOptions = {"Oui", "Annuler"};
				int exitConfirmationResult = JOptionPane.showOptionDialog(rootPane, "Etes-vous sur de quitter?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, confirmOptions, confirmOptions[1]);
				if(exitConfirmationResult == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		espaceMenu.add(exitMenuItem);

		JMenu helpMenuItem = new JMenu("Aide");
		helpMenuItem.setBounds(new Rectangle(0, 0, 100, 26));
		helpMenuItem.setSize(new Dimension(75, 26));
		helpMenuItem.setForeground(new Color(222, 221, 218));
		helpMenuItem.setBackground(new Color(26, 95, 180));
		menuBar.add(helpMenuItem);

		mainLayeredPane = new JLayeredPane();
		mainLayeredPane.setBounds(0, 42, 900, 622);
		this.getContentPane().add(mainLayeredPane);

		//********************************************* ESPACE CLIENT********************************************************************

		String[] options = {"5 \u2605\u2605\u2605\u2605\u2605",
				"4 \u2605\u2605\u2605\u2605\u2606",
				"3 \u2605\u2605\u2605\u2606\u2606",
				"2 \u2605\u2605\u2606\u2606\u2606",
				"1 \u2605\u2606\u2606\u2606\u2606"};

		//********************************************* TABBED ESPACE AGENCE ********************************************************************
		espaceClient = new JPanel();
		espaceClient.setLayout(null);
		espaceClient.setBackground(new Color(61, 56, 70));
		espaceClient.setBounds(0, 0, 900, 622);
		mainLayeredPane.add(espaceClient);


		JLayeredPane resultLayeredPane = new JLayeredPane();
		resultLayeredPane.setBackground(new Color(246, 245, 244));
		resultLayeredPane.setBounds(0, 140, 900, 490);
		espaceClient.add(resultLayeredPane);

		tabbedPane = new TabbedPane(JTabbedPane.TOP,
				0, 12, 900, 466,
				theme.getBlack(), theme.getWhite());
		resultLayeredPane.add(tabbedPane);

		SimplePanel resultHotelAvailabilityPane = new SimplePanel(theme.getBlack(),
				theme.getWhite());
		tabbedPane.addTab("Résultats de recherche", null, resultHotelAvailabilityPane, null);

		SearchSection searchHeadPanel = new SearchSection(espaceClient,
				0, 0, 900, 135,
				theme.getBlack(), theme.getWhite(), resultHotelAvailabilityPane
		);
		searchHeadPanel.attachTabEventListener(this);

		espaceClient.add(searchHeadPanel);

		BoundedPanel resultHotelAvailabilityInnerPanel = new BoundedPanel(75, 25, 775, 400,
				theme.getBlack(), theme.getWhite());
		resultHotelAvailabilityPane.add(resultHotelAvailabilityInnerPanel);

		ScrollablePanel resultHotelAvailabilityScrollPane = new ScrollablePanel(0, 0, 765, 400,
				theme.getBlack(), theme.getWhite());
		resultHotelAvailabilityInnerPanel.add(resultHotelAvailabilityScrollPane);

		SimplePanel resultOffersPane =new SimplePanel(theme.getBlack(),
				theme.getWhite());

		resultHotelAvailabilityGridPanel = new HotelsDisplaySection(resultHotelAvailabilityScrollPane,
				null, this, resultOffersPane,0, 0, 765, 400
				, theme.getBlack(), theme.getWhite()
				);


		searchHeadPanel.attachObserver(resultHotelAvailabilityGridPanel);

		// set view port
		resultHotelAvailabilityScrollPane.setViewportView(resultHotelAvailabilityGridPanel);


		BoundedPanel resultOffersInnerPanel = new BoundedPanel(75, 25, 700, 400,
				theme.getBlack(), theme.getWhite());

		tabbedPane.addTab("Offres", null, resultOffersPane, null);
		resultOffersPane.add(resultOffersInnerPanel);
		ScrollablePanel resultOffersScrollPane = new ScrollablePanel(0, 0, 700, 375,
				theme.getBlack(), theme.getWhite());
		resultOffersScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		resultOffersInnerPanel.add(resultOffersScrollPane);

		SimplePanel bookingPane =new SimplePanel(theme.getBlack(),
				theme.getWhite());
		BookingClientInformationSection	bookingGridPanel = new BookingClientInformationSection(
				10, 25, 650, 900,
				theme.getBlack(), theme.getWhite());
		bookingGridPanel.setPreferredSize(new Dimension(650, 900));

		roomOfferGridPanel = new OfferDisplaySection(true, resultOffersScrollPane,
				null, bookingPane,0, 0, 680, 400,
				theme.getBlack(), theme.getWhite(), null
				);
		// For Tab Switching events
		roomOfferGridPanel.attachTabEventListener(this);
		// For Payment events
		roomOfferGridPanel.attachPaymentEventObserver(bookingGridPanel);
		resultOffersScrollPane.setViewportView(roomOfferGridPanel);
//		roomOfferGridPanel.setPreferredSize(new Dimension(700, 450)); // BuGGous

		resultHotelAvailabilityGridPanel.setForwardSection(roomOfferGridPanel);
		roomOfferGridPanel.setForwardSection(bookingGridPanel);
		ScrollablePanel bookingScrollPane = new ScrollablePanel(
				0, 25, 750, 370,
				theme.getIndigo(), theme.getWhite());
		bookingScrollPane.setViewportView(bookingGridPanel);

		BoundedPanel bookingInnerPanel = new BoundedPanel(
				65, 25, 750, 400,
				theme.getBlack(), theme.getWhite());
		bookingInnerPanel.add(bookingScrollPane);
		bookingPane.add(bookingInnerPanel);
		tabbedPane.addTab("Réservation", null, bookingPane, null);

		//********************************************* ESPACE AGENCE ********************************************************************
		tabbedEspaceAgence = new JPanel();
		tabbedEspaceAgence.setBounds(0, 0, 900, 622);
		tabbedEspaceAgence.setBackground(theme.getBlack());
		tabbedEspaceAgence.setForeground(theme.getWhite());
		mainLayeredPane.add(tabbedEspaceAgence);
		tabbedEspaceAgence.setLayout(null);

		JTabbedPane agenceTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		agenceTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		agenceTabbedPane.setBackground(theme.getBlack());
		agenceTabbedPane.setForeground(theme.getWhite());
		agenceTabbedPane.setBounds(0, 0, 890, 622);
		tabbedEspaceAgence.add(agenceTabbedPane);

		AgencyDiscoveryServices agenceServiceCredPanel = new AgencyDiscoveryServices(
				theme.getBlack(), theme.getWhite(), espaceClient
		);
		agenceServiceCredPanel.attachObserver(this);


		agenceTabbedPane.addTab("Informations d'utilsation des services", null, agenceServiceCredPanel, null);
		agenceServiceCredPanel.setLayout(null);

		AgencyClient clientsPanel = new AgencyClient(theme.getBlack(), theme.getWhite());
		agenceTabbedPane.addTab("Nos Clients", null, clientsPanel, null);


		AgencyBookingHistory bookingHistoryPanel = new AgencyBookingHistory(theme.getBlack(), theme.getWhite());
		agenceTabbedPane.addTab("Historique des réservations", null, bookingHistoryPanel, null);

		//********************************************* CONNEXION POUR ACCESS A L'ESPACE AGENCE ********************************************************************
		connexionAgence = new Connexion(0, 0, 900, 625,
				theme.getBlack(), theme.getWhite(), tabbedEspaceAgence);
		connexionAgence.attachObserver(this);


		switchScreen(espaceClient); // Demarre sur l'espace client
	}

	public static void switchScreen(JPanel panel) {
		mainLayeredPane.removeAll();
		mainLayeredPane.add(panel);
		mainLayeredPane.repaint();
		mainLayeredPane.revalidate();
	}

	/**
	 * @param target
	 */
	@Override
	public void onScreenSwitch(JComponent target) {
		switchScreen((JPanel) target);
	}

	/**
	 * @param target
	 */
	@Override
	public void onTabSwitch(JComponent target) {
		tabbedPane.setSelectedComponent(target);
	}
}