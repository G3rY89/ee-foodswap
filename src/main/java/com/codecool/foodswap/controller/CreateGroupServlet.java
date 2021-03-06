package com.codecool.foodswap.controller;

import com.codecool.foodswap.dao.FoodDao;
import com.codecool.foodswap.dao.GroupDao;
import com.codecool.foodswap.dao.UserDao;
import com.codecool.foodswap.dao.implementation.FoodDaoImpl;
import com.codecool.foodswap.dao.implementation.GroupDaoImpl;
import com.codecool.foodswap.dao.implementation.UserDaoImpl;
import com.codecool.foodswap.model.Group;
import org.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class CreateGroupServlet extends HttpServlet {
    private GroupDao groupDao = GroupDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    private String name;
    private HttpSession session;

    public CreateGroupServlet(String name) {
        this.name = name;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.getWriter().write("Itt tucc majd eccer csopit létrehozni... eccer...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getParameter("submit-group") != null){
            StringBuffer jb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = req.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) { /*report an error*/ }
            JSONObject newGroup = new JSONObject(jb.toString());
            session = req.getSession();
            groupDao.add(new Group(newGroup.getString("group_name"),userDao.getUserById((Integer) session.getAttribute("uId"))));
        }
    }
}
