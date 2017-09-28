package com.example.user.magangbdv.data;

import com.example.user.magangbdv.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by User on 18/09/2017.
 */

@Table( database = MyDatabase.class)
public class EmailData extends BaseModel {

    @Column
    @PrimaryKey
    int id;
    @Column
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
