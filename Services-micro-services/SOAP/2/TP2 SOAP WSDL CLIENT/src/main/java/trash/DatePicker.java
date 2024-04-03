//package trash;
//
//import com.toedter.calendar.JCalendar;
//
//import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.util.Date;
//
//public class DatePicker {
//    private JSpinner spinner;
//    private JDialog dialog;
//    private JCalendar calendar;
//    private SpinnerDateModel model;
//    private JLabel selectedDateTimeLabel;
//
//    public DatePicker() {
//        dialog = new JDialog();
//        dialog.setTitle("Select a Date and Time");
//        dialog.setModal(true);
//
//        model = new SpinnerDateModel();
//        spinner = new JSpinner(model);
//        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd HH:mm");
//        spinner.setEditor(editor);
//
//        calendar = new JCalendar();
//        calendar.addPropertyChangeListener("calendar", new PropertyChangeListenerImpl());
//        calendar.getDayChooser().addDateEvaluator(new com.toedter.calendar.IDateEvaluator() {
//            public boolean isSpecial(Date date) {
//                return date.before(new Date());
//            }
//
//            public Color getSpecialForegroundColor() {
//                return Color.RED;
//            }
//
//            public Color getSpecialBackroundColor() {
//                return null;
//            }
//
//            public String getSpecialTooltip() {
//                return "Date before today";
//            }
//
//            @Override
//            public boolean isInvalid(Date date) {
//                return false;
//            }
//
//            @Override
//            public Color getInvalidForegroundColor() {
//                return null;
//            }
//
//            @Override
//            public Color getInvalidBackroundColor() {
//                return null;
//            }
//
//            @Override
//            public String getInvalidTooltip() {
//                return null;
//            }
//        });
//
//        selectedDateTimeLabel = new JLabel("Selected date and time: ");
//
//        JPanel content = new JPanel(new BorderLayout());
//        content.add(calendar, BorderLayout.CENTER);
//        content.add(selectedDateTimeLabel, BorderLayout.SOUTH);
//
//        JPanel buttons = new JPanel(new FlowLayout());
//        JButton okButton = new JButton("OK");
//        okButton.addActionListener(new ActionListenerImpl());
//        JButton cancelButton = new JButton("Cancel");
//        cancelButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                dialog.dispose();
//            }
//        });
//        buttons.add(okButton);
//        buttons.add(cancelButton);
//
//        dialog.add(content, BorderLayout.CENTER);
//        dialog.add(buttons, BorderLayout.SOUTH);
//
//        spinner.addChangeListener((ChangeListener) new ChangeListenerImpl());
//    }
//
//    public void showDatePicker() {
//        dialog.pack();
//        dialog.setLocationRelativeTo(null);
//        dialog.setVisible(true);
//    }
//
//    private class ChangeListenerImpl implements ChangeListener {
//        public void stateChanged(ChangeEvent e) {
//            Date date = model.getDate();
//            calendar.setDate(date);
//            selectedDateTimeLabel.setText("Selected date and time: " + date);
//        }
//    }
//
//    private class PropertyChangeListenerImpl implements PropertyChangeListener {
//        public void propertyChange(PropertyChangeEvent e) {
//            Date date = calendar.getDate();
//            model.setValue(date);
//            selectedDateTimeLabel.setText("Selected date and time: " + date);
//        }
//    }
//
//    private class ActionListenerImpl implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            Date date = model.getDate();
//            System.out.println("Selected date and time: " + date);
//            dialog.dispose();
//        }
//    }
//
//    public static void main(String[] args) {
//        DatePicker picker = new DatePicker();
//        picker.showDatePicker();
//    }
//}
