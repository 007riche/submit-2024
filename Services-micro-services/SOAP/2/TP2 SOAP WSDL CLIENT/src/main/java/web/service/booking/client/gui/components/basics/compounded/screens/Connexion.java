package web.service.booking.client.gui.components.basics.compounded.screens;

import web.service.booking.client.gui.components.basics.simple.LabeledIcon;
import web.service.booking.client.gui.components.basics.simple.PasswordInputField;
import web.service.booking.client.gui.components.basics.simple.TextInputField;
import web.service.booking.client.gui.components.utils.Theme;
import web.service.booking.client.gui.events.ScreenSwitchEvent;
import web.service.booking.client.models.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Connexion extends JPanel {

    private int X;
    private int Y;
    private int Width;
    private int Height;
    private Color backGroundColor;
    private Color foreGroundColor;
    LabeledIcon switchIdLabel;
    TextInputField switchIdTextField;
    LabeledIcon switchPwdLabel;
    PasswordInputField switchPasswordField;
    JButton switchLoginButton;
    Theme theme = new Theme();
    User loggingUser = new User();
    JComponent target;
    
    private List<ScreenSwitchEvent> screenSwitchObservers = new ArrayList<ScreenSwitchEvent>();
    private Font font =  new Font("Tahoma", Font.PLAIN, 18);

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public Connexion(int x, int y, int width, int height,
                     Color backGroundColor, Color foreGroundColor, JComponent target) {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.target = target;
        initializeComponent();
    }

    void initializeComponent() {
        setLayout(null);
        setForeground(getForeGroundColor());
        setBackground(getBackGroundColor());
        setBounds(getX(), getY(), getWidth(), getHeight());

        switchIdLabel = new LabeledIcon("Identifiant:",
                SwingConstants.RIGHT, "application/icons/24x24/utilisateur.png",
                200, 200,  175, 30,
                theme.getBlack(), theme.getWhite(), font);
        add(switchIdLabel);

        switchIdTextField = new TextInputField(
                385, 200, 250, 30,
                30, "@Identifiant",
                theme.getBlack(), theme.getWhite(), font
                );
        add(switchIdTextField);

        switchPwdLabel = new LabeledIcon("Mot de passe:",
                SwingConstants.RIGHT, "application/icons/24x24/cadenas-verrouille.png",
                200, 260,  175, 30,
                theme.getBlack(), theme.getWhite(), font);
        add(switchPwdLabel);

        switchPasswordField= new PasswordInputField(
                385, 260, 250, 30,
                30, 4, 15,
                "c1@#4454kml$L",   theme.getBlack(), theme.getWhite()
        );
        add(switchPasswordField);

        switchLoginButton = new JButton("Connexion");
        switchLoginButton.setBounds(200, 325, 450, 35);
        switchLoginButton.setBackground(theme.getBlack());
        switchLoginButton.setForeground(theme.getWhite());
        switchLoginButton.setBorder(new LineBorder(theme.getYelloowAccent(), 3));
        add(switchLoginButton);

        switchLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean result = validated();
                if (result) {
                    if (switchIdTextField.getText().trim().contentEquals("root")
                    && switchPasswordField.getText().trim().contentEquals("root")) {
                        notifySearchEventObserver();
                        switchIdTextField.setText("@Identifiant");
                        switchPasswordField.setText("");
                    }

                }
            }
        });
    }

    private boolean validated() {
        if (switchIdTextField.getText().equals("@Identifiant")
                || switchIdTextField.getText().isBlank()
        || switchPasswordField.getText().equals("c1@#4454kml$L")
                || switchPasswordField.getText().isBlank()
        ) {
            if (switchIdTextField.getText().equals("@Identifiant")
                    || switchIdTextField.getText().isBlank()) {
                switchIdTextField.setBackground(theme.getIndigo());
                switchIdTextField.setBorder(new LineBorder(theme.getYelloowAccent()));
            } else  {
                switchIdTextField.setBackground(theme.getBlack());
                switchIdTextField.setBorder(new LineBorder(theme.getWhite()));
            }

            if (switchPasswordField.getText().equals("c1@#4454kml$L")
                    || switchPasswordField.getText().isBlank()) {
                switchPasswordField.setBackground(theme.getIndigo());
                switchPasswordField.setBorder(new LineBorder(theme.getYelloowAccent()));
            } else {
                switchPasswordField.setBackground(theme.getBlack());
                switchPasswordField.setBorder(new LineBorder(theme.getWhite()));
            }
            return false;
        } else {
            return true;
        }

    }
    public void attachObserver(ScreenSwitchEvent observer) {
        screenSwitchObservers.add(observer);
    }

    public void detachObserver(ScreenSwitchEvent observer) {
        screenSwitchObservers.add(observer);
    }

    public void notifySearchEventObserver() {
        for(ScreenSwitchEvent observer : screenSwitchObservers) {
            observer.onScreenSwitch(this.target);
        }
    }

    @Override
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    @Override
    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    @Override
    public int getHeight() {
        return Height;
    }

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
}
