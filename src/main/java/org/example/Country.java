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

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
            resp.getWriter().write("ID parameter is missing or empty.");
            return;
        }

            Integer id = Integer.valueOf(idParam);

            DbService dbService = new DbService();
            boolean isDeleted = dbService.deleteCountry(id).isSuccess();

            if (isDeleted) {
                resp.setStatus(HttpServletResponse.SC_OK); // 200 OK
                resp.getWriter().write("Country deleted successfully!");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
                resp.getWriter().write("Failed to delete country.");
            }
    }
}
