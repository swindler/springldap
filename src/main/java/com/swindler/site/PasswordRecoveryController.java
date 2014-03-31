package com.swindler.site;

import com.swindler.site.DB.DAO.IUserDAO;
import com.swindler.site.DB.DBController;
import com.swindler.site.mailer.AsyncMailSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeMessage;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.ExtendedRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sw on 26.03.14.
 */
@Controller
public class PasswordRecoveryController {
    private Logger log = Logger.getLogger(PasswordRecoveryController.class);
    @Autowired
    private IUserDAO user;
    @Autowired
    private DBController controller;
    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private AsyncMailSender mailSender;

    @RequestMapping(method = RequestMethod.GET, value = "/reset")
    public String getMail(ModelMap model) {
        return "reset";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reset")
    public ModelAndView resetPassword(ModelAndView model, @RequestParam(value = "email", required = true) String email, HttpServletRequest request) {
        String key = new String();
        model.setViewName("reset");
        List<String> user_uids = ldapTemplate.search(
                "ou=users", "(mail=" + email + ")",
                new AttributesMapper() {
                    public Object mapFromAttributes(Attributes attrs)
                            throws NamingException, javax.naming.NamingException {
                        return attrs.get("uid").get();
                    }
                });
        if (user_uids.size() == 1) {
            model.addObject("string", user_uids.get(0));
        }
        JdbcTemplate db = controller.getJdbcTemplate();
        final String select = "SELECT u.id FROM public.users u " +
                " WHERE name = ? LIMIT 1";
        int[] types = new int[]{Types.VARCHAR};
        Object[] params = new Object[]{user_uids.get(0)};
        RowMapper<String> rm = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("id");
            }
        };
        List<String> usernames = db.query(select, params, types, rm);
        String ids = usernames.get(0);
        int id = Integer.valueOf(ids).intValue();
        try {
            key = user.getResetPasswordUrl(id);
        } catch (NoSuchAlgorithmException ex) {
        } catch (UnsupportedEncodingException ex) {
        }
        String baseUrl = String.format("%s://%s:%d/reset/", request.getScheme(), request.getServerName(), request.getServerPort());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setSubject("Recovery password notification");
            helper.setText("<a href='" + baseUrl + "/" + key + "'>Click me to recover password</a>", true);
            helper.setFrom("mailer@mail.com");
            helper.setTo("mailer@mail.com");
            mailSender.send(message);
        } catch (Exception ex) {

        }

        // model.addObject("string", baseUrl+key);
        //TODO: create link to reset password
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reset/{key}")
    public ModelAndView resetPassForm(ModelAndView model, HttpServletRequest request, @PathVariable(value = "key") String key) {
        JdbcTemplate db = controller.getJdbcTemplate();
        final String select = "SELECT u.name FROM public.user_reset_pass p " +
                "JOIN public.users u on u.id = p.uid " +
                "WHERE unique_key = ? LIMIT 1";
        int[] types = new int[]{Types.VARCHAR};
        Object[] params = new Object[]{key};
        RowMapper<String> rm = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("name");
            }
        };
        List<String> usernames = db.query(select, params, types, rm);
        String username = usernames.get(0);

        model.setViewName("changepass");
        model.addObject("username", username);
        return model;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reset/{key}")
    public String ldapPasswordReset(ModelAndView model, HttpServletRequest request, @RequestParam(value = "password") String password, @PathVariable(value = "key") String key) {
        JdbcTemplate db = controller.getJdbcTemplate();
        final String select = "SELECT u.name,u.id FROM public.user_reset_pass p " +
                "JOIN public.users u on u.id = p.uid " +
                "WHERE unique_key = ? LIMIT 1";
        final String delete_used_key = "DELETE FROM public.user_reset_pass" +
                " WHERE uid=?";
        int[] types = new int[]{Types.VARCHAR};
        Object[] params = new Object[]{key};
        RowMapper<Map<String,String>> rm = new RowMapper<Map<String,String>>() {
            @Override
            public Map<String,String> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String,String> map = new HashMap<String, String>();
                map.put("name",resultSet.getString("name"));
                map.put("id",resultSet.getString("id"));
                return map;
            }
        };
        List<Map<String,String>> usernames = db.query(select, params, types, rm);
        String username = usernames.get(0).get("name");
        String id = usernames.get(0).get("id");
        log.error("!!!!!!!!!!!!!!"+id);
        ModificationItem[] mods = new ModificationItem[]{new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword",new LdapShaPasswordEncoder().encodePassword(password,null) ))};
        ldapTemplate.modifyAttributes("uid=" + username + ",ou=users", mods);
        Object[] params_del = new Object[]{Integer.getInteger(id)};
        db.update(delete_used_key,params_del,new int[]{Types.INTEGER});


        return "login";
    }
}
