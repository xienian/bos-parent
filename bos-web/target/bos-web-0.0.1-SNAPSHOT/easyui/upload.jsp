<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <h2>File upload demo</h2>
    <form action="${pageContext.request.contextPath }/fileload"  method="post" enctype="multipart/form-data">
        <input type="file" name="filename" size="45"><br>
        <input type="submit" name="submit" value="submit">
    </form>
</body>
</html>