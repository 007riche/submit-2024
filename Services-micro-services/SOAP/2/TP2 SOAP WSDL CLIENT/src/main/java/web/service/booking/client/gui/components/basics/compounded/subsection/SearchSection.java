package web.service.booking.client.gui.components.basics.compounded.subsection;

import web.service.booking.client.gui.components.basics.simple.Label;
import web.service.booking.client.gui.components.basics.simple.*;
import web.service.booking.client.gui.components.basics.simple.spinner.component.BasicSpinner;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.gui.events.SearchEvent;
import web.service.booking.client.gui.events.TabEvent;
import web.service.booking.client.models.BrowsingInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchSection extends BoundedPanel {
    private List<SearchEvent<BrowsingInfo>> browsingEventObserver = new ArrayList<>();
    private Container parent;
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private Color backGroundColor;
    private Color foreGroundColor;

    private Font font =  new Font("Tahoma", Font.PLAIN, 15);

    TextInputField citySearchTextField;
    BasicSpinner maxPriceSpinner;
    BasicSpinner numberPersonSpinner;
    BasicSpinner minPriceSpinner;
    JSpinner toDateSpinner;
    JSpinner fromDateSpinner;
    Calendar currentCalendar;
    DropDown starsNumberComboBox;
    JButton clientSpaceSearchButton;
    private BrowsingInfo currentSearch = new BrowsingInfo();
    private SimplePanel tab;
    private List<TabEvent> tabEventListeners = new ArrayList<>();
    Theme theme = new Theme();
    String[] options = {"5 \u2605\u2605\u2605\u2605\u2605",
            "4 \u2605\u2605\u2605\u2605\u2606",
            "3 \u2605\u2605\u2605\u2606\u2606",
            "2 \u2605\u2605\u2606\u2606\u2606",
            "1 \u2605\u2606\u2606\u2606\u2606"};

    public SearchSection(Container parent,
                         int x, int y, int width, int height,
                         Color backgroundColor, Color foregroundColor,
                         SimplePanel tab
                         ) {
        super(x, y, width, height, backgroundColor, foregroundColor);
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.backGroundColor = backgroundColor;
        this.foreGroundColor = foregroundColor;
        this.tab = tab;
        this.parent = parent;
        initializeComponent();
        clientSpaceSearchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentCalendar = Calendar.getInstance();
                currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
                currentCalendar.set(Calendar.MINUTE, 0);
                currentCalendar.set(Calendar.SECOND, 0);
                currentCalendar.set(Calendar.MILLISECOND, 0);
                boolean result =validated();
//                System.err.println("Clicked + "+result);
                if (result) {
                    // manually setting
                    currentSearch.setCity(citySearchTextField.getText().trim());
                    currentSearch.setFrom((Date) fromDateSpinner.getValue());
                    currentSearch.setTo((Date) toDateSpinner.getValue());
                    currentSearch.setMinPrice(Double.parseDouble(minPriceSpinner.getValue().toString()) );
                    currentSearch.setMaxPrice(Double.parseDouble(maxPriceSpinner.getValue().toString()));
                    currentSearch.setNumberPerson(Integer.parseInt(numberPersonSpinner.getValue().toString().trim()));
                    currentSearch.setStars(Integer.parseInt(String.valueOf(starsNumberComboBox.getSelectedItem().toString().trim().charAt(0))));
                    System.out.println(currentSearch);;
                    notifySearchEventObserver();
                    notifyTabEventListeners(tab);
                }
            }
        });
    }

    private void initializeComponent() {
        BoundedPanel searchHeadPanel = new BoundedPanel(0, 0, 900, 135,
                theme.getBlack(), theme.getWhite());

        this.parent.add(searchHeadPanel);

        Label searchSectionTitleLabel = new Label("Rechercher", SwingConstants.CENTER,
                390, 0, 100, 25,
                theme.getBlack(), theme.getWhite(),
                font);
       searchHeadPanel.add(searchSectionTitleLabel);

       LabeledIcon cityLabel = new LabeledIcon("Ville:", SwingConstants.LEADING,
               "application/icons/16x16/goupille-de-localisation.png",
               10, 35, 80, 25,
               theme.getBlack(), theme.getWhite(),
               font);
       searchHeadPanel.add(cityLabel);

       citySearchTextField = new TextInputField(80, 35, 140, 25,
               10, "EX: Montpellier...",
               theme.getBlack(), theme.getWhite(), font);
       searchHeadPanel.add(citySearchTextField);

       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        LabeledIcon fromDateLabel = new LabeledIcon("Date d'arrivée:",
                SwingConstants.LEADING,
                "application/icons/16x16/calendrier.png",
                245, 35, 115, 25,
                theme.getBlack(), theme.getWhite(),
                font);
       searchHeadPanel.add(fromDateLabel);

        int calendar = Calendar.DAY_OF_MONTH;
        Date initDate = new Date();
        SpinnerModel arrivalDateSpinnerModel = new SpinnerDateModel(initDate, null, null, calendar);


       fromDateSpinner = new JSpinner(arrivalDateSpinnerModel);
       JSpinner.DateEditor fromDateSpinnerEditor = new JSpinner.DateEditor(fromDateSpinner,
               ((SimpleDateFormat) dateFormat).toPattern());

       fromDateSpinner.setEditor(fromDateSpinnerEditor);
       fromDateSpinner.setBounds(360, 35, 120, 25);
       searchHeadPanel.add(fromDateSpinner);


        LabeledIcon toDateLabel = new LabeledIcon("Date de départ:",
                SwingConstants.LEADING,
                "application/icons/16x16/calendrier.png",
                550, 35, 125, 25,
                theme.getBlack(), theme.getWhite(),
                font);
       searchHeadPanel.add(toDateLabel);


       SpinnerModel departureDateSpinnerModel =  new SpinnerDateModel(initDate, null, null, calendar);

       toDateSpinner = new JSpinner(departureDateSpinnerModel);
       JSpinner.DateEditor toDateSpinnerEditor = new JSpinner.DateEditor(toDateSpinner, ((SimpleDateFormat) dateFormat).toPattern());
       toDateSpinner.setEditor(toDateSpinnerEditor);
       toDateSpinner.setBounds(675, 35, 140, 25);
       searchHeadPanel.add(toDateSpinner);

       SpinnerModel minPrice = new SpinnerNumberModel(1.0, 1.0, 1000.0, 0.1);
       SpinnerModel maxPrice = new SpinnerNumberModel(1.0, 1.0, 1000.0, 0.1);

        LabeledIcon minPriceLabel = new LabeledIcon("Prix minimum:",
                SwingConstants.LEADING,
                "application/icons/16x16/etiquette-de-prix-en-euros.png",
                10, 75, 140, 25,
                theme.getBlack(), theme.getWhite(),
                font);
       searchHeadPanel.add(minPriceLabel);

        minPriceSpinner = new BasicSpinner(minPrice,
                150, 75, 75, 20,
                theme.getBlack(), theme.getWhite(), font);
       searchHeadPanel.add(minPriceSpinner);


        LabeledIcon maxPriceLabel = new LabeledIcon("Prix maximum:",
                SwingConstants.LEADING,
                "application/icons/16x16/etiquette-de-prix-en-euros.png",
                245, 75, 140, 25,
                theme.getBlack(), theme.getWhite(),
                font);
       searchHeadPanel.add(maxPriceLabel);


        maxPriceSpinner = new BasicSpinner(maxPrice,
                400, 75, 125, 20,
                theme.getBlack(), theme.getWhite(), font);
       searchHeadPanel.add(maxPriceSpinner);


        LabeledIcon starsNumberLabel = new LabeledIcon("Nombre d'étoiles:",
                SwingConstants.LEADING,
                "application/icons/16x16/etoile.png",
                550, 75, 150, 25,
                theme.getBlack(), theme.getWhite(),
                font);
       searchHeadPanel.add(starsNumberLabel);

        starsNumberComboBox = new DropDown(List.of(options), theme.getBlack(),
                theme.getYelloowAccent(),125, 25);
       starsNumberComboBox.setBounds(700, 75, 125, 25);
       searchHeadPanel.add(starsNumberComboBox);


        LabeledIcon numberPersonLabel = new LabeledIcon("Nombre de personnes (10 max):",
                SwingConstants.LEADING,
                "application/icons/16x16/silhouette-dutilisateurs-multiples.png",
                10, 105, 260, 25,
                theme.getBlack(), theme.getWhite(),
                font);
        searchHeadPanel.add(numberPersonLabel);


        SpinnerModel nbPeople = new SpinnerNumberModel(1, 1, 10,1);

        numberPersonSpinner = new BasicSpinner(nbPeople,
                260, 105, 80, 20,
                theme.getBlack(), theme.getWhite(), font);
       searchHeadPanel.add(numberPersonSpinner);

       clientSpaceSearchButton = new JButton("Rechercher");
       clientSpaceSearchButton.setForeground(theme.getWhite());
       clientSpaceSearchButton.setBackground(theme.getBlack());
       clientSpaceSearchButton.setBounds(402, 105, 250, 25);
       searchHeadPanel.add(clientSpaceSearchButton);
   }

   private boolean validated() {
       if (citySearchTextField.getText().equals("EX: Montpellier...")
               || (((double) maxPriceSpinner.getValue()) < 1.0 )
               || (((double) maxPriceSpinner.getValue()) <= ((double) minPriceSpinner.getValue()))
               || ( (int) numberPersonSpinner.getValue() <=0)
               || ((int) numberPersonSpinner.getValue() > 10)
               || (((Date) toDateSpinner.getValue()).compareTo((Date) fromDateSpinner.getValue()) <=0 )
               || (((Date)  fromDateSpinner.getValue()).compareTo((Date) currentCalendar.getTime()) < 0)

       ) {
           //				citySearchTextField ville
           if (citySearchTextField.getText().equals("EX: Montpellier...")) {
               citySearchTextField.
                       setBackground(theme.getIndigo());
               citySearchTextField.setBorder(new LineBorder(theme.getYelloowAccent(), 2, true));
           } else {
               citySearchTextField.setBackground(theme.getBlack());
               citySearchTextField.setBorder(new LineBorder(theme.getGrey(), 1, true));
           }

//				maxPriceSpinner prix max
//				minPriceSpinner prix min
           if ((((double) maxPriceSpinner.getValue()) < 1.0 )
                   || (((double) maxPriceSpinner.getValue()) <= ((double) minPriceSpinner.getValue()))) {
               maxPriceSpinner.setBorder(new LineBorder(theme.getYelloowAccent(), 2, true));
           } else {

               maxPriceSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));
           }

           //				numberPersonSpinner numbre de personne
           if ( (int) numberPersonSpinner.getValue() <=0 || (int) numberPersonSpinner.getValue() > 10) {
               numberPersonSpinner.setBorder(new LineBorder(theme.getYelloowAccent(), 2, true));
           } else {
               numberPersonSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));
           }

           // fromDateSpinner
           if (((Date)  fromDateSpinner.getValue()).compareTo((Date) currentCalendar.getTime()) < 0) {
               fromDateSpinner.setBorder(new LineBorder(theme.getYelloowAccent(), 2, true));
           } else {
               fromDateSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));
           }

           // toDateSpinner checkout date
           if (((Date) toDateSpinner.getValue()).compareTo((Date) fromDateSpinner.getValue()) <=0) {
               toDateSpinner.
                       setBorder(new
                               LineBorder(theme.getYelloowAccent(), 2, true));
           } else {
               System.out.println("caleed "+((Date) toDateSpinner.getValue()).compareTo((Date) fromDateSpinner.getValue()));
               toDateSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));
           }
           return false;
       }
       else {

           citySearchTextField.setBackground(theme.getBlack());
           citySearchTextField.setBorder(new LineBorder(theme.getGrey(), 1, true));
           maxPriceSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));
           numberPersonSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));
           fromDateSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));
           toDateSpinner.setBorder(new LineBorder(theme.getGrey(), 1, true));

             return true;
       }

   }

    public void attachObserver(SearchEvent<BrowsingInfo> observer) {
        browsingEventObserver.add(observer);
    }

    public void detachObserver(SearchEvent<BrowsingInfo> observer) {
        browsingEventObserver.add(observer);
    }

    public void notifySearchEventObserver() {
        for(SearchEvent<BrowsingInfo> observer : browsingEventObserver) {
            observer.onSearch(currentSearch);
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

    @Override
    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public void setX(int x) {
        X = x;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public void setY(int y) {
        Y = y;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    @Override
    public void setWidth(int width) {
        Width = width;
    }

    @Override
    public int getHeight() {
        return Height;
    }

    @Override
    public void setHeight(int height) {
        Height = height;
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

    public String transformStarsToString(Double stars) {
        String generatedStars="";
        String filledStar = "\u2605";
        String outlinedStar = "\u2606";

        generatedStars += filledStar;
        for (int i = 1; i <5 ; i++) {
            stars-=1;
            if (stars>=1.0) {
                generatedStars+=filledStar;
            } else {
                generatedStars+=outlinedStar;
            }
        }

        return generatedStars;
    }

}
