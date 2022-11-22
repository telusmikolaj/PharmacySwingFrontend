package gui;

import model.Medicine;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class MedicineTableModel extends AbstractTableModel {

    private final static String[] COLUMNS = {"id", "Name", "Expiration date", "Quantity"};
    private List<Medicine> medicineList;

    public MedicineTableModel(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    @Override
    public int getRowCount() {
        return medicineList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> this.medicineList.get(rowIndex).getId();
            case 1 -> this.medicineList.get(rowIndex).getName();
            case 2 -> this.medicineList.get(rowIndex).getExpirationDate();
            case 3 -> this.medicineList.get(rowIndex).getQuantity();
            default -> "-";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        } else {
            return Object.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> this.medicineList.get(rowIndex).setId((Long) aValue);
            case 1 -> this.medicineList.get(rowIndex).setName((String) aValue);
            case 2 -> this.medicineList.get(rowIndex).setExpirationDate((Date) aValue);
            case 3 -> this.medicineList.get(rowIndex).setQuantity((Integer) aValue);
        };
    }
}
