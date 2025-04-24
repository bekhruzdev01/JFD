package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.dbService.DbService;
import org.example.model.Book;

import java.io.IOException;
import java.io.PrintWriter;

public class Main extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(ma);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbService dbService = new DbService();

        if (req.getParameter("editId") != null && !req.getParameter("editId").isEmpty()) {
            int editId = Integer.parseInt(req.getParameter("editId"));
            String editName = req.getParameter("editName");
            double editPrice = Double.parseDouble(req.getParameter("editPrice"));
            String editWriter = req.getParameter("editWriter");
            int editYear = Integer.parseInt(req.getParameter("editYear"));

            dbService.editBook(Book.builder()
                    .id(editId)
                    .name(editName)
                    .price(editPrice)
                    .writer(editWriter)
                    .year(editYear)
                    .build());
        } else {
            // Aks holda, yangi kitob qo‘shilsin
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String writer = req.getParameter("writer");
            int year = Integer.parseInt(req.getParameter("year"));

            dbService.addBook(Book.builder()
                    .name(name)
                    .price(price)
                    .writer(writer)
                    .year(year)
                    .build());
        }

        resp.sendRedirect("/main");
    }

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        DbService dbService = new DbService();

        String pathInfo = req.getPathInfo();
        System.out.println(pathInfo);
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID kerak");
            return;
        }

        try {
            int id = Integer.parseInt(pathInfo.substring(1));

            boolean deleted = dbService.deleteBook(id).isSuccess();

            if (deleted) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);// 204
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Kitob topilmadi");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID noto‘g‘ri");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}

