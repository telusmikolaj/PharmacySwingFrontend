package gui;

import model.Medicine;
import service.MedicineService;

import javax.swing.*;

import java.io.IOException;
import java.util.List;

public class PharmacyGUI extends JFrame {

    private MedicineService medicineService;
    private JPanel mainPanel;
    private JLabel medicineTableLabel;
    private JTable medicineTable;
    private JScrollPane medicineTableScrollPane;
    private JButton getMedicinesBtn;

    public PharmacyGUI(String title) throws Exception {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.medicineService = new MedicineService();

        getMedicinesBtn.addActionListener(e -> {
            try {
                List<Medicine> medicineList = medicineService.getAll();
                MedicineTableModel medicineTableModel = new MedicineTableModel(medicineList);
                medicineTable.setModel(medicineTableModel);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }



}
