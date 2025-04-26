package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.dbService.DbService;
import org.example.model.Result;

import java.io.IOException;

@WebServlet("/country")
public class Country extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("Country.jsp");
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        DbService dbService = new DbService();
        Result result = dbService.addCountry(name);
        resp.sendRedirect("Country.jsp");
        req.getServletContext().setAttribute("message", result.getMessage());
        req.getServletContext().setAttribute("success", String.valueOf(result.isSuccess()));
    }

}