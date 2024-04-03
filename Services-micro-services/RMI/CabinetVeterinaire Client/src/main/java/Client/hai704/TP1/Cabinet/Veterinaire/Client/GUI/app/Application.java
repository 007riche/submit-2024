package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.app;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.Client;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.AdaptedScrollBar;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.AppTitlePanel;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.RoundTitleBarButtons;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.VerticalMenuButtonPanel;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.VerticalMenuIconButton;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.menu.VerticalMenu;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.AnimalFormEvent;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.PatientInformatioUISectionComponemt;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.SearchUISection;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.TitleBarPanel;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base.AlertBox;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base.ObserverPanel;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.table.*;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.model.PatientTableModel;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.model.SpecyTableModel;
import Common.hai704.TP1.Cabinet.Veterinaire.Animal;
import Common.hai704.TP1.Cabinet.Veterinaire.ICabinet;
import Common.hai704.TP1.Cabinet.Veterinaire.Specy;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Application implements AnimalFormEvent {

    //
    private Client currentClient ;
    private ICabinet service;
    public JFrame frame;
    private JTable patientTable;

    static private int editingPatientRow;
    private static int initialX;
    private static int initialY;
    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 700;
    private JTable speciesTable;



    private List<Animal> patients = new ArrayList<Animal>();
    private List<Specy> specyList = new ArrayList<Specy>();
    static private ObserverPanel updateSection;
    static private TitleBarPanel titleBarPanel;
    static private AlertBox alertPanel;
    static private String currentWorkingSectionName="";
    static private String emptySectionName="";
    static private String alertSectionName="";
    static private String editSectionName="";
    static  private PatientInformatioUISectionComponemt editingInfoPanel;

    static private JPanel tableList;

    private EventDispatch editingObser ; // The editing component watches this to identify new insertion from updation
    private Theme theme = new Theme();
    static ClassLoader fileLoader = Application.class.getClassLoader();


    /**
     * Create the application.
     */
    public Application(/*ICabinet service*/) {
        currentClient = new Client();
        frame = new JFrame();
//        this.service = service;
//        try {
//            this.service.signUpCabinetsClient(currentClient);
//            this.patients = service.getAllPatients();
//            System.out.println("From the constructor: "+this.patients.toString());
//            this.specyList = service.getAllSpecies();
//        } catch (RemoteException e) {
////            throw new RuntimeException(e);
//        }

        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings("serial")
    private void initialize() {

        frame.getContentPane().setForeground(new Color(175, 177, 179));
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(35, 35, 35));
        frame.getContentPane().setFont(new Font("Lato", Font.PLAIN, 10));
        frame.getContentPane().setMinimumSize(new Dimension(1000, 700));
        frame.getContentPane().setPreferredSize(new Dimension(1000, 700));
        frame.getRootPane().setDoubleBuffered(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        InputStream inputStream = fileLoader.getResourceAsStream("icons/app/logo.png");
        byte[] buffer = new byte[0];
        try {
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            Image icon = Toolkit.getDefaultToolkit().createImage(buffer);
            frame.setIconImage(icon);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }

        initialX = (device.getDisplayMode().getWidth() / 2) - (FRAME_WIDTH/2);
        initialY = (device.getDisplayMode().getHeight()/ 2) - (FRAME_HEIGHT/2);


        frame.setBounds(initialX, initialY, FRAME_WIDTH, FRAME_HEIGHT);

        JPanel rootContentPanel = new JPanel();
        rootContentPanel.setBackground(new Color(35, 35, 35));
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(rootContentPanel, GroupLayout.DEFAULT_SIZE, FRAME_WIDTH, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(rootContentPanel, GroupLayout.DEFAULT_SIZE, FRAME_HEIGHT, Short.MAX_VALUE)
        );

        JLayeredPane rootLayeredPane = new JLayeredPane();
//        rootLayeredPane.setIgnoreRepaint(true);
        rootLayeredPane.setOpaque(true);
        rootLayeredPane.setFocusTraversalPolicyProvider(true);
        rootLayeredPane.setFocusCycleRoot(true);
        rootLayeredPane.setAlignmentY(0.0f);
        rootLayeredPane.setAlignmentX(0.0f);
        rootLayeredPane.setBounds(new Rectangle(0, 35, 1000, 660));

        titleBarPanel = new TitleBarPanel();


        // Enable dragging functionality
        titleBarPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
                initialY = e.getY();
            }
        });

        titleBarPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = frame.getLocation().x + e.getX() - initialX;
                int newY = frame.getLocation().y + e.getY() - initialY;
                frame.setLocation(newX, newY);
                titleBarPanel.repaint();
                frame.repaint();
            }
        });

        JPanel zIndexZeroForAppWidget = new JPanel();
        zIndexZeroForAppWidget.setBorder(null);
        zIndexZeroForAppWidget.setBackground(new Color(35, 35, 35));

        tableList = new JPanel();
        tableList.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableList.setPreferredSize(new Dimension(550, 350));
        tableList.setForeground(new Color(175, 177, 179));
        tableList.setBackground(new Color(35, 35, 35));


        AppTitlePanel appIconTitlePanel = new AppTitlePanel("Cabinet Veterinaire");

        JButton listAllPatientsButton = new VerticalMenuIconButton("Lister les patients","32x32/livestock.png");
        VerticalMenuButtonPanel listPatientsIconButtonPanel = new VerticalMenuButtonPanel(listAllPatientsButton);

        JButton addPatientButton = new VerticalMenuIconButton("Ajouter un patient","32x32/vet.png");
        VerticalMenuButtonPanel addPatientIconButtonPanel = new VerticalMenuButtonPanel(addPatientButton);

        JButton listAllSpeciesButton = new VerticalMenuIconButton("Lister les espèces","32x32/paw.png");
        VerticalMenuButtonPanel listSpeciesIconButtonPanel = new VerticalMenuButtonPanel(listAllSpeciesButton);

        JButton searchPatientButtonInMenu = new VerticalMenuIconButton("Rechercher des patients","32x32/magnifying-glass.png");
        VerticalMenuButtonPanel searchPatientsIconButtonPanel = new VerticalMenuButtonPanel(searchPatientButtonInMenu);

        List<VerticalMenuButtonPanel> menuButtonPanels = new ArrayList<>() {{
            add(listPatientsIconButtonPanel);
            add(addPatientIconButtonPanel);
            add(listSpeciesIconButtonPanel);
            add(searchPatientsIconButtonPanel);
        }};

        VerticalMenu verticalMenu=new VerticalMenu((AppTitlePanel) appIconTitlePanel, menuButtonPanels);

        verticalMenu.attach(titleBarPanel);

        JLayeredPane mainLayeredPane = new JLayeredPane();
        mainLayeredPane.setBorder(null);
        mainLayeredPane.setAlignmentX(0.0f);
        mainLayeredPane.setAlignmentY(0.0f);
        mainLayeredPane.setBounds(new Rectangle(450, 0, 550, 655));
        mainLayeredPane.setForeground(new Color(175, 177, 179));

        JScrollPane innerPatientsScrollPane = new JScrollPane();


        innerPatientsScrollPane.setDoubleBuffered(true);
        innerPatientsScrollPane.setForeground(new Color(175, 177, 179));
        innerPatientsScrollPane.setBackground(new Color(35, 35, 35)); //));  175, 177, 179
        innerPatientsScrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        innerPatientsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        innerPatientsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        JScrollPane editPatientScrollPane = new JScrollPane();


        editingInfoPanel = new PatientInformatioUISectionComponemt(specyList);
        editingInfoPanel.addObserver(this);
        editingObser = editingInfoPanel;

        editPatientScrollPane.setViewportView(editingInfoPanel);


        System.out.println("From initialization: "+this.patients.toString());

        PatientTableModel patientTableModel = new PatientTableModel(patients);


        patientTable = new DisplayTable(
                patientTableModel,
                52, new Dimension(450, 700),
               theme.getGrey175(),  theme.getBlack35(),
                theme.getBlack35(), theme.getGrey205(), theme.getBlack35(),
                "Liste des patients");
        CellActionEvent event = new CellActionEvent() {
            @Override
            public void onEdit(int row) {
                   editingObser.dispatchEvent(1);
                   editingInfoPanel.fillFieldsWithAnimal(patients.get(row));
                   CardLayout cardLayout = (CardLayout) updateSection.getLayout();
                   cardLayout.show(updateSection,editSectionName);
                   currentWorkingSectionName = editSectionName;
                   editingPatientRow = row;
            }

            @Override
            public void onDelete(int row) {
                System.out.println("Deleting row: "+row);
                if(patientTable.isEditing()) {
                    patientTable.getCellEditor().stopCellEditing();
                }

                PatientTableModel tableModel = (PatientTableModel) patientTable.getModel();
                tableModel.removeRow(row);
                patients.stream().forEach(animal -> {
                    System.out.println( animal.toString());
                });
                patientTable.repaint();
            }
        };

        patientTable.getColumnModel().getColumn(5).setCellRenderer(new PanelTableCellRender());
        patientTable.getColumnModel().getColumn(5).setCellEditor(new PanelTableCellEditor(event));
        patientTable.repaint();

        innerPatientsScrollPane.setViewportView(patientTable);
        innerPatientsScrollPane.getVerticalScrollBar().setUI(new AdaptedScrollBar());

        updateSection = new ObserverPanel(  theme.getBlack35(),
                                            theme.getGrey175(),
                                            new Dimension(550, 350));
        editPatientScrollPane.getVerticalScrollBar().setUI(new AdaptedScrollBar());


        editPatientScrollPane.setBorder(null);
        editPatientScrollPane.setViewportBorder(null);
        editPatientScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        editPatientScrollPane.setBackground(new Color(35, 35, 35));
        GroupLayout gl_mainLayeredPane = new GroupLayout(mainLayeredPane);
        gl_mainLayeredPane.setHorizontalGroup(
                gl_mainLayeredPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(tableList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateSection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        gl_mainLayeredPane.setVerticalGroup(
                gl_mainLayeredPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_mainLayeredPane.createSequentialGroup()
                                .addComponent(tableList, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(updateSection, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
        );
        tableList.setLayout(new CardLayout(0, 0));

        JScrollPane innerPatientSearchScrollPane = new JScrollPane();
        innerPatientSearchScrollPane.getVerticalScrollBar().setUI(new AdaptedScrollBar());
        innerPatientSearchScrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        innerPatientSearchScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        innerPatientSearchScrollPane.setForeground(new Color(175, 177, 179));
        innerPatientSearchScrollPane.setDoubleBuffered(true);
        innerPatientSearchScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        innerPatientSearchScrollPane.setBackground(new Color(35, 35, 35));
        tableList.add(innerPatientSearchScrollPane, "innerPatientSearchScrollPane");

        JPanel patientSearchPanel = new SearchUISection();

        innerPatientSearchScrollPane.setViewportView(patientSearchPanel);

        JScrollPane innerSpeciesScrollPane = new JScrollPane();
        innerSpeciesScrollPane.getVerticalScrollBar().setUI(new AdaptedScrollBar());
        innerSpeciesScrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        innerSpeciesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        innerSpeciesScrollPane.setForeground(new Color(175, 177, 179));
        innerSpeciesScrollPane.setDoubleBuffered(true);
        innerSpeciesScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        innerSpeciesScrollPane.setBackground(new Color(35, 35, 35));
        tableList.add(innerSpeciesScrollPane, "innerSpeciesScrollPane");


        SpecyTableModel specyTableModel = new SpecyTableModel(specyList);
        speciesTable = new DisplayTable(
                specyTableModel,
                52, new Dimension(450, 700),
                theme.getGrey175(),  theme.getBlack35(),
                theme.getBlack35(), theme.getGrey205(), theme.getBlack35(),
                "Liste des expeces");
        innerSpeciesScrollPane.setViewportView(speciesTable);
        innerSpeciesScrollPane.getVerticalScrollBar().setUI(new AdaptedScrollBar());
        tableList.add(innerPatientsScrollPane, "innerPatientsScrollPane");


        updateSection.setLayout(new CardLayout(0, 0));

        JPanel alertSectionPanel = new JPanel();
        alertSectionPanel.setBackground(new Color(35, 35, 35));
        updateSection.add(alertSectionPanel, "alertSectionPanel");
        alertSectionName = "alertSectionPanel";
        alertSectionPanel.setLayout(null);

        alertPanel = new AlertBox(patients.size());
        alertPanel.attach(updateSection); // binding Display Alert observer
        currentClient.attachAlertListener(alertPanel); // Binding Alert From Server observer
        alertSectionPanel.add(alertPanel);

        JPanel emptyDetailsPanel = new JPanel();
        emptyDetailsPanel.setBorder(null);
        emptyDetailsPanel.setBackground(new Color(35, 35, 35));


        updateSection.add(emptyDetailsPanel, "emptyDetailsPanel");
        emptySectionName = "emptyDetailsPanel";

        updateSection.add(editPatientScrollPane, "editPatientScrollPane");
        editSectionName = "editPatientScrollPane";


        mainLayeredPane.setLayout(gl_mainLayeredPane);

        zIndexZeroForAppWidget.setLayout(null);
        zIndexZeroForAppWidget.add(verticalMenu);
//        zIndexZeroForAppWidget.add(menu);
        zIndexZeroForAppWidget.add(mainLayeredPane);
        rootContentPanel.setLayout(null);
        rootContentPanel.add(titleBarPanel);
        titleBarPanel.setLayout(null);


        listPatientsIconButtonPanel.getInnerButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cardLayout = (CardLayout) tableList.getLayout();
                cardLayout.show(tableList,"innerPatientsScrollPane");
            }
        });


        addPatientIconButtonPanel.getInnerButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editingInfoPanel.dispatchEventOnButton(0);
                CardLayout cardLayout = (CardLayout) updateSection.getLayout();
                cardLayout.show(updateSection, editSectionName);
                currentWorkingSectionName =    editSectionName;
            }
        });

        listSpeciesIconButtonPanel.getInnerButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cardLayout = (CardLayout) tableList.getLayout();
                cardLayout.show(tableList,"innerSpeciesScrollPane");
            }
        });

        searchPatientsIconButtonPanel.getInnerButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cardLayout = (CardLayout) tableList.getLayout();
                cardLayout.show(tableList,"innerPatientSearchScrollPane");
            }
        });

        JButton closeAppButton = new RoundTitleBarButtons(32, theme.getRed(),
                theme.getRed215(), 0,
                "24x24/circle-xmark-black.png" );

        closeAppButton.setBackground(new Color(35, 35, 35));
        closeAppButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        closeAppButton.setBounds(5, 2, 32, 32);

        titleBarPanel.add(closeAppButton);

        JButton minimizeAppFrameButton = new RoundTitleBarButtons(32, theme.getGreen215(),
                theme.getGreen200(), 0, "24x24/down-left-and-up-right-to-center-black.png" );

        minimizeAppFrameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setState(Frame.ICONIFIED);
            }
        });

        minimizeAppFrameButton.setBounds(40, 2, 32, 32);
        titleBarPanel.add(minimizeAppFrameButton);


        rootContentPanel.add(rootLayeredPane);
        rootLayeredPane.setLayout(new CardLayout(0, 0));
        rootLayeredPane.add(zIndexZeroForAppWidget, "name_441041558822900");
        frame.getContentPane().setLayout(groupLayout);
        frame.setResizable(false);
        System.out.println("Display mode: "+frame.getGraphicsConfiguration().getDevice().getDisplayModes());;
        frame.pack();
//        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    @Override
    public void onSaved() {
//        editingObser.dispatchEvent(1);
        Animal updatedPatient= editingInfoPanel.getUpdatedAnimalInformations();
        System.out.println("row: "+editingPatientRow);
        patients.remove(editingPatientRow);
        patients.add(editingPatientRow, updatedPatient);
        System.out.println("App:OnSaved:"+updatedPatient.getFullName()+"----"+updatedPatient.getSpecy().getName());
        patients.stream().forEach(animal -> {
            System.out.println( animal.toString());
        });
       patientTable.repaint();
    }

    @Override
    public void onAdd() {
//        editingInfoPanel.dispatchEventOnButton(0);
        Animal addedPatient= editingInfoPanel.getUpdatedAnimalInformations();
        patients.add( addedPatient);
        patientTable.repaint();
        CardLayout cardLayout = (CardLayout) tableList.getLayout();
        cardLayout.show(tableList,"innerPatientsScrollPane");
    }



    public ICabinet getService() {
        return service;
    }

    public void setService(ICabinet service) {
        this.service = service;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public Client getCurrentClient() {
        return currentClient;
    }
}
