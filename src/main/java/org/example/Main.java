package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.dbService.DbService;
import org.example.model.Book;
import org.example.model.Result;

import java.io.IOException;
import java.io.PrintWriter;

public class Main extends HttpServlet {
    @SneakyThrows
    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PrintWriter writer = resp.getWriter();
            DbService dbService = new DbService();
            StringBuilder str = new StringBuilder();
            int tr = 1;
            for (Book book : dbService.getBooks()) {
                str.append(
                        "        <tr>\n" +
                                "            <td>" + tr + "</td>\n" +
                                "            <td>" + book.getName() + "</td>\n" +
                                "            <td>" + book.getPrice() + "so'm</td>\n" +
                                "            <td>" + book.getWriter() + "</td>\n" +
                                "            <td>" + book.getYear() + "-yil</td>\n" +
                                "            <td>\n" +
                                "                <button class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#editExampleModal\">Taxrirlash</button>\n" +
                                "            </td>\n" +
                                "            <td>\n" +
                                "                <button class=\"btn btn-danger\" onclick=\"deleteBook(" + book.getId() + ")\">O'chirish</button>\n" +
                                "            </td>\n" +
                                "        </tr>\n"
                );
                tr++;
            }

            writer.write(
                    "<!doctype html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\"\n" +
                            "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                            "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                            "    <title>Book</title>\n" +
                            "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css\">\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<div class=\"container\">\n" +
                            "    <h1 class=\"text-center text-success\">Kitoblar sahifasi</h1>\n" +
                            "    <button class=\"btn btn-success mt-3 mb-3\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">Saqlash</button>\n" +
                            "    <table class=\"table\">\n" +
                            "        <thead>\n" +
                            "        <tr>\n" +
                            "            <td>T/r</td>\n" +
                            "            <td>Nomi</td>\n" +
                            "            <td>Narxi</td>\n" +
                            "            <td>Yozuvchisi</td>\n" +
                            "            <td>Yili</td>\n" +
                            "            <td colspan=\"2\">Sozlamalar</td>\n" +
                            "        </tr>\n" +
                            "        </thead>\n" +
                            "        <tbody>\n" +
                            str +
                            "        </tbody>\n" +
                            "    </table>\n" +
                            "</div>\n" +
                            "<div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n" +
                            "    <div class=\"modal-dialog\">\n" +
                            "        <div class=\"modal-content\">\n" +
                            "            <div class=\"modal-header\">\n" +
                            "                <h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\">Kitob saqlash</h1>\n" +
                            "                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                            "            </div>\n" +
                            "            <div class=\"modal-body\">\n" +
                            "                <form action=\"/main\" method=\"post\">\n" +
                            "                    <input type=\"text\" class=\"form-control mb-2\" placeholder=\"Kitob nomini kiriting\" id=\"name\" name=\"name\"/>\n" +
                            "                    <input type=\"number\" class=\"form-control mb-2\" placeholder=\"Kitob narxini kiriting\" id=\"price\" name=\"price\"/>\n" +
                            "                    <input type=\"text\" class=\"form-control mb-2\" placeholder=\"Kitob yozuvchisini kiriting\" id=\"writer\" name=\"writer\"/>\n" +
                            "                    <input type=\"number\" class=\"form-control mb-2\" placeholder=\"Kitob chiqqan yilni kiriting\" id=\"year\" name=\"year\"/>\n" +
                            "                    <div class=\"modal-footer\">\n" +
                            "                        <button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Yopish</button>\n" +
                            "                        <button type=\"submit\" class=\"btn btn-primary\">Saqlash</button>\n" +
                            "                    </div>\n" +
                            "                </form>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</div>" +
                            "<div class=\"modal fade\" id=\"editExampleModal\" tabindex=\"-1\" aria-labelledby=\"editExampleModalLabel\" aria-hidden=\"true\">\n" +
                            "    <div class=\"modal-dialog\">\n" +
                            "        <div class=\"modal-content\">\n" +
                            "            <div class=\"modal-header\">\n" +
                            "                <h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\">Kitob saqlash</h1>\n" +
                            "                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                            "            </div>\n" +
                            "            <div class=\"modal-body\">\n" +
                            "                <form action=\"/main\" method=\"post\">\n" +
                            "                    <input type=\"number\" class=\"form-control mb-2\" placeholder=\"Kitob Id kiriting\" id=\"editName\" name=\"editId\"/>\n" +
                            "                    <input type=\"text\" class=\"form-control mb-2\" placeholder=\"Kitob nomini kiriting\" id=\"editName\" name=\"editName\"/>\n" +
                            "                    <input type=\"number\" class=\"form-control mb-2\" placeholder=\"Kitob narxini kiriting\" id=\"editPrice\" name=\"editPrice\"/>\n" +
                            "                    <input type=\"text\" class=\"form-control mb-2\" placeholder=\"Kitob yozuvchisini kiriting\" id=\"editWriter\" name=\"editWriter\"/>\n" +
                            "                    <input type=\"number\" class=\"form-control mb-2\" placeholder=\"Kitob chiqqan yilni kiriting\" id=\"editYear\" name=\"editYear\"/>\n" +
                            "                    <div class=\"modal-footer\">\n" +
                            "                        <button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Yopish</button>\n" +
                            "                        <button type=\"submit\" class=\"btn btn-primary\">Saqlash</button>\n" +
                            "                    </div>\n" +
                            "                </form>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</div>" +
                            "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                            "<script >" +
                            "function deleteBook(id){\n" +
                            "    fetch(`http://localhost:8080/main/${id}`,{\n" +
                            "        method:'delete'\n" +
                            "    }).then(response =>{\n" +
                            "        if (response.ok) {\n" +
                            "            alert(\"Muvaffaqiyatli o'chirildi\")\n" +
                            "        }else{\n" +
                            "            alert(\"Error\")\n" +
                            "        }\n" +
                            "    }).catch(error => {\n" +
                            "        console.error('Serverga ulanishda xatolik:', error);\n" +
                            "    });\n" +
                            "}" +
                            "</script>\n" +
                            "</body>\n" +
                            "</html>"
            );
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

        resp.sendRedirect("/");
    }

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
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
}
