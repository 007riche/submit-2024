package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.model;

import Common.hai704.TP1.Cabinet.Veterinaire.Animal;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PatientTableModel extends AbstractTableModel {
    private List<Animal> animals;
//    private AnimalFormEvent saveEvent;
    private String[] columnNames = {"Nom", "Nom du maître", "Race", "Espèce", "Suivi", "Modifier"};
    private boolean[] canEdit = new boolean [] { false, false, false, false, false, true};

    public PatientTableModel(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }



    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }



    @Override
    public int getRowCount() {
        return animals.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = animals.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return animal.getName();
            case 1:
                return animal.getMasterName();
            case 2:
                return animal.getBreed();
            case 3:
                return animal.getSpecy().getName();
            case 4:
                return animal.getFollowUpFile().getContent();
            case 5:
                return "";
            default:
                return null;
        }

    }


    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Animal getAnimalAtRow(int rowIndex) {
        return animals.get(rowIndex);
    }

//    public void setSaveEvent(AnimalFormEvent saveEvent) {
//        this.saveEvent = saveEvent;
//    }
    public void removeRow(int row) {
        animals.remove(row);
    }

//    public void updateAnimalList(Animal animal, int index) {
//        if (saveEvent.onSaved()) {
//            animals.remove(index);
//            animals.add(index, animal);
//        }
//
//    }
}
