package com.todo.company.hai704.restapi.Client.gui.components.basics.simple.table.models;


import com.todo.company.hai704.restapi.Client.entity.BookingAgency;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookingHistoryTableModel extends AbstractTableModel {
    private List<BookingAgency> bookings;

    private String[] columnNames = {"SNo.", "R\u00E9f\u00E9rence",
            "Prix",  "Personne principale"};

    Class[] columnTypes = new Class[] {
            Integer.class, String.class,  Double.class, String.class
    };

    boolean[] columnEditables = new boolean[] {
            false, false,  false,false

    };

    public BookingHistoryTableModel(List<BookingAgency> bookings) {
        this.bookings = bookings;
    }

    public List<BookingAgency> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingAgency> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnEditables[columnIndex];
    }



    @Override
    public int getRowCount() {
        return bookings.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BookingAgency bookingAgency = bookings.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bookingAgency.getId();
            case 1:
                return bookingAgency.getBookingReference();
            case 2:
                return bookingAgency.getTotalPrice();
            case 3:
                return bookingAgency.getMainPersonne();


            default:
                return null;
        }

    }


    public void updateBooking(List<BookingAgency> newBookings) {
        this.bookings = newBookings;
        fireTableDataChanged();
    }


    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public BookingAgency getBookingAtRow(int rowIndex) {
        return bookings.get(rowIndex);
    }
}
