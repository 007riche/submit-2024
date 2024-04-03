package web.service.booking.client.gui.components.basics.compounded.subsection;

import web.service.booking.client.data.BookingHistoryDAO;
import web.service.booking.client.data.ClientDAO;
import web.service.booking.client.data.JDBCConnectionFactory;
import web.service.booking.client.gui.components.basics.simple.Label;
import web.service.booking.client.gui.components.basics.simple.*;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.gui.events.PaymentEvent;
import web.service.booking.client.gui.events.RoomClickEvents;
import web.service.booking.client.models.*;
import web.service.booking.client.services.NodeServiceUsage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookingClientInformationSection extends JPanel implements PaymentEvent, RoomClickEvents {
    // Warning Labels
    private static Label bookingFirstnameWarningLabel;
    private static Label bookingSurnameWarningLabel;
    private static LabeledIcon bookingCardnumberLabel;
    private static Label bookingCvvWarningLabel;
    private static Label bookingCardnumberWarningLabel;
    private static Label totalPriceLabel;
    // Editable fields
    private static TextInputField bookingFirstnameTextField;
    private static TextInputField bookingSurnameTextField;
    private static TextInputField bookingCardnumberTextField;
    private static PasswordInputField bookingCvvTextField;
    private static JTextPane recapChoosenPane;
    Theme theme = new Theme();
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private Color backGroundColor;
    private Color foreGroundColor;
    private Font commonFont24 = new Font("Tahoma", Font.BOLD, 24);
    private Font commonFont18 = new Font("Tahoma", Font.BOLD, 16);
    private Font labelFont = new Font("Tahoma", Font.BOLD, 12);
    private List<RetrievedRoom> selectedRoomList = new ArrayList<RetrievedRoom>();

    private Service hotelService = new Service();
    private ClientDAO clientDAO;
    private BookingHistoryDAO bookingHistoryDAO;


    public BookingClientInformationSection(int x, int y, int width, int height,
                                           Color backgroundColor, Color foregroundColor
    ) {
//        super(x, y, width, height, backgroundColor, foregroundColor);
        super();
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.backGroundColor = backgroundColor;
        this.foreGroundColor = foregroundColor;
        initializeComponent();
        Agency running = Agency.getInstance();
        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(running.getAGENCY_DB_NAME().trim().toUpperCase(),
                "soap", "password", true);
        try {
            clientDAO = new ClientDAO(jdbcConnectionFactory.getConnectionDB());
            bookingHistoryDAO = new BookingHistoryDAO(jdbcConnectionFactory.getConnectionDB(), clientDAO);
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }
        }

    private void initializeComponent() {
        setLayout(null);
        setBackground(this.backGroundColor);
        setForeground(this.foreGroundColor);
        BoundedPanel promptPanel = new BoundedPanel(0, 100, 675, 30,
                theme.getYelloowAccent(), theme.getWhite());
        add(promptPanel);

        // ad-hoc event
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignore event
                }
            }
        };

        Label bookingPromptLabel = new Label("VOS INFORMATIONS POUR LA RESERVATION (PERSONNE PRINCIPALE)",
                SwingConstants.CENTER,
                0, 0, 675, 30,
                theme.getBlack(), theme.getWhite(),
                new Font("Tahoma", Font.BOLD, 16));
        promptPanel.add(bookingPromptLabel);

        BoundedPanel firstnamePanel = new BoundedPanel(
                25, 200, 600, 24,
                theme.getBlack(), theme.getWhite()
        );
        add(firstnamePanel);

        LabeledIcon bookingFirstnameLabel = new LabeledIcon("Prénom(s):  ",
                SwingConstants.TRAILING,
                "application/icons/24x24/utilisateur.png",
                0, 0, 200, 24,
                theme.getYelloowAccent(), theme.getWhite(), commonFont18);
        firstnamePanel.add(bookingFirstnameLabel);

        bookingFirstnameTextField = new TextInputField(
                225, 0, 375, 24, 25,
                "Ex: Pierre", theme.getBlack(), theme.getGrey(), commonFont18);
        firstnamePanel.add(bookingFirstnameTextField);

        BoundedPanel firstnameWarningPanel = new BoundedPanel(
                250, 225, 375, 24,
                theme.getBlack(), theme.getYelloowAccent());
        add(firstnameWarningPanel);

        bookingFirstnameWarningLabel = new Label("Entrez vos correctes prenoms s'il vous plait",
                SwingConstants.CENTER,
                0, 5, 375, 12,
                theme.getBlack(), theme.getYelloowAccent(),
                labelFont);
        bookingFirstnameWarningLabel.setVisible(false);
        firstnameWarningPanel.add(bookingFirstnameWarningLabel);

        BoundedPanel surnamePanel = new BoundedPanel(
                25, 300, 600, 24,
                theme.getBlack(), theme.getWhite()
        );
        add(surnamePanel);

        LabeledIcon bookingSurnameLabel = new LabeledIcon("Nom:  ",
                SwingConstants.TRAILING,
                "application/icons/24x24/utilisateur.png",
                0, 0, 200, 24,
                theme.getYelloowAccent(), theme.getWhite(), commonFont18);
        surnamePanel.add(bookingSurnameLabel);

        bookingSurnameTextField = new TextInputField(
                225, 0, 375, 24, 25,
                "Ex: Kodjo", theme.getBlack(), theme.getGrey(), commonFont18);
        surnamePanel.add(bookingSurnameTextField);

        BoundedPanel surnameWarningPanel = new BoundedPanel(
                250, 325, 375, 24,
                theme.getBlack(), theme.getYelloowAccent());
        add(surnameWarningPanel);

        bookingSurnameWarningLabel = new Label("Entrez votre correcte nom s'il vous plait",
                SwingConstants.CENTER,
                0, 5, 375, 12,
                theme.getBlack(), theme.getYelloowAccent(),
                labelFont);
        bookingSurnameWarningLabel.setVisible(false);
        surnameWarningPanel.add(bookingSurnameWarningLabel);

        BoundedPanel cardNumberPanel = new BoundedPanel(
                25, 400, 600, 24,
                theme.getBlack(), theme.getWhite()
        );
        add(cardNumberPanel);

        bookingCardnumberLabel = new LabeledIcon("Numero de carte:  ",
                SwingConstants.TRAILING,
                "application/icons/24x24/carte-bancaire.png",
                0, 0, 200, 24,
                theme.getYelloowAccent(), theme.getWhite(), commonFont18);
        cardNumberPanel.add(bookingCardnumberLabel);

        bookingCardnumberTextField = new TextInputField(
                225, 0, 375, 24, 25,
                "Ex: 4949 4949 4949 4949", theme.getBlack(),
                theme.getGrey(), commonFont18);
        bookingCardnumberTextField.addKeyListener(keyAdapter);
        cardNumberPanel.add(bookingCardnumberTextField);

        BoundedPanel cardNumberWarningPanel = new BoundedPanel(
                250, 425, 375, 24,
                theme.getBlack(), theme.getYelloowAccent());
        add(cardNumberWarningPanel);

        bookingCardnumberWarningLabel = new Label("Entrez un numero de carte correcte s'il vous plait",
                SwingConstants.CENTER,
                0, 5, 375, 12,
                theme.getBlack(), theme.getYelloowAccent(),
                labelFont);
        bookingCardnumberWarningLabel.setVisible(false);
        cardNumberWarningPanel.add(bookingCardnumberWarningLabel);

        BoundedPanel cvvPanel = new BoundedPanel(
                25, 500, 600, 24,
                theme.getBlack(), theme.getWhite()
        );
        add(cvvPanel);

        LabeledIcon bookingCvvLabel = new LabeledIcon("CVV:  ",
                SwingConstants.TRAILING,
                "application/icons/24x24/cvv.png",
                0, 0, 200, 24,
                theme.getYelloowAccent(), theme.getWhite(), commonFont18);
        cvvPanel.add(bookingCvvLabel);

        bookingCvvTextField = new PasswordInputField(
                225, 0, 375, 24, 3,
                3, 3, "999",
                theme.getBlack(), theme.getGrey()
        );
        bookingCvvTextField.addKeyListener(keyAdapter);
        cvvPanel.add(bookingCvvTextField);

        BoundedPanel cvvWarningPanel = new BoundedPanel(
                250, 525, 375, 24,
                theme.getBlack(), theme.getYelloowAccent());
        add(cvvWarningPanel);

        bookingCvvWarningLabel = new Label("Entrez un numero de CVV s'il vous plait",
                SwingConstants.CENTER,
                0, 5, 375, 12,
                theme.getBlack(), theme.getYelloowAccent(),
                labelFont);
        bookingCvvWarningLabel.setVisible(false);
        cvvWarningPanel.add(bookingCvvWarningLabel);

        JButton completeBookingButton = new JButton("Payer");
        completeBookingButton.setForeground(theme.getWhite());
        completeBookingButton.setBackground(theme.getBlack());
        completeBookingButton.setBounds(50, 800, 400, 25);
        completeBookingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = validated();
                // TODO Auto-generated method stub
               if (valid) {
                   Object[] confirmOptions = {"Oui", "Revenir"};
                   int exitConfirmationResult = JOptionPane.showOptionDialog(getTopLevelAncestor(), "Etes-vous sur d'avoir fourni les correctes informations ?", "Confirmation des informations", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, confirmOptions, confirmOptions[0]);
                   if (exitConfirmationResult == JOptionPane.YES_OPTION) {

                       System.out.println("RecapText: "+getRecapText());
                       List<String> chosen = getChosenRooms();
                       System.out.println("Chosen rooms: "+chosen);
                       String token = getToken();
                       System.out.println("Token: "+token);
                       double total = getTotal();

                       Client currentClient = new Client();

                       currentClient.setLastName(bookingSurnameTextField.getInputedText());
                       currentClient.setFirstName(bookingFirstnameTextField.getInputedText());
                       currentClient.setCardNumber(bookingCardnumberTextField.getInputedText());
                       System.err.println("in  Payment: Service:\n"
                               + "IdAgency: "+ hotelService.getIdAgency()+" "
                               + "username: "+ hotelService.getUserName()+" "
                               + "password: "+ hotelService.getPassword()
                       );

                       String unifiedBookingID = NodeServiceUsage.makeBookingOnNode(
                               hotelService, hotelService.getIdAgency(),
                               hotelService.getUserName(), hotelService.getPassword()
                               , currentClient, chosen, token);

                       if (!unifiedBookingID.isBlank()) {
                           System.out.println("Unified Booking id: "+unifiedBookingID);

                           List<BookingAgency> bookingAgencyList = new ArrayList<>();

                           // Booking Between client and server
                           for (int i = 0; i < selectedRoomList.size()-1; i++) {
                               RetrievedRoom room = selectedRoomList.get(i);
                               BookingAgency currentBoooking = makeAgencyBooking(unifiedBookingID, total, room);
                               bookingAgencyList.add(currentBoooking);
                           }

                           // Local Record on the client DAOs
                           if (bookingAgencyList.size()>0) {
                               for (BookingAgency booking: bookingAgencyList){
                                   bookingHistoryDAO.compoundAdd(booking, currentClient);
                               }
                           }

                           int clientId = -1;
                           List<Client> allCurrent = new ArrayList<>();
                           allCurrent = clientDAO.getAll();

                           for (int i = 0; i < allCurrent.size(); i++) {
                               Client indexCl = allCurrent.get(i);
                               if (indexCl.getFirstName().contentEquals(currentClient.getFirstName())
                                       && indexCl.getLastName().contentEquals(currentClient.getLastName())
                                       && indexCl.getCardNumber().contentEquals(currentClient.getCardNumber())) {
                                   clientId = indexCl.getId();
                               }
                           }

                           if(clientId == -1) {
                               clientDAO.add(currentClient);
//            clientId = this.clientDAO.getClientID(client);
                           }
                           JOptionPane.showMessageDialog(getTopLevelAncestor(), "Votre Reservation a bien ete enregistree:",
                                   "Reservation reussie", JOptionPane.INFORMATION_MESSAGE);
                       }

                       else {
                           JOptionPane.showMessageDialog(getTopLevelAncestor(), "Une erreur s'est produite lors de votre reservation"+unifiedBookingID,
                                   "Echec de reservation", JOptionPane.INFORMATION_MESSAGE);
                       }

                       resetCompoenent();
                       selectedRoomList = new ArrayList<RetrievedRoom>();

                   }
               }
            }
        });
        add(completeBookingButton);

        ScrollablePanel recapScrollablePanel = new ScrollablePanel(
                50, 600, 600, 150,
                theme.getBlack(), theme.getWhite());

        recapChoosenPane = new JTextPane();
        recapChoosenPane.setBackground(theme.getBlack());
        recapChoosenPane.setForeground(theme.getYelloowAccent());
        recapChoosenPane.setContentType("text/plain");
        recapChoosenPane.setEnabled(false);


