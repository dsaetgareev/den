<%@ page import="ru.dinis.library.beans.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.dinis.library.enums.SearchType" %><%--
  Created by IntelliJ IDEA.
  User: dinis
  Date: 05.11.17
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/leftMenu.jspf" %>
<jsp:useBean id="bookList" class="ru.dinis.library.service.BookList" scope="page"/>
<%@include file="../WEB-INF/jspf/letters.jspf"%>


<div class="book_list">

<% request.setCharacterEncoding("utf-8");

    ArrayList<Book> list = null;

    if(request.getParameter("genre_id") != null) {
        Long genreId = Long.valueOf(request.getParameter("genre_id"));
        list = bookList.getBooksByGenre(genreId);
    }
    if (request.getParameter("letter") != null) {
        String letter = request.getParameter("letter");
        list = bookList.getBooksByLetter(letter);
    }
    if (request.getParameter("search_string") != null) {
        String search = request.getParameter("search_string");
        SearchType type = SearchType.TITLE;
        if (request.getParameter("search_option").equals("Автор")) {
            type = SearchType.AUTHOR;
        }

        if (search != null && !search.trim().equals("")) {
            list = bookList.getBooksBySearch(search, type);
        }

    }
%>


    <h5 style="text-align: left; margin-top: 20px">Найдено книг: <%=list.size()%></h5>

    <%
        session.setAttribute("currentBookList", list);
        for (Book book : list) {
    %>
    <div class="book_info">
        <div class="book_title">
            <p><%=book.getName()%>
            </p>
        </div>
        <div class="book_img">
            <a href="#"><img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(book)%>" height="250"
                             width="190" alt="Обложка"/></a>
        </div>
        <div class="book_details">
            <p style="color:#378de5 ;font-weight: bold; font-size: 15px;"><%=book.getName()%>
            </p><br>
            <strong>ISBN: </strong><%=book.getIsbn()%>
            <br/><strong>Издательство: </strong><%=book.getPublisher()%>
            <br><strong>Количество страниц: </strong><%=book.getPageCount()%>
            <br><strong>Год издания: </strong><%=book.getPublishDate()%>
            <br><strong>Автор: </strong><%=book.getAuthor()%>
            <br>
            <p style="margin: 10px"><a href="<%=request.getContextPath()%>/PdfContent?index=<%=list.indexOf(book)%>&session_id=<%=request.getSession().getId()%>">Читать</a></p>
        </div>
    </div>
    <%}%>

</div>