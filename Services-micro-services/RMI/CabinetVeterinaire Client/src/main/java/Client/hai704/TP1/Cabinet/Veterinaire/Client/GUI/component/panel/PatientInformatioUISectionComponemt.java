package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base.DropDown;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.table.EventDispatch;
import Common.hai704.TP1.Cabinet.Veterinaire.Animal;
import Common.hai704.TP1.Cabinet.Veterinaire.FollowUpFile;
import Common.hai704.TP1.Cabinet.Veterinaire.Specy;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PatientInformatioUISectionComponemt extends JPanel  implements EventDispatch {

    private List<AnimalFormEvent> observers = new ArrayList<>();

    static private int EventId;
    static private int editingRow;
    private JTextField nameEditTextField ;
    private JTextField masterNameEditTextField ;
    private JTextField breedEditTextField ;
//    private JTextField specyNameEditTextField ;
    private DropDown specyNameDropdownField ;
    private List<Specy> specyList;
    private JTextField specyLifeSpanEditTextField ;
    private JEditorPane followUpEditorPane;
    static ClassLoader fileLoader = PatientInformatioUISectionComponemt.class.getClassLoader();


    private Theme theme = new Theme();


    public PatientInformatioUISectionComponemt(List<Specy> specyList) {
        this.specyList = specyList;
        initComponent();
    }

    private JButton saveChangesButton ;

    private void initComponent() {
        setPreferredSize(new Dimension(500, 700));
        setLayout(null);
        setBounds(new Rectangle(0, 0, 500, 700));
        setBorder(null);
        setBackground(new Color(35, 35, 35));


        JLabel editInfoLabel = new JLabel("Informations du patient");
        editInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editInfoLabel.setForeground(new Color(175, 177, 179));
        editInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        editInfoLabel.setBounds(171, 10, 221, 31);
        add(editInfoLabel);

        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(35, 35, 35));
        namePanel.setBounds(60, 60, 460, 50);
        add(namePanel);

        JLabel nameEditLabel = new JLabel("Nom du patient");
        nameEditLabel.setIconTextGap(10);
        nameEditLabel.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/livestock.png")));
        nameEditLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameEditLabel.setForeground(new Color(175, 177, 179));
        nameEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameEditLabel.setBackground(new Color(35, 35, 35));

        nameEditTextField = new JTextField();
        nameEditTextField.setBackground(new Color(51, 51, 51));
        nameEditTextField.setSelectedTextColor(new Color(175, 177, 179));
        nameEditTextField.setForeground(new Color(175, 177, 179));
        nameEditTextField.setColumns(10);
        nameEditTextField.setCaretColor(theme.getYellow());
        nameEditTextField.getCaret().setBlinkRate(300);
        nameEditTextField.addFocusListener(new FocusListener() {
            String placeholder = "Ex: Crocs, Rax";
            @Override
            public void focusGained(FocusEvent e) {
                if (nameEditTextField.getText().contentEquals(placeholder)) {
                    nameEditTextField.setText("");
//                    nameEditTextField.setForeGroundColor(backGroundColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameEditTextField.getText().isEmpty()) {
                    nameEditTextField.setText(placeholder);
//                    nameEditTextField.setForeGroundColor(foreGroundColor);
                }
            }
        });
        GroupLayout gl_namePanel = new GroupLayout(namePanel);
        gl_namePanel.setHorizontalGroup(
                gl_namePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 460, Short.MAX_VALUE)
                        .addGroup(gl_namePanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nameEditLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(nameEditTextField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_namePanel.setVerticalGroup(
                gl_namePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addGroup(gl_namePanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_namePanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameEditLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(nameEditTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        namePanel.setLayout(gl_namePanel);

        JPanel masterNamePanel = new JPanel();
        masterNamePanel.setBackground(new Color(35, 35, 35));
        masterNamePanel.setBounds(60, 135, 460, 50);
        add(masterNamePanel);

        JLabel masterNameEditLabel = new JLabel("Nom du maitre");
        masterNameEditLabel.setIconTextGap(10);
        masterNameEditLabel.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/man.png")));
        masterNameEditLabel.setHorizontalAlignment(SwingConstants.CENTER);
        masterNameEditLabel.setForeground(new Color(175, 177, 179));
        masterNameEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        masterNameEditLabel.setBackground(new Color(35, 35, 35));

        masterNameEditTextField =  new JTextField();
        masterNameEditTextField.setBackground(new Color(51, 51, 51));
        masterNameEditTextField.setSelectedTextColor(new Color(175, 177, 179));
        masterNameEditTextField.setForeground(new Color(175, 177, 179));
        masterNameEditTextField.setColumns(10);
        masterNameEditTextField.setCaretColor(theme.getYellow());
        masterNameEditTextField.getCaret().setBlinkRate(300);
        masterNameEditTextField.addFocusListener(new FocusListener() {
            String placeholder = "Ex: Jimmy";
            @Override
            public void focusGained(FocusEvent e) {
                if (masterNameEditTextField.getText().contentEquals(placeholder)) {
                    masterNameEditTextField.setText("");
//                    masterNameEditTextField.setForeGroundColor(backGroundColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (masterNameEditTextField.getText().isEmpty()) {
                    masterNameEditTextField.setText(placeholder);
//                    masterNameEditTextField.setForeGroundColor(foreGroundColor);
                }
            }
        });
        GroupLayout gl_masterNamePanel = new GroupLayout(masterNamePanel);
        gl_masterNamePanel.setHorizontalGroup(
                gl_masterNamePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 460, Short.MAX_VALUE)
                        .addGroup(gl_masterNamePanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(masterNameEditLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(masterNameEditTextField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_masterNamePanel.setVerticalGroup(
                gl_masterNamePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addGroup(gl_masterNamePanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_masterNamePanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(masterNameEditLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(masterNameEditTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        masterNamePanel.setLayout(gl_masterNamePanel);

        JPanel breedNamePanel = new JPanel();
        breedNamePanel.setBackground(new Color(35, 35, 35));
        breedNamePanel.setBounds(60, 210, 460, 50);
        add(breedNamePanel);

        JLabel breedEditLabel = new JLabel("Race");
        breedEditLabel.setIconTextGap(10);
        breedEditLabel.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/claw-marks.png")));
        breedEditLabel.setHorizontalAlignment(SwingConstants.CENTER);
        breedEditLabel.setForeground(new Color(175, 177, 179));
        breedEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        breedEditLabel.setBackground(new Color(35, 35, 35));

        breedEditTextField = new JTextField();
        breedEditTextField.setBackground(new Color(51, 51, 51));
        breedEditTextField.setSelectedTextColor(new Color(175, 177, 179));
        breedEditTextField.setForeground(new Color(175, 177, 179));
        breedEditTextField.setColumns(10);
        breedEditTextField.setCaretColor(theme.getYellow());
        breedEditTextField.getCaret().setBlinkRate(300);
        breedEditTextField.addFocusListener(new FocusListener() {
            String placeholder = "Ex: Chiuaua";
            @Override
            public void focusGained(FocusEvent e) {
                if (breedEditTextField.getText().contentEquals(placeholder)) {
                    breedEditTextField.setText("");
//                    breedEditTextField.setForeGroundColor(backGroundColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (breedEditTextField.getText().isEmpty()) {
                    breedEditTextField.setText(placeholder);
//                    breedEditTextField.setForeGroundColor(foreGroundColor);
                }
            }
        });
        GroupLayout gl_breedNamePanel = new GroupLayout(breedNamePanel);
        gl_breedNamePanel.setHorizontalGroup(
                gl_breedNamePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 460, Short.MAX_VALUE)
                        .addGroup(gl_breedNamePanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(breedEditLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(breedEditTextField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_breedNamePanel.setVerticalGroup(
                gl_breedNamePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addGroup(gl_breedNamePanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_breedNamePanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(breedEditLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(breedEditTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        breedNamePanel.setLayout(gl_breedNamePanel);

        JPanel specyNamePanel = new JPanel();
        specyNamePanel.setBackground(new Color(35, 35, 35));
        specyNamePanel.setBounds(60, 360, 460, 50);
        add(specyNamePanel);

        JLabel specyNameEditLabel = new JLabel("Espece");
        specyNameEditLabel.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/paw.png")));
        specyNameEditLabel.setIconTextGap(10);
        specyNameEditLabel.setHorizontalAlignment(SwingConstants.CENTER);
        specyNameEditLabel.setForeground(new Color(175, 177, 179));
        specyNameEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        specyNameEditLabel.setBackground(new Color(35, 35, 35));

        specyNameDropdownField = new DropDown(List.of(generateNamesArray(this.specyList)),
                  theme.getBlack51(), theme.getGrey175(),
                255, 32);

        GroupLayout gl_specyNamePanel = new GroupLayout(specyNamePanel);
        gl_specyNamePanel.setHorizontalGroup(
                gl_specyNamePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 460, Short.MAX_VALUE)
                        .addGroup(gl_specyNamePanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(specyNameEditLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(specyNameDropdownField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_specyNamePanel.setVerticalGroup(
                gl_specyNamePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addGroup(gl_specyNamePanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_specyNamePanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(specyNameEditLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(specyNameDropdownField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        specyNamePanel.setLayout(gl_specyNamePanel);

        JPanel specyLifeSpanPanel = new JPanel();
        specyLifeSpanPanel.setBackground(new Color(35, 35, 35));
        specyLifeSpanPanel.setBounds(60, 285, 460, 50);

        add(specyLifeSpanPanel);

        JLabel specyLifeSpanEditLabel = new JLabel("Esperance de vie");
        specyLifeSpanEditLabel.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/time-half-past.png")));
        specyLifeSpanEditLabel.setIconTextGap(10);
        specyLifeSpanEditLabel.setHorizontalAlignment(SwingConstants.CENTER);
        specyLifeSpanEditLabel.setForeground(new Color(175, 177, 179));
        specyLifeSpanEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        specyLifeSpanEditLabel.setBackground(new Color(35, 35, 35));

        specyLifeSpanEditTextField = new JTextField();
        specyLifeSpanEditTextField.setBackground(new Color(51, 51, 51));
        specyLifeSpanEditTextField.setSelectedTextColor(new Color(175, 177, 179));
        specyLifeSpanEditTextField.setForeground(new Color(175, 177, 179));
        specyLifeSpanEditTextField.setColumns(10);
        specyLifeSpanEditTextField.setCaretColor(theme.getYellow());
        specyLifeSpanEditTextField.getCaret().setBlinkRate(300);
        specyLifeSpanEditTextField.addFocusListener(new FocusListener() {
            String placeholder = "(En nombre de jours) Ex: 300";
            @Override
            public void focusGained(FocusEvent e) {
                if (specyLifeSpanEditTextField.getText().contentEquals(placeholder)) {
                    specyLifeSpanEditTextField.setText("");
//                    specyLifeSpanEditTextField.setForeGroundColor(backGroundColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (specyLifeSpanEditTextField.getText().isEmpty()) {
                    specyLifeSpanEditTextField.setText(placeholder);
//                  specyLifeSpanEditTextField.setForeGroundColor(foreGroundColor);
                }
            }
        });
        GroupLayout gl_specyLifeSpanPanel = new GroupLayout(specyLifeSpanPanel);
        gl_specyLifeSpanPanel.setHorizontalGroup(
                gl_specyLifeSpanPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 460, Short.MAX_VALUE)
                        .addGroup(gl_specyLifeSpanPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(specyLifeSpanEditLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(specyLifeSpanEditTextField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_specyLifeSpanPanel.setVerticalGroup(
                gl_specyLifeSpanPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addGroup(gl_specyLifeSpanPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_specyLifeSpanPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(specyLifeSpanEditLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(specyLifeSpanEditTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        specyLifeSpanPanel.setLayout(gl_specyLifeSpanPanel);


        JPanel followUpPanel = new JPanel();
        followUpPanel.setBackground(new Color(35, 35, 35));
        followUpPanel.setBounds(60, 435, 460, 150);
        add(followUpPanel);

        JLabel followUpLabel = new JLabel("Suivi");
        followUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        followUpLabel.setForeground(new Color(175, 177, 179));
        followUpLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        followUpLabel.setBackground(new Color(35, 35, 35));

        followUpEditorPane = new JEditorPane();
        followUpEditorPane.setBackground(new Color(51, 51, 51));
        followUpEditorPane.setForeground(new Color(175, 177, 179));
        followUpEditorPane.setCaretColor(theme.getYellow());
        followUpEditorPane.getCaret().setBlinkRate(300);
        followUpEditorPane.addFocusListener(new FocusListener() {
            String placeholder = "Le contenu du suivi de l'état de santé du patient";
            @Override
            public void focusGained(FocusEvent e) {
                if (followUpEditorPane.getText().contentEquals(placeholder)) {
                    followUpEditorPane.setText("");
//                    followUpEditorPane.setForeGroundColor(backGroundColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (followUpEditorPane.getText().isEmpty()) {
                    followUpEditorPane.setText(placeholder);
//                    followUpEditorPane.setForeGroundColor(foreGroundColor);
                }
            }
        });
        GroupLayout gl_followUpPanel = new GroupLayout(followUpPanel);
        gl_followUpPanel.setHorizontalGroup(
                gl_followUpPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 460, Short.MAX_VALUE)
                        .addGroup(gl_followUpPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(followUpLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(followUpEditorPane, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                                .addGap(21))
        );
        gl_followUpPanel.setVerticalGroup(
                gl_followUpPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addGroup(gl_followUpPanel.createSequentialGroup()
                                .addGroup(gl_followUpPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_followUpPanel.createSequentialGroup()
                                                .addGap(5)
                                                .addComponent(followUpEditorPane, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                        .addGroup(gl_followUpPanel.createSequentialGroup()
                                                .addGap(48)
                                                .addComponent(followUpLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        followUpPanel.setLayout(gl_followUpPanel);

        saveChangesButton = new JButton("Enregistrer");
        saveChangesButton.setForeground(new Color(175, 177, 179));
        saveChangesButton.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/disk.png")));
        saveChangesButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        saveChangesButton.setBackground(new Color(35, 35, 35));
        saveChangesButton.setBounds(60, 600, 460, 40);
        saveChangesButton.setRequestFocusEnabled(false);
        saveChangesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("in Comp: eventid: "+ EventId);
                notifyObservers(EventId);
            }

        });
        add(saveChangesButton);

    }



    public int dispatchEventOnButton(int eventId) {
        return eventId;
    }

    public void fillFieldsWithAnimal(Animal animal) {
        nameEditTextField.setText(animal.getName());
        masterNameEditTextField.setText(animal.getMasterName());
        breedEditTextField.setText(animal.getBreed());
//        specyNameEditTextField.setText(animal.getSpecy().getName());
        specyLifeSpanEditTextField.setText(animal.getSpecy().getAverageLifeSpanInDays().toString());
        followUpEditorPane.setText(animal.getFollowUpFile().getContent());
    }

    public void resetFields() {
        nameEditTextField.setText("");
        masterNameEditTextField.setText("");
        breedEditTextField.setText("");
//        specyNameEditTextField.setText("");
        specyLifeSpanEditTextField.setText("");
        followUpEditorPane.setText("");
    }

    public Animal getUpdatedAnimalInformations() {
        Animal updatedAnimal = new Animal();

        updatedAnimal.setName(nameEditTextField.getText().trim());
        updatedAnimal.setMasterName(masterNameEditTextField.getText().trim());
        updatedAnimal.setBreed(breedEditTextField.getText().trim());
//        updatedAnimal.setSpecy(new Specy(specyNameEditTextField.getText().trim(),
//                Double.parseDouble(specyLifeSpanEditTextField.getText().trim())));
//        updatedAnimal.setSpecy((Specy) specyNameDropdownField.getSelected()
//                .setAverageLifeSpanInDays(Double.parseDouble(specyLifeSpanEditTextField.getText().trim())));
        updatedAnimal.setFollowUpFile(new FollowUpFile(followUpEditorPane.getText().trim()));
        resetFields();

        return updatedAnimal;
    }

    public void addObserver(AnimalFormEvent observer) {
        observers.add(observer);
    }


    private static String[] generateNamesArray(List<Specy> specyList) {
        String[] namesArray = new String[specyList.size()];
        for (int i = 0; i < specyList.size(); i++) {
            namesArray[i] = specyList.get(i).getName();
        }
        return namesArray;
    }
    @Override
    public void dispatchEvent(int EventId) {
        PatientInformatioUISectionComponemt.EventId = EventId;

    }
    public void notifyObservers(int EventId) {
        if(EventId == 1) {
            for (AnimalFormEvent observer : observers) {
                observer.onSaved(/*editingRow*/);
            }
        }

        if (EventId == 0) {
            for (AnimalFormEvent observer : observers) {
                observer.onAdd();
            }
        }

    }


}
