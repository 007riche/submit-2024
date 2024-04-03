package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.table;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PanelTableCellRender extends DefaultTableCellRenderer {
    Theme theme = new Theme();
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component initialComp =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        CellPanelAction panelAction = new CellPanelAction();

        if (isSelected) {
            panelAction.setBackground(theme.getGrey205a());

        }
        else {
            panelAction.setBackground(theme.getBlack35());
        }

        return panelAction;
    }
}
