<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"/>
<title>Image Gallery Uploader | Error </title>
<link href="../css/common.css" rel="stylesheet">
<script src="../js/jquery-1.11.1.min.js"></script>
</head>
<body> 

 <div class="main-holder">

  <strong>Oooppsss....</strong><br><br>
  
  Unexpected error occured, please contact system admistrator proving the following details:<br><br>
    
  URL:${url} <br><br>
  Exception:  ${exception.message}
  <c:forEach items="${exception.stackTrace}" var="ste">    ${ste} <br><br>
</c:forEach>
      
</body>
