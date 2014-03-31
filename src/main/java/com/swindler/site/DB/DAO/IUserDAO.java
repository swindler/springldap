package com.swindler.site.DB.DAO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sw on 26.03.14.
 */
public interface IUserDAO {
    public String getUserById();

    public String getUserByEmail(String email);

    public String getResetPasswordUrl(int id) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    public String getUserByName(String name);
}
