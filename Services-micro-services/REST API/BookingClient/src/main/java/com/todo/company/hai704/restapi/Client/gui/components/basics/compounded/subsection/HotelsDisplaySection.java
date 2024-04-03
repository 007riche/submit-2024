package com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.subsection;

import com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.cards.HotelExpandedSingleCard;
import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.SimplePanel;
import com.todo.company.hai704.restapi.Client.gui.components.utils.Theme;
import com.todo.company.hai704.restapi.Client.gui.events.SearchEvent;
import com.todo.company.hai704.restapi.Client.gui.events.TabEvent;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.BrowsingInfo;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedHotel;
import com.todo.company.hai704.restapi.Client.services.persistence.PersistenecService;
import com.todo.company.hai704.restapi.Client.services.remote.NodeServiceUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

//@Component
public class HotelsDisplaySection extends JPanel implements SearchEvent<BrowsingInfo> {
    private Container parent;
    private Color backGroundColor;
    private Color foreGroundColor;
//    private List<RetrievedHotel> retrievedHotel = new ArrayList<>();


    private OfferDisplaySection forwardSection;
    private SimplePanel tab;
    private TabEvent sourceTab;
    private Theme theme = new Theme();

    private Font font =  new Font("Tahoma", Font.PLAIN, 15);


    private NodeServiceUsage serviceUsage;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */

//    @Autowired
    public HotelsDisplaySection(PersistenecService persistenecService,
                                Container parent,
                                OfferDisplaySection forwardSection,
                                TabEvent sourceTab,
                                SimplePanel tab,
                                Color backGroundColor,
                                Color foreGroundColor) {
        super(true);
        this.parent = parent;
        this.sourceTab = sourceTab;
        this.tab = tab;
        this.forwardSection = forwardSection;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.serviceUsage = new NodeServiceUsage(persistenecService);


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
        List<RetrievedHotel> hotelList = this.serviceUsage.getHotelsAvalaible(browsingInfo);
        int size = hotelList.size();

//        System.out.println("HotelList: "+hotelList);
        for (RetrievedHotel hotel : hotelList) {
//            for (int i = 0; i < 10; i++) {

            System.out.println("Current Hotel: "+hotel);
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
