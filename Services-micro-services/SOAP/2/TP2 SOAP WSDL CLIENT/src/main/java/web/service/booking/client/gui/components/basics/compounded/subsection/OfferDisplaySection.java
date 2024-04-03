package web.service.booking.client.gui.components.basics.compounded.subsection;

import web.service.booking.client.gui.components.basics.compounded.cards.OfferRoomCard;
import web.service.booking.client.gui.components.basics.simple.SimplePanel;
import web.service.booking.client.gui.components.utils.ResourceLoader;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.gui.events.HotelClickEvents;
import web.service.booking.client.gui.events.PaymentEvent;
import web.service.booking.client.gui.events.RoomClickEvents;
import web.service.booking.client.gui.events.TabEvent;
import web.service.booking.client.models.*;
import web.service.booking.client.services.NodeServiceUsage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OfferDisplaySection extends JPanel implements HotelClickEvents {
    private Container parent;
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private Color backGroundColor;
    private Color foreGroundColor;
    private BookingClientInformationSection forwardSection;
    private TabEvent sourceTab;
    private SimplePanel tab;
    private Theme theme = new Theme();

    private boolean selected=false;
    private RetrievedHotel currentHotel = new RetrievedHotel();
    private RetrievedRoom tokenRoom = new RetrievedRoom();
    ResourceLoader resourceLoader = new ResourceLoader();

    private List<TabEvent> tabEventListeners = new ArrayList<>();
    private List<RoomClickEvents> roomClickEventsListeners = new ArrayList<>();
    private List<PaymentEvent> paymentEventsListeners = new ArrayList<>();

    public OfferDisplaySection(boolean isDoubleBuffered,
                               Container parent,
                               BookingClientInformationSection forwardSection,
//                               TabEvent sourceTab,
                               SimplePanel tab,
                               int x, int y, int width, int height,
                               Color backGroundColor, Color foreGroundColor,
                               RetrievedHotel currentHotel) {
        super(isDoubleBuffered);
        this.parent = parent;
//        this.sourceTab = sourceTab;
        this.tab = tab;
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.forwardSection = forwardSection;
        this.currentHotel = currentHotel;
        initializeComponent();
    }

    private void initializeComponent() {
        setLayout(null);
        setBackground(getBackGroundColor());
        setForeground(getForeGroundColor());
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        GridLayout roomOfferGridLayout = new GridLayout(0, 3, 30, 30);
        setLayout(roomOfferGridLayout);

        JButton bookButton = new JButton("Réserver");
        bookButton.setForeground(theme.getWhite());
        bookButton.setBackground(theme.getBlack());
        bookButton.setBounds(200, 375, 380, 25);

        getParent().getParent().add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forwardSection.onSelected(tokenRoom);
                notifyPaymentEventObserver();
                notifyTabEventListeners(tab);
                removeAllComp();
            }
        });

        revalidate();
        repaint();
    }

    /**
     * @param hotel
     */
    @Override
    public void onSelected(RetrievedHotel hotel, BrowsingInfo browsingInfo)  {

        removeAll();

        setCurrentHotel(hotel);

        List<Service> services = Agency.getInstance().getAllServices();

        Optional<Service> hotelServiceOptional = services.stream()
                .filter(service ->
                        service.getHotelName().trim().equalsIgnoreCase(hotel.getHotelName().trim())
                )
                .findFirst();

        if (hotelServiceOptional.isPresent()) {
            Service hotelService = hotelServiceOptional.get();

            // forwarding the concerned service
            this.forwardSection.setHotelService(hotelService);

            List<RetrievedRoom> retrievedRooms =  NodeServiceUsage.browseToNode(hotelService,
                    hotelService.getIdAgency(), hotelService.getPassword(),
                    browsingInfo.getFrom(), browsingInfo.getTo(), browsingInfo.getNumberPerson());

            int size = retrievedRooms.size() - 1;
            if (retrievedRooms.size() > 0 ) {
                for (int i=0; i<size; i++){
                        OfferRoomCard roomCard =
                                new OfferRoomCard(true, retrievedRooms.get(i));
                        roomCard.attachObserver(this.forwardSection);
                        this.add(roomCard);
                }

                tokenRoom = retrievedRooms.get(size);
            }
        }

        this.revalidate();
        this.repaint();
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

    @Override
    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    public BookingClientInformationSection getForwardSection() {
        return forwardSection;
    }

    public void setForwardSection(BookingClientInformationSection forwardSection) {
        this.forwardSection = forwardSection;
    }

    public RetrievedHotel getCurrentHotel() {
        return currentHotel;
    }

    public void setCurrentHotel(RetrievedHotel currentHotel) {
        this.currentHotel = currentHotel;
    }

    public void attachRoomClickObserver(RoomClickEvents observer) {
        roomClickEventsListeners.add(observer);
    }

    public void detachRoomClickObserver(RoomClickEvents observer) {
        roomClickEventsListeners.add(observer);
    }

    public void notifyRoomClicksEventObserver(RetrievedRoom room) {
        for(RoomClickEvents observer : roomClickEventsListeners) {
            if (room.isSelect()) {
                observer.onSelected(room);
            }
            else {
                observer.onUnselected(room);
            }
        }
    }


    public void attachPaymentEventObserver(PaymentEvent observer) {
        paymentEventsListeners.add(observer);
    }

    public void detachPaymentEventObserver(PaymentEvent observer) {
        paymentEventsListeners.add(observer);
    }

    public void notifyPaymentEventObserver() {
        for(PaymentEvent observer : paymentEventsListeners) {
            observer.onSentPayment();
        }
    }

    public void attachTabEventListener(TabEvent listener) {
        tabEventListeners.add(listener);
    }

    public void notifyTabEventListeners(JComponent component) {
        for (TabEvent listener : tabEventListeners) {
            listener.onTabSwitch(component);
        }
    }
    private void removeAllComp() {
        this.removeAll();
    }

}
