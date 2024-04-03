package com.todo.company.hai704.restapi.Client.gui.components.basics.compounded.cards;



import com.todo.company.hai704.restapi.Client.gui.components.utils.Theme;
import com.todo.company.hai704.restapi.Client.gui.events.RoomClickEvents;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedRoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OfferRoomCard extends JPanel {

	private int X;
	private int Y;
	private int Width;
	private int Height;
	private Color backGroundColor;
	private Color foreGroundColor;
	private Theme theme = new Theme();
	private List<RoomClickEvents> roomClickObservers = new ArrayList<RoomClickEvents>();
	private RetrievedRoom currentRoom;
	private boolean selected;
	

	public OfferRoomCard(boolean isDoubleBuffered,
						 RetrievedRoom currentRoom)
	{
		super(isDoubleBuffered);
		this.currentRoom = currentRoom;

		initializeComponent();

    }

	private void initializeComponent()  {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fromDateString = dateFormat.format(this.currentRoom.getFrom());
		String toDateString = dateFormat.format(this.currentRoom.getTo());
		System.out.println(this.currentRoom.getFrom()+" : "+this.currentRoom.getTo()+" : "+
				this.currentRoom.getPrice()+"$ : Room number"+this.currentRoom.getRoomNumber());

		selected=false;

        URL roomImgUrl = null;
        try {
            roomImgUrl = new URL(this.currentRoom.getImgUrl());
        } catch (MalformedURLException e) {
        }
		setLayout(null);
        setPreferredSize(new Dimension(200, 250));
		setBounds(0, 0, 200, 250);

		setBackground(theme.getBlack());
		setForeground(theme.getWhite());
		setBorder(new LineBorder(theme.getYelloowAccent()
				, 1, true));

        JButton roomMaximizeButton = null;
        try {
            roomMaximizeButton = new JButton(new ImageIcon(ImageIO.read(roomImgUrl)));
        } catch (IOException e) {
        }
        roomMaximizeButton.setBounds(1, 1, 200, 150);
		roomMaximizeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFrame imageDetail = new JFrame("Detail");

				try {
					URL imageUrl = new URL(currentRoom.getImgUrl());
					imageDetail.setSize(1440,900);
					ImageIcon imageIcon = new ImageIcon(imageUrl);
					JLabel imgLabel = new JLabel(imageIcon);
					imageDetail.getContentPane().add(imgLabel);
					imageDetail.setVisible(true);
				} catch (MalformedURLException e) {
				}
			}
		});

				add(roomMaximizeButton);

		JTextPane roomFromTextPane = new JTextPane();
		roomFromTextPane.setText("Dispo partir du: "+fromDateString);
		roomFromTextPane.setForeground(theme.getWhite());
		roomFromTextPane.setBackground(theme.getBlack());
		roomFromTextPane.setEditable(false);
		roomFromTextPane.setBounds(1, 150, 200, 20);
		add(roomFromTextPane);

		JTextPane roomToTextPane = new JTextPane();
		roomToTextPane.setText("Jusqu'au: "+toDateString);
		roomToTextPane.setForeground(theme.getWhite());
		roomToTextPane.setBackground(theme.getBlack());
		roomToTextPane.setEditable(false);
		roomToTextPane.setBounds(1, 170, 198, 20);
		add(roomToTextPane);

		JTextPane roomPriceTextPane = new JTextPane();
		roomPriceTextPane.setText("Lit(s): "+this.currentRoom.getNumberBed()+
				"    Prix: "+(Math.round(this.currentRoom.getPrice() * 100.0) / 100.0)+"\u20ac");
		roomPriceTextPane.setForeground(theme.getWhite());
		roomPriceTextPane.setBackground(theme.getBlack());
		roomPriceTextPane.setEditable(false);
		roomPriceTextPane.setBounds(1, 190, 198, 20);
		add(roomPriceTextPane);

		HashMap<Boolean, String> selectionOptionText=new HashMap<Boolean, String>();
		selectionOptionText.put(false, "Sélectionner");
		selectionOptionText.put(true, "Désélectionner");

		HashMap<Boolean, Color> selectionOptionBackgroundColor=new HashMap<Boolean, Color>();
		selectionOptionBackgroundColor.put(false, theme.getBlack());
		selectionOptionBackgroundColor.put(true, theme.getYelloowAccent());

		HashMap<Boolean, Color> selectionOptionForegroundColor=new HashMap<Boolean, Color>();
		selectionOptionForegroundColor.put(false, theme.getWhite());
		selectionOptionForegroundColor.put(true, theme.getBlack());

		JButton bookOfferButton = new JButton(selectionOptionText.get(selected));
		bookOfferButton.setForeground(theme.getWhite());
		bookOfferButton.setBackground(selectionOptionBackgroundColor.get(selected));
		bookOfferButton.setForeground(selectionOptionForegroundColor.get(selected));
		bookOfferButton.setBounds(1, 210, 200, 30);
		bookOfferButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selected=!selected;
				bookOfferButton.setText(selectionOptionText.get(selected));
				bookOfferButton.setBackground(selectionOptionBackgroundColor.get(selected));
				bookOfferButton.setForeground(selectionOptionForegroundColor.get(selected));
				bookOfferButton.repaint();
				notifyRoomClickEventObserver(selected);
			}
		});
		add(bookOfferButton);
	}

	public void attachObserver(RoomClickEvents observer) {
		roomClickObservers.add(observer);
	}

	public void detachObserver(RoomClickEvents observer) {
		roomClickObservers.add(observer);
	}

	public void notifyRoomClickEventObserver(boolean selection) {
		for(RoomClickEvents observer : roomClickObservers) {

			if (selection)
			{
				observer.onSelected(this.currentRoom);
			}
			else  {
				observer.onUnselected(this.currentRoom);
			}
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

	public List<RoomClickEvents> getRoomClickObservers() {
		return roomClickObservers;
	}

	public void setRoomClickObservers(List<RoomClickEvents> roomClickObservers) {
		this.roomClickObservers = roomClickObservers;
	}

	public RetrievedRoom getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(RetrievedRoom currentRoom) {
		this.currentRoom = currentRoom;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
