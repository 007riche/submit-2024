package com.todo.company.hai704.restapi.Client.gui.components.basics.simple.table.models;



import com.todo.company.hai704.restapi.Client.entity.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {

    private List<Client> clients;

    private String[] columnNames = {"SNo.", "Nom comple",  "Numero de carte"};

    Class[] columnTypes = new Class[] {
            Integer.class, String.class,   String.class
    };

    boolean[] columnEditables = new boolean[] {
            false, false,  false

    };

    public ClientTableModel(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getBookings() {
        return clients;
    }

    public void setBookings(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnEditables[columnIndex];
    }



    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return client.getId();
            case 1:
                return client.getLastName()+" "+client.getLastName();
            case 2:
                return client.getCardNumber();
            default:
                return null;
        }

    }

    public void updateClients(List<Client> newClients) {
        this.clients = newClients;
        fireTableDataChanged();
    }


    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Client getClientAtRow(int rowIndex) {
        return clients.get(rowIndex);
    }
}
