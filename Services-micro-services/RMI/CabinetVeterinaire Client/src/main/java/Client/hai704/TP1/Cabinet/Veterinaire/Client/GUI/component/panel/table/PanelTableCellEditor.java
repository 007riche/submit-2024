package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.table;

import javax.swing.*;
import java.awt.*;

public class PanelTableCellEditor extends DefaultCellEditor {
    private CellActionEvent event;

    public PanelTableCellEditor(CellActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CellPanelAction action = new CellPanelAction();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
}
