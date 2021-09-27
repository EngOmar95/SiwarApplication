package com.Siwar.siwar_application;

import android.widget.EditText;

public class Node {

    String nameColumnDatabase;
    EditText editText;
    String Value;




    public Node(String nameColumnDatabase, String Value) {
        this.nameColumnDatabase = nameColumnDatabase;
        this.Value = Value;
    }

    public Node() {
    }

    public String getNameColumnDatabase() {
        return nameColumnDatabase;
    }

    public void setNameColumnDatabase(String nameColumnDatabase) {
        this.nameColumnDatabase = nameColumnDatabase;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public Node(String nameColumnDatabase, EditText editText) {
        this.nameColumnDatabase = nameColumnDatabase;
        this.editText = editText;
    }

}
