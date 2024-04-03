package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.model;

import Common.hai704.TP1.Cabinet.Veterinaire.Specy;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SpecyTableModel extends AbstractTableModel {
    private List<Specy> species;
    private String[] columnNames = {"Nom de l'espèce", "Durée de vie moyenne"};


    public SpecyTableModel(List<Specy> species) {
        this.species = species;
    }

    @Override
    public int getRowCount() {
        return species.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Specy specy = species.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return specy.getName();
            case 1:
                return specy.getAverageLifeSpanInDays();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Specy getSpecyAtRow(int rowIndex) {
        return species.get(rowIndex);
    }
}
