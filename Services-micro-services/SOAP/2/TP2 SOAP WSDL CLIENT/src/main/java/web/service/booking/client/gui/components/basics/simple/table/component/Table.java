package web.service.booking.client.gui.components.basics.simple.table.component;

import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class Table extends JTable {
    private Dimension preferredSize;
    private Color foregroundColor;
    private Color backgroundColor;
    private Color selectionForegroundColor;
    private Color selectionBackgroundColor;
    private Color gridColor;
    private boolean editable=false;
    private Font font=new Font("Tahoma", Font.PLAIN, 14);
    private String name;
    private Theme theme = new Theme() ;

    private TableModel tableModel;

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, a default column model,
     * and a default selection model.
     *
     * @param dm the data model for the table
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public Table(TableModel dm,
                 Dimension preferredSize, Color foregroundColor,
                 Color backgroundColor, Color selectionForegroundColor,
                 Color selectionBackgroundColor, Color gridColor,
                 String name) {
        super(dm);
        this.preferredSize = preferredSize;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.selectionForegroundColor = selectionForegroundColor;
        this.selectionBackgroundColor = selectionBackgroundColor;
        this.gridColor = gridColor;
        this.name = name;
        this.tableModel = dm;
        initializeComponent();
    }

    private void initializeComponent() {
        setIntercellSpacing(new Dimension(0, 0));
        setShowVerticalLines(false);
        setShowHorizontalLines(false);
        setShowGrid(false);
        setFillsViewportHeight(true);
        setColumnSelectionAllowed(false);
        setBorder(null);
        setRowMargin(3);
        setIntercellSpacing(new Dimension(5, 7));

        //styling
        setFont(font);
        setForeground(this.foregroundColor);
        setBackground(this.backgroundColor); // backgroundColor!=null?
        setSelectionForeground(this.selectionForegroundColor);
        setSelectionBackground(this.selectionBackgroundColor);

        setName(name);
        setRowHeight(32);
        setRowHeight(this.rowHeight);
    }


    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getSelectionForegroundColor() {
        return selectionForegroundColor;
    }

    public void setSelectionForegroundColor(Color selectionForegroundColor) {
        this.selectionForegroundColor = selectionForegroundColor;
    }

    public Color getSelectionBackgroundColor() {
        return selectionBackgroundColor;
    }

    public void setSelectionBackgroundColor(Color selectionBackgroundColor) {
        this.selectionBackgroundColor = selectionBackgroundColor;
    }

    @Override
    public Color getGridColor() {
        return gridColor;
    }

    @Override
    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }
}
