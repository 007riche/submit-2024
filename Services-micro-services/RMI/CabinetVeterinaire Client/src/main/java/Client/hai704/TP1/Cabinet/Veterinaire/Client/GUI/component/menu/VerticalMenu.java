package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.menu;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.AppTitlePanel;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.VerticalMenuButtonPanel;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.VerticalMenuIconButton;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.ApplicationSectionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class VerticalMenu extends JPanel {
    private AppTitlePanel appIconTitlePanel;
    private List<VerticalMenuButtonPanel> menuButtonPanels;
    private  GroupLayout layout;

    private static String currentSectionTitle = "";
    private Theme theme = new Theme();

    //      For all the obserservers which listen to the section changes of this observable.
    //      This component is the observable
    //      Tightly couple to the concrete type of Observers, here  `ApplicationSectionEvent`
    private List<ApplicationSectionEvent> observersOfVerticalSectionChange= new ArrayList<ApplicationSectionEvent>();

    public VerticalMenu(AppTitlePanel appIconTitlePanel, List<VerticalMenuButtonPanel> menuButtonPanels) {
        this.appIconTitlePanel = appIconTitlePanel;
        this.menuButtonPanels = menuButtonPanels;
        initComponent();
    }

    private void initComponent() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {

                }
            }
        });

        setBounds(new Rectangle(0, 0, 450, 660));
        setBorder(null);
        setPreferredSize(new Dimension(450, 700));
        setMinimumSize(new Dimension(450, 700));
        setForeground(theme.getGrey175());
        setBackground(theme.getBlack29());

         layout=new GroupLayout(this);

        // Set preferred size for appTitlePanel
        appIconTitlePanel.setPreferredSize(new Dimension(450, 86));

        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hGroup.addComponent(appIconTitlePanel, GroupLayout.PREFERRED_SIZE, 480, Short.MAX_VALUE);
        GroupLayout.SequentialGroup sequentialGroup = layout.createSequentialGroup();
        GroupLayout.ParallelGroup subHGroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false);

        for (int index=0; index<menuButtonPanels.size(); index++) {
            if(index==0) {
                subHGroup.addComponent(menuButtonPanels.get(index), GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE);
            }
            else {
                subHGroup.addComponent(menuButtonPanels.get(index),
                        GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
            }
        }
        sequentialGroup.addGroup(subHGroup).addContainerGap();
        hGroup.addGroup(sequentialGroup);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup subVGroup = layout.createSequentialGroup();
        subVGroup.addContainerGap();
        subVGroup.addComponent(appIconTitlePanel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE);
        subVGroup.addGap(140);

        for (int i=0; i<menuButtonPanels.size(); i++) {
            subVGroup.addComponent(menuButtonPanels.get(i),
                    GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE);
            if(i==0) {
                subVGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
            }
            else {
                subVGroup.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED);
            }
        }
        subVGroup.addContainerGap(250, Short.MAX_VALUE);
        vGroup.addGroup(subVGroup);
        layout.setVerticalGroup(vGroup);

        setLayout(layout);

        for (VerticalMenuButtonPanel buttonPanel : menuButtonPanels) {
            buttonPanel.getInnerButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Ensure that mouseClicked is used to capture the click event
                    setSelectedButton((VerticalMenuIconButton) buttonPanel.getInnerButton());
                }
            });
        }

    }

    public void addButtonPanel(VerticalMenuButtonPanel buttonPanel) {

        menuButtonPanels.add(buttonPanel);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(appIconTitlePanel)
                .addComponent(buttonPanel));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(appIconTitlePanel)
                .addComponent(buttonPanel));
        revalidate();
        repaint();
    }

    public void setSelectedButton(VerticalMenuIconButton selectedButton) {

        for (VerticalMenuButtonPanel buttonPanel: menuButtonPanels ) {
            buttonPanel.setSelected(selectedButton == buttonPanel.getInnerButton());
            if (selectedButton == buttonPanel.getInnerButton())
                currentSectionTitle=buttonPanel.getInnerButton().getText();
        }
//        System.out.println("VertButton Click event: val: "+currentSectionTitle);
        notifyObservers();
        getTopLevelAncestor().repaint();
    }

    public void attach(ApplicationSectionEvent newObserver) {
        observersOfVerticalSectionChange.add(newObserver);
    }

    public void detach(ApplicationSectionEvent observer) {
        observersOfVerticalSectionChange.remove(observer);
    }

    public void notifyObservers() {
        for (ApplicationSectionEvent o: observersOfVerticalSectionChange) {
            o.onChange(currentSectionTitle);
        }
    }

}
