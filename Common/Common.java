package com.example.issa.pdm_project_2018.Common;

import com.example.issa.pdm_project_2018.Model.User;

public class Common {
    public static User currentUser;

    public static final String DELETE = "Delete";

    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";

    public static String convertCodeToStatus(String code)
    {
        if (code.equals("0"))
            return "Placed";
        else
            return "On my way";
    }

    public static String convertCode1ToStatus(String code)
    {
        if (code.equals("0"))
            return "Placed";
        else
            return "Seen";
    }

}
