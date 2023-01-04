package com.datscie.cinematix.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class StudioComboBox extends AbstractListModel<Studio> implements ComboBoxModel<Studio> {
    ArrayList<Studio> studios;
    Studio selection;

    public StudioComboBox() {
        this.studios = new ArrayList<>();
    }

    public StudioComboBox(ArrayList<Studio> studios) {
        this.studios = studios;
    }

    @Override
    public int getSize() {
        return studios.size();
    }

    @Override
    public Studio getElementAt(int index) {
        return studios.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Studio) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
