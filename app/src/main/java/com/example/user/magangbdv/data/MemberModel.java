
package com.example.user.magangbdv.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberModel {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("user_list")
    @Expose
    private List<UserList> userList = null;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<UserList> getUserList() {
        return userList;
    }

    public void setUserList(List<UserList> userList) {
        this.userList = userList;
    }

}
