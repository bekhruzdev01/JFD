<%@ page import="java.io.PrintWriter" %>
<%@ page import="org.example.dbService.DbService" %>
<%@ page import="org.example.model.Country" %>
<%@ page import="java.sql.SQLException" %>
<jsp:include page="Header.jsp"/>

<%
  DbService dbService = new DbService();
  StringBuilder str = new StringBuilder();
    try {
        for (Country country : dbService.countryList()) {
          str.append(
                  "<tr>" +
                          "<td>" + country.getId() + "</td>" +
                          "<td>" + country.getName() + "</td>" +
                          "<td> " + "<button class=\"btn btn-warning\" onclick=\"editCountry("+country.getId()+", "+country.getName()+"\">Edit</button>" +"</td>"+
                          "<td> " + "<button class=\"btn btn-danger\" onclick=\"deleteCountry("+country.getId()+")\">Delete</button>" +"</td>"+
                          "</tr>"
          );
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
//    pageContext.setAttribute("body", str);
%>


<div class="toast align-items-center border-0" role="alert" aria-live="assertive" aria-atomic="true"
     style="position: absolute; bottom: 2rem; right: 2rem;
             display: <%=request.getServletContext().getAttribute("success")!=null?"block":"none"%>;
             color:white;
             background: <%=request.getServletContext().getAttribute("success")=="true"?"blue":"red"%>;">
  <div class="d-flex">
    <div class="toast-body">
      <%=request.getServletContext().getAttribute("message")%>
    </div>
  </div>
</div>

<h1 class="text-center text-primary">Davlatlar sahifasi</h1>

<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  <i class="bi bi-plus-circle"></i>
</button>

<table class="table">
  <thead>
  <tr>
    <th>T/r</th>
    <th>Nomi</th>
  </tr>
  </thead>
  <tbody>
  <%=str%>
  </tbody>
</table>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="/country" method="post" class="w-100">
          <label for="name" class="m-2 text-primary">
            Nomi
            <input type="text" placeholder="Davlat nomini kiriting" name="name" id="name"
                   class="form-control">
          </label>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Yopish</button>
            <button type="submit" class="btn btn-primary">Saqlash</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<jsp:include page="Footer.jsp"/>