<%@ page import="org.example.dbService.DbService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Book" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="/Header.jsp" flush="true"/>
<div class="container">
    <h1 class="text-center text-success">Kitoblar sahifasi</h1>
    <button class="btn btn-success mt-3 mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal">Saqlash</button>
    <form method="get" action="/main">    <input type="text" class="form-control mb-3" placeholder="Qidirish..." onkeyup="searchBooks(this.value)" name="search"></form>    <table class="table">
    <thead>
    <tr>
        <td>T/r</td>
        <td>Nomi</td>
        <td>Narxi</td>
        <td>Yozuvchisi</td>
        <td>Yili</td>
        <td colspan="2">Sozlamalar</td>
    </tr>
    </thead>
    <tbody>
    <% DbService dbService = new DbService();
        List<Book> books = new ArrayList<>();
        int tr = 0;
        String data = "";
        for (Book book : books) {
            data =
                    "<tr>\n" +
                            "        <td>" +tr+ "</td>\n" +
                            "        <td>"+book.getName()+"</td>\n" +
                            "        <td>"+book.getPrice()+"</td>\n" +
                            "        <td>"+book.getWriter()+"</td>\n" +
                            "        <td>"+book.getYear()+"</td>\n" +
                            "        <td>\n" +
                            "            <button class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#editExampleModal\"                  onclick=\"fillEditForm(9, 'mazgi', 20000.0, 'behruz', 2020)\">Taxrirlash</button>            </td>\n" +
                            "        <td>\n" +
                            "            <button class=\"btn btn-danger\" onclick=\"deleteBook(9)\">O'chirish</button>\n" +
                            "        </td>\n" +
                            "    </tr>";
            tr++;
        }

    %>
    <%=data%>
    </tbody>
</table>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">Kitob saqlash</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/main" method="post">
                    <input type="text" class="form-control mb-2" placeholder="Kitob nomini kiriting" id="name" name="name"/>
                    <input type="number" class="form-control mb-2" placeholder="Kitob narxini kiriting" id="price" name="price"/>
                    <input type="text" class="form-control mb-2" placeholder="Kitob yozuvchisini kiriting" id="writer" name="writer"/>
                    <input type="number" class="form-control mb-2" placeholder="Kitob chiqqan yilni kiriting" id="year" name="year"/>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Yopish</button>
                        <button type="submit" class="btn btn-primary">Saqlash</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div><div class="modal fade" id="editExampleModal" tabindex="-1" aria-labelledby="editExampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Kitob saqlash</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/main" method="post">
                    <input type="number" class="form-control mb-2" placeholder="Kitob Id kiriting" id="editId" name="editId"/>
                    <input type="text" class="form-control mb-2" placeholder="Kitob nomini kiriting" id="editName" name="editName"/>
                    <input type="number" class="form-control mb-2" placeholder="Kitob narxini kiriting" id="editPrice" name="editPrice"/>
                    <input type="text" class="form-control mb-2" placeholder="Kitob yozuvchisini kiriting" id="editWriter" name="editWriter"/>
                    <input type="number" class="form-control mb-2" placeholder="Kitob chiqqan yilni kiriting" id="editYear" name="editYear"/>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Yopish</button>
                        <button type="submit" class="btn btn-primary">Saqlash</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div><script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
<script >function fillEditForm(id, name, price, writer, year) {
    document.getElementsByName("editId")[0].value = id;
    document.getElementsByName("editName")[0].value = name;
    document.getElementsByName("editPrice")[0].value = price;
    document.getElementsByName("editWriter")[0].value = writer;
    document.getElementsByName("editYear")[0].value = year;
    console.log(document.getElementById("editId").value);
}function deleteBook(id){
    fetch(`http://localhost:8080/${id}`,{
        method:'delete'
    }).then(response =>{
        if (response.ok) {
            alert("Muvaffaqiyatli o'chirildi")
        }else{
            alert("Error")
        }
    }).catch(error => {
        console.error('Serverga ulanishda xatolik:', error);
    });
}function searchBooks(value) {
    <%for (Book book : books) {
        
    }%>
}
</script>
<jsp:include page="/Footer.jsp" flush="true"/>