<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 폼</title>
</head>
<body>
<h1>파일 업로드 폼</h1>
<form action="/sample/exUploadPost" method="post"
enctype="multipart/form-data">
<div><input type="file" name="files"></div>
<div><input type="file" name="files"></div>
<div><input type="file" name="files"></div>
<div><input type="file" name="files"></div>
<div><input type="file" name="files"></div>
<div><button>전송</button></div>
</form>

</body>
</html>