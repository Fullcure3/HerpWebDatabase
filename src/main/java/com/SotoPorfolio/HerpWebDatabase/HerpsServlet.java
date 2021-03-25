package com.SotoPorfolio.HerpWebDatabase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HerpsServlet", urlPatterns = {"/herps"})
public class HerpsServlet extends HttpServlet {
    private Service service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setHerpsService(request);
        List<Herp> herps = new ArrayList<>();
        try {
            ResultSet herpsResultSet = service.getMatchingResults(request);
            while(herpsResultSet.next()){
                String classes = herpsResultSet.getString("classes");
                String common_name = herpsResultSet.getString("common_name");
                String genus = herpsResultSet.getString("genus");
                String species = herpsResultSet.getString("species");
                String activity = herpsResultSet.getString("activity");
                Herp herp = new Herp(classes, common_name, genus, species, activity);
                herps.add(herp);
            }
            request.setAttribute("herps", herps);
            request.getRequestDispatcher("herps.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void setHerpsService(ServletRequest request) {
        ServletContext context = request.getServletContext();
        DAO herpDAO = new HerpDAO(context);
        service = new HerpQueryService(herpDAO);
    }
}