//        String text = "Le Lorem Ipsum est simplement" +
//                " du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.";

//        recapChoosenPane.setText(text);


        recapScrollablePanel.setViewportView(recapChoosenPane);
        add(recapScrollablePanel);


        totalPriceLabel = new Label("Total: ", SwingConstants.CENTER,
                400, 800, 275, 24,
                theme.getBlack(), theme.getYelloowAccent(), labelFont);
        add(totalPriceLabel);
    }


    private boolean validated() {
        if (bookingFirstnameTextField.getText().trim().isBlank()
                || bookingSurnameTextField.getText().trim().isBlank()
                || bookingCardnumberTextField.getText().trim().isBlank()
                || bookingCvvTextField.getText().trim().isBlank()
        ) {
            if (bookingFirstnameTextField.getText().trim().isBlank()) {
                bookingFirstnameWarningLabel.setVisible(true);
            } else {
                bookingFirstnameWarningLabel.setVisible(false);
            }

            if (bookingSurnameTextField.getText().trim().isBlank()) {
                bookingSurnameWarningLabel.setVisible(true);
            } else {
                bookingSurnameWarningLabel.setVisible(false);
            }


            if (bookingCvvTextField.getText().trim().isBlank()
            || bookingCvvTextField.getText().trim().length() != 3) {
                bookingCvvWarningLabel.setVisible(true);
            } else {
                System.out.println("password: "+bookingCvvTextField.getText());
                bookingCvvWarningLabel.setVisible(false);
            }


            if (bookingCardnumberTextField.getText().trim().isBlank()
                    || bookingCardnumberTextField.getText().trim()
                    .replace(" ", "").length() != 12) {
                bookingCardnumberWarningLabel.setVisible(true);
            } else {
                bookingCardnumberWarningLabel.setVisible(false);
            }
            return false;
        }


            bookingFirstnameWarningLabel.setVisible(false);
            bookingSurnameWarningLabel.setVisible(false);
            bookingCvvWarningLabel.setVisible(false);
            bookingCardnumberWarningLabel.setVisible(false);
//            String totaltext = totalPriceLabel.getText();

            return true;


    }

    /**
     *
     */
    @Override
    public void onSentPayment() {
        resetCompoenent();
        recapChoosenPane.setText(getRecapText());
        double total = getTotal();
        totalPriceLabel.setText("Total: "+total+" \u20ac");


    }

    /**
     * @param room
     */
    @Override
    public void onSelected(RetrievedRoom room) {
        System.out.println("room selectetion fired: "+ room.getOfferId());
        selectedRoomList.add(room);
    }

    /**
     * @param room
     */
    @Override
    public void onUnselected(RetrievedRoom room) {
        System.out.println("room unselectetion fired: "+ room.getOfferId());
        selectedRoomList.remove(room);
    }

    private void resetCompoenent() {
        bookingFirstnameTextField.setText("");
        bookingSurnameTextField.setText("");
        bookingCardnumberTextField.setText("");
        bookingCvvTextField.setText("");
        recapChoosenPane.setText("");

        bookingFirstnameWarningLabel.setVisible(false);
        bookingSurnameWarningLabel.setVisible(false);
        bookingCvvWarningLabel.setVisible(false);
        bookingCardnumberWarningLabel.setVisible(false);
        totalPriceLabel.setText("Total: ");

    }

    private String  getRecapText() {
        String text = "";

        for (RetrievedRoom room: selectedRoomList) {
            if(room.getNumberBed() > 0) {
                if (room.getNumberBed() == 1 ) {
                    text += "\n1 place, Prix: ";
                }
                else {
                    text += "\n"+room.getNumberBed()+" places, Prix: ";
                }
                text+=(Math.round(room.getPrice() * 100.0) / 100.0)+"\u20ac";
            }
        }

        return text;
    }

    private List<String> getChosenRooms() {
        List<String> ch = new ArrayList<>();
        for (int i = 0; i < selectedRoomList.size()-1; i++) {
            String offerId = selectedRoomList.get(i).getOfferId();
            ch.add(offerId);
        }
        return ch;
    }

    private String getToken() {
        int index = selectedRoomList.size() -1;
        return selectedRoomList.get(index).getOfferId();
    }

    private double getTotal() {
        double total = 0.0;
        if (!selectedRoomList.isEmpty())
        {
            for (int i = 0; i < selectedRoomList.size()-1; i++) {
                total += selectedRoomList.get(i).getPrice();
            }
            total = (Math.round(total * 100.0) / 100.0);
        }

        return total;
    }

    private BookingAgency makeAgencyBooking(String ID, double total, RetrievedRoom room) {
        BookingAgency currentBooking = new BookingAgency();
        currentBooking.setBookingReference(ID);
        currentBooking.setTotalPrice(total);
        currentBooking.setArrivalDate(room.getFrom());
        currentBooking.setCheckoutDate(room.getTo());
        currentBooking.setNumberPersons(room.getSearchedNumberPerson());

        return currentBooking;
    }

    public Service getHotelService() {
        return hotelService;
    }

    public void setHotelService(Service hotelService) {
        this.hotelService = hotelService;
    }
}
