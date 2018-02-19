<%--
  Created by IntelliJ IDEA.
  User: dinis
  Date: 08.11.17
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<div class="pdf_viewer">
    <applet
            code="EmbedPDF.class"
            archive="<%=request.getServletContext().getContextPath()%>/jars/EmbedPDF.jar"
            width="850"
            height="900"

    >

        <param name="pdf"
               value="<%=request.getContextPath()%>/PdfContent?index=<%=request.getParameter("index")%>&session_id=<%=request.getSession().getId()%>"/>

        <param name="enableOpenWindow" value="true"/>
        <param name="enableSubpixelAA" value="true"/>
        <param name="enablePrinting" value="true"/>

        <!-- Parameters for Sun's Java plug-in: -->
        <param name="codebase_lookup" value="false">
        <param name="classloader_cache" value="false">
        <param name="java_arguments" value="-Djnlp.packEnabled=true -Xmx128m"/>
        <param name="image" value="<%=request.getServletContext().getContextPath()%>/image/splash.gif"/>
        <param name="boxborder" value="false"/>
        <param name="centerimage" value="true"/>
        <param name="boxbgcolor" value="white">
        <param name="boxfgcolor" value="black">
        <param name="boxmessage" value="Loading EmbedPDF...">

        <!-- Provide a link to the PDF-document for browsers without Java support: -->


    </applet>
</div>
</body>
</html>
