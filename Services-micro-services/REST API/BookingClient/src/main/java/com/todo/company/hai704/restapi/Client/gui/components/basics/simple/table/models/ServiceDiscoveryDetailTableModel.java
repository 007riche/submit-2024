package com.todo.company.hai704.restapi.Client.gui.components.basics.simple.table.models;

import com.todo.company.hai704.restapi.Client.entity.NodeService;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ServiceDiscoveryDetailTableModel extends AbstractTableModel {

    private List<NodeService> services=new ArrayList<>();

    	private  String[] columnNames = {
                "Hotel Name",
                "Login",
                "Password",
                "Booking URL",
                "Browsing URL",
                "Partnership URL"
    };

    boolean[] columnEditables = new boolean[] {
            false,
            true,
            true,
            false,
            false,
            false,
    };

    public ServiceDiscoveryDetailTableModel(List<NodeService> services) {
        this.services = services;

    }

    @Override
    public int getRowCount() {
        return services.size() ;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnEditables[rowIndex];
    }


    @Override
    public String  getValueAt(int rowIndex,
                              int columnIndex) {
        NodeService service= services.get(rowIndex);
        return switch (columnIndex) {
            case 1 -> service.getLoginId();
            case 2 -> service.getPassword();
            default -> null;
        };

    }
}
