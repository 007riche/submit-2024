package com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.subsection;

import com.todo.company.hai704.restapi.Client.entity.NodeService;
import com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.cards.OfferRoomCard;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.SimplePanel;
import com.todo.company.hai704.restapi.Client.gui.components.utils.ResourceLoader;
import com.todo.company.hai704.restapi.Client.gui.components.utils.Theme;
import com.todo.company.hai704.restapi.Client.gui.events.HotelClickEvents;
import com.todo.company.hai704.restapi.Client.gui.events.PaymentEvent;
import com.todo.company.hai704.restapi.Client.gui.events.RoomClickEvents;
import com.todo.company.hai704.restapi.Client.gui.events.TabEvent;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.BrowsingInfo;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedHotel;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedRoom;
import com.todo.company.hai704.restapi.Client.services.persistence.PersistenecService;
import com.todo.company.hai704.restapi.Client.services.remote.NodeServiceUsage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
//    private RetrievedRoom tokenRoom = new RetrievedRoom();
    ResourceLoader resourceLoader = new ResourceLoader();

    private List<TabEvent> tabEventListeners = new ArrayList<>();
    private List<RoomClickEvents> roomClickEventsListeners = new ArrayList<>();
    private List<PaymentEvent> paymentEventsListeners = new ArrayList<>();
    private NodeServiceUsage serviceUsage;

    private PersistenecService persistenecService;

    public OfferDisplaySection(boolean isDoubleBuffered,
                               PersistenecService persistenecService,
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
        this.persistenecService = persistenecService;
        this.forwardSection = forwardSection;
        this.currentHotel = currentHotel;
        this.serviceUsage = new NodeServiceUsage(persistenecService);
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
//                forwardSection.onSelected(tokenRoom);
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

        NodeService hotelService =
                this.persistenecService.findFirstByHotelName(hotel.getHotelName());

            // forwarding the concerned service
            this.forwardSection.setHotelService(hotelService);

            List<RetrievedRoom> retrievedRooms =  this.serviceUsage.browseToNode(hotelService,
                    hotelService.getIdAgency(), hotelService.getPassword(),
                    browsingInfo.getFrom(), browsingInfo.getTo(), browsingInfo.getNumberPerson());

            int size = retrievedRooms.size() ;
            if (retrievedRooms.size() > 0 ) {
                for (int i=0; i<size; i++){
                        OfferRoomCard roomCard =
                                new OfferRoomCard(true, retrievedRooms.get(i));
                        roomCard.attachObserver(this.forwardSection);
                        this.add(roomCard);
                }

//                tokenRoom = retrievedRooms.get(size);
            }
//        }

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
