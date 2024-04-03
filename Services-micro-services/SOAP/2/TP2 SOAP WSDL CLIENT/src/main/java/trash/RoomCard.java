package trash;

import web.service.booking.client.models.RetrievedRoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

//*********************************** ROOM CARD  ***********************************
public class RoomCard extends JPanel {
  private static final long serialVersionUID = 1L;
	private String imgUrl;
  private Date from;
  private Date to;
  private Double price;
  private RetrievedRoom room;

  public RoomCard() {
  }

  public RoomCard(RetrievedRoom room) {
      this.imgUrl = imgUrl;
      this.from = from;
      this.to = to;
      this.price = price;

      BufferedImage myPicture = null;
      try {
          myPicture = ImageIO.read(new File("/home/richard/hotels.jpg"));
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

//      JPanel panel = new JPanel();
//      panel.setBorder(null);
//      FlowLayout flowLayout = (FlowLayout) panel.getLayout();
//      flowLayout.setVgap(7);
//      flowLayout.setHgap(0);
//      panel.setPreferredSize(new Dimension(300, 300));
//      panel.setBounds(43, 52, 271, 270);
//      frame.getContentPane().add(panel);

      JPanel innerContent = new JPanel();
      this.add(innerContent);
      FlowLayout fl_innerContent = (FlowLayout) innerContent.getLayout();
      fl_innerContent.setVgap(0);
      fl_innerContent.setHgap(0);
      innerContent.setAlignmentY(Component.TOP_ALIGNMENT);
      innerContent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      innerContent.setAlignmentX(Component.LEFT_ALIGNMENT);
      innerContent.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      innerContent.setMaximumSize(new Dimension(300, 400));
      innerContent.setPreferredSize(new Dimension(250, 250));
      innerContent.setAutoscrolls(true);

      JLabel img = new JLabel(new ImageIcon(myPicture));
      img.setMaximumSize(new Dimension(415, 150));
      img.setMinimumSize(new Dimension(415, 150));
      img.setOpaque(true);
      img.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      img.setVerticalAlignment(SwingConstants.TOP);
      img.setHorizontalTextPosition(SwingConstants.LEFT);
      img.setBorder(new LineBorder(new Color(0, 0, 0), 2));
      img.setPreferredSize(new Dimension(250, 150));
      innerContent.add(img);

      JLabel FromLabel = new JLabel("  De:");
      FromLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      FromLabel.setMinimumSize(new Dimension(24, 25));
      FromLabel.setMaximumSize(new Dimension(24, 25));
      FromLabel.setPreferredSize(new Dimension(250, 25));
      FromLabel.setVerticalAlignment(SwingConstants.TOP);
      FromLabel.setRequestFocusEnabled(false);
      innerContent.add(FromLabel);

      JLabel ToLabel = new JLabel("  A: ");
      ToLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      ToLabel.setMinimumSize(new Dimension(18, 25));
      ToLabel.setMaximumSize(new Dimension(18, 25));
      ToLabel.setVerticalAlignment(SwingConstants.TOP);
      ToLabel.setRequestFocusEnabled(false);
      ToLabel.setPreferredSize(new Dimension(250, 25));
      innerContent.add(ToLabel);

      JLabel PriceLabel = new JLabel("  Prix: ");
      PriceLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      PriceLabel.setMaximumSize(new Dimension(35, 25));
      PriceLabel.setMinimumSize(new Dimension(35, 25));
      PriceLabel.setVerticalAlignment(SwingConstants.TOP);
      PriceLabel.setRequestFocusEnabled(false);
      PriceLabel.setPreferredSize(new Dimension(250, 25));
      innerContent.add(PriceLabel);

      JButton BookingButton = new JButton("Resever");
      BookingButton.setIconTextGap(0);
      BookingButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      BookingButton.setVerifyInputWhenFocusTarget(false);
      BookingButton.setBorder(null);
      BookingButton.setForeground(new Color(255, 255, 255));
      BookingButton.setBackground(new Color(26, 95, 180));
      BookingButton.setMargin(new Insets(0, 0, 0, 0));
      BookingButton.setPreferredSize(new Dimension(250, 25));
      BookingButton.setMaximumSize(new Dimension(250, 50));
      innerContent.add(BookingButton);
  }

  public String getImgUrl() {
      return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
  }

  public Date getFrom() {
      return from;
  }

  public void setFrom(Date from) {
      this.from = from;
  }

  public Date getTo() {
      return to;
  }

  public void setTo(Date to) {
      this.to = to;
  }

  public Double getPrice() {
      return price;
  }

  public void setPrice(Double price) {
      this.price = price;
  }
}