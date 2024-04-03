package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.table;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button.RoundTitleBarButtons;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class DisplayTable extends JTable {
    private int rowHeight;
    private Dimension preferredSize;
    private Color foregroundColor;
    private Color backgroundColor;
    private Color selectionForegroundColor;
    private Color selectionBackgroundColor;
    private Color gridColor;
    private String iconPathString;
    private Font font;
    private String name;
    private Theme theme ;

    private TableModel tableModel;



    static ClassLoader fileLoader = RoundTitleBarButtons.class.getClassLoader();

    public DisplayTable(TableModel dm, int rowHeight,
                        Dimension preferredSize,
                        Color foregroundColor,
                        Color backgroundColor,
                        Color selectionForegroundColor,
                        Color selectionBackgroundColor,
                        Color gridColor,
                        String name) {
        super(dm);
//        setTableModel(dm);
        this.rowHeight = rowHeight;
        this.preferredSize = preferredSize;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.selectionForegroundColor = selectionForegroundColor;
        this.selectionBackgroundColor = selectionBackgroundColor;
        this.gridColor = gridColor;
        this.name = name;
        this.theme = new Theme();

        initComponent();

    }

    private void initComponent() {
        setIntercellSpacing(new Dimension(0, 0));
        setShowVerticalLines(false);
        setShowHorizontalLines(false);
        setShowGrid(false);
//        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setFillsViewportHeight(true);
        setColumnSelectionAllowed(false);
        setBorder(null);
        setRowMargin(3);
        setIntercellSpacing(new Dimension(5, 7));

        //styling
        setPreferredSize(preferredSize==null?new Dimension(450, 700):preferredSize);
        setFont(font==null?new Font("Tahoma", Font.PLAIN, 22):font);
        setForeground(this.foregroundColor);
        setBackground(this.backgroundColor); // backgroundColor!=null?
        setSelectionForeground(this.selectionForegroundColor);
        setSelectionBackground(this.selectionBackgroundColor);

        setName(name);
//        setRowHeight(32);
        setRowHeight(this.rowHeight);

    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
        setModel(tableModel);
    }

    public void setIconPathString(String iconPathString) {
        this.iconPathString = iconPathString;
    }

//    @Override
//    public void setRowHeight(int rowHeight) {
//        this.rowHeight = rowHeight;
//    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setSelectionForegroundColor(Color selectionForegroundColor) {
        this.selectionForegroundColor = selectionForegroundColor;
    }

    public void setSelectionBackgroundColor(Color selectionBackgroundColor) {
        this.selectionBackgroundColor = selectionBackgroundColor;
    }

    @Override
    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }



}
