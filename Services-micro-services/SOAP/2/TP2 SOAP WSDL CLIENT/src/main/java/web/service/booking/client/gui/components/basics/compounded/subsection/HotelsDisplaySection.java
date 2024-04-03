package web.service.booking.client.gui.components.basics.compounded.subsection;

import web.service.booking.client.gui.components.basics.compounded.cards.HotelExpandedSingleCard;
import web.service.booking.client.gui.components.basics.simple.SimplePanel;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.gui.events.SearchEvent;
import web.service.booking.client.gui.events.TabEvent;
import web.service.booking.client.models.BrowsingInfo;
import web.service.booking.client.models.RetrievedHotel;
import web.service.booking.client.services.NodeServiceUsage;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class HotelsDisplaySection extends JPanel implements SearchEvent<BrowsingInfo> {
    private Container parent;
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private Color backGroundColor;
    private Color foreGroundColor;
//    private List<RetrievedHotel> retrievedHotel = new ArrayList<>();


    private OfferDisplaySection forwardSection;
    private SimplePanel tab;
    private TabEvent sourceTab;
    Theme theme = new Theme();

    private Font font =  new Font("Tahoma", Font.PLAIN, 15);

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public HotelsDisplaySection(Container parent,
                                OfferDisplaySection forwardSection,
                                TabEvent sourceTab,
                                SimplePanel tab,
                                int x, int y,
                                int width, int height,
                                Color backGroundColor, Color foreGroundColor) {
        super(true);
        this.parent = parent;
        this.sourceTab = sourceTab;
        this.tab = tab;
        X = x;
        Y = y;
        this.forwardSection = forwardSection;
        Width = width;
        Height = height;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        initializeComponent();
    }

    private void initializeComponent() {
        setLayout(null);
        setBackground(getBackGroundColor());
        setForeground(getForeGroundColor());
        setLayout(new GridLayout(0, 1, 10, 1));
    }

    @Override
    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
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

    public OfferDisplaySection getForwardSection() {
        return forwardSection;
    }

    public void setForwardSection(OfferDisplaySection forwardSection) {
        this.forwardSection = forwardSection;
    }

    public void setForeGroundColor(Color foreGroundColor) {
        this.foreGroundColor = foreGroundColor;
    }



    /**
     * @param browsingInfo
     */
    @Override
    public void onSearch(BrowsingInfo browsingInfo) {
//        removeAllComp();
        removeAll();
        List<RetrievedHotel> hotelList = NodeServiceUsage.getHotelsAvalaible(browsingInfo);
        int size = hotelList.size();
        for (RetrievedHotel hotel : hotelList) {
//            for (int i = 0; i < 10; i++) {
            HotelExpandedSingleCard card = new HotelExpandedSingleCard(false, hotel, browsingInfo, this.tab);
            card.attachObserver(this.forwardSection);
            card.attachTabEventListener(this.sourceTab);
            this.add(card);
            //            }
        }
        revalidate();
        repaint();
    }

    private void removeAllComp() {
        this.removeAll();
    }
}
