package com.swindler.site.DB.DAO;

import com.swindler.site.DB.DBController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sw on 26.03.14.
 */
@Component
public class UserDAOImpl implements IUserDAO {
    private JdbcTemplate db;
    private DefaultLdapAuthoritiesPopulator ldapAuthoritiesPopulator;
    private final String INSERT_NEW_UNIQUE_KEY = "INSERT INTO " +
            " public.user_reset_pass(uid,unique_key) VALUES(?,?); ";

    @Autowired
    public UserDAOImpl(DBController controller) {
        this.db = controller.getJdbcTemplate();
    }

    @Override
    public String getUserById() {
        List<Map<String, Object>> list = db.queryForList("select * from public.users");
        return Arrays.toString(list.get(0).values().toArray());
    }

    @Override
    public String getUserByEmail(String email) {
        return null;
    }

    @Override
    public String getResetPasswordUrl(int id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = getUserById() + new Date().getTime() + "secretsalt";

        md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();

        String key = new String(Base64.encode(digest));
        key = key.replace("/", "");
        Object[] params = new Object[]{id, key};
        int[] types = new int[]{Types.INTEGER, Types.VARCHAR};
        db.update(INSERT_NEW_UNIQUE_KEY, params, types);
        return key;
    }

    @Override
    public String getUserByName(String name) {
        return null;
    }
}
