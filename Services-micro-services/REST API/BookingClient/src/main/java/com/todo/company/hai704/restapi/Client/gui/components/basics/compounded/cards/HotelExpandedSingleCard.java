package com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.cards;

import com.todo.company.hai704.restapi.Client.gui.components.basics.simple.SimplePanel;
import com.todo.company.hai704.restapi.Client.gui.components.utils.ResourceLoader;
import com.todo.company.hai704.restapi.Client.gui.components.utils.Theme;
import com.todo.company.hai704.restapi.Client.gui.events.HotelClickEvents;
import com.todo.company.hai704.restapi.Client.gui.events.TabEvent;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.BrowsingInfo;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedHotel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HotelExpandedSingleCard extends JPanel {

	private List<HotelClickEvents> hotelClickObservers = new ArrayList<HotelClickEvents>();

	private List<TabEvent> tabEventListeners = new ArrayList<>();
	private Theme theme = new Theme();

	private RetrievedHotel currentHotel = new RetrievedHotel();

	private BrowsingInfo browsingInfo;


	ResourceLoader resourceLoader = new ResourceLoader();

	public HotelExpandedSingleCard( boolean isDoubleBuffered, RetrievedHotel hotel,
									BrowsingInfo browsingInfo, SimplePanel tab
	)
//			throws IOException
	{
		super(isDoubleBuffered);
		this.currentHotel = hotel;
		this.browsingInfo = browsingInfo;
		setLayout(null);
		setPreferredSize(new Dimension(750, 250));
//	resultHotelAvailabilityGridPanel.add(hotelCard1);

		setBackground(theme.getBlack());
		JPanel innerHotelCard = new JPanel();
		innerHotelCard.setLayout(null);
		innerHotelCard.setBorder(new LineBorder(theme.getYelloowAccent()
				, 1, true));
		innerHotelCard.setBackground(theme.getBlack());
		innerHotelCard.setBounds(25, 25, 700, 200);
		add(innerHotelCard);

		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(null);
		imgPanel.setBackground(theme.getBlack());
		imgPanel.setBounds(15, 3, 200, 192);
		innerHotelCard.add(imgPanel);

        URL imageUrl = null;
        try {
            imageUrl = new URL(this.currentHotel.getImgUrl());
        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
        }

        JButton imgButton = null;
        try {
            assert imageUrl != null;
            imgButton = new JButton(new ImageIcon(ImageIO.read(imageUrl)));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
        imgButton.setBounds(3, 7, 180, 180);
		URL finalImageUrl = imageUrl;
		imgButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFrame imageDetail = new JFrame("Detail");

				imageDetail.setSize(1280,900);
//				System.err.println("this clicked");
				ImageIcon imageIcon = new ImageIcon(finalImageUrl);
				JLabel imgLabel = new JLabel(imageIcon);
				imageDetail.getContentPane().add(imgLabel);
				imageDetail.setVisible(true);
			}
		});
		imgPanel.add(imgButton);

		JPanel hotelNamePanel = new JPanel();
		hotelNamePanel.setLayout(null);
		hotelNamePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		hotelNamePanel.setBackground(theme.getBlack());
		hotelNamePanel.setBounds(215, 3, 476, 36);
		innerHotelCard.add(hotelNamePanel);

		JTextPane hotelNameTextPane = new JTextPane();
		hotelNameTextPane.setText(this.currentHotel.getHotelName().toLowerCase().contains("hotel") ?
				this.currentHotel.getHotelName().toUpperCase() :"HOTEL "+
				this.currentHotel.getHotelName().toUpperCase());
		hotelNameTextPane.setBackground(theme.getBlack());
		hotelNameTextPane.setForeground(theme.getWhite());
		hotelNameTextPane.setFont(new Font("Liberation Serif", Font.BOLD | Font.ITALIC, 20));
		hotelNameTextPane.setEditable(false);
		hotelNameTextPane.setBounds(2, 4, 470, 30);
		hotelNamePanel.add(hotelNameTextPane);

		JPanel starsPanel = new JPanel();
		starsPanel.setLayout(null);
		starsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		starsPanel.setBackground(theme.getBlack());
		starsPanel.setBounds(215, 40, 476, 40);
		innerHotelCard.add(starsPanel);

		JTextPane starsTextPane = new JTextPane();
		starsTextPane.setText(transformStarsToString(this.currentHotel.getStars()));
		starsTextPane.setForeground(theme.getYelloowAccent());
		starsTextPane.setBackground(theme.getBlack());
		starsTextPane.setFont(new Font("Dialog", Font.BOLD, 24));
		starsTextPane.setEditable(false);
		starsTextPane.setBounds(2, 0, 450, 30);
		starsPanel.add(starsTextPane);

		JPanel consultAvailabiltyButtonPanel = new JPanel();
		consultAvailabiltyButtonPanel.setLayout(null);
		consultAvailabiltyButtonPanel.setBackground(theme.getBlack());
		consultAvailabiltyButtonPanel.setBounds(215, 165, 475, 25);
		innerHotelCard.add(consultAvailabiltyButtonPanel);

		JButton consultAvailabilityButton = new JButton("Voir les disponibilités >");
		consultAvailabilityButton.setForeground(theme.getWhite());
		consultAvailabilityButton.setBackground(theme.getBlack());
		consultAvailabilityButton.setBounds(0, 0, 473, 25);
		consultAvailabilityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					notifyHotelSelectionEventObserver();
					notifyTabEventListeners(tab);
			}
		});
		consultAvailabilityButton.addActionListener(null);

		consultAvailabiltyButtonPanel.add(consultAvailabilityButton);

		JPanel previewAvailabilityPanel = new JPanel();
		previewAvailabilityPanel.setLayout(null);
		previewAvailabilityPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		previewAvailabilityPanel.setBackground(theme.getBlack());
		previewAvailabilityPanel.setBounds(215, 80, 476, 92);
		innerHotelCard.add(previewAvailabilityPanel);

		JTextPane addresTextPane = new JTextPane();
		addresTextPane.setText("Adresse: "+this.currentHotel.getHotelAdresse()+"\n");
		addresTextPane.setEditable(false);
		addresTextPane.setBackground(theme.getBlack());
		addresTextPane.setForeground(theme.getWhite());
		addresTextPane.setBounds(2, 12, 241, 71);
		previewAvailabilityPanel.add(addresTextPane);

		JTextPane availabilityPreviewTextPane = new JTextPane();
		availabilityPreviewTextPane.setText("A partie de: "+ Math.round(this.currentHotel.getPriceMin())
				+"$\n"+this.currentHotel.getRoombeds()+" lits\n");
		availabilityPreviewTextPane.setEditable(false);
		availabilityPreviewTextPane.setBackground(theme.getBlack());
		availabilityPreviewTextPane.setForeground(theme.getWhite());
		availabilityPreviewTextPane.setBounds(241, 12, 225, 71);
		previewAvailabilityPanel.add(availabilityPreviewTextPane);

	}

	public void attachObserver(HotelClickEvents observer) {
		hotelClickObservers.add(observer);
	}

	public void detachObserver(HotelClickEvents observer) {
		hotelClickObservers.add(observer);
	}

	public void notifyHotelSelectionEventObserver() {
		for(HotelClickEvents observer : hotelClickObservers) {
			observer.onSelected(currentHotel, this.browsingInfo);
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


	public static String transformStarsToString(Double stars) {
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
