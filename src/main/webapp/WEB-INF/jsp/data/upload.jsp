<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"/>
<title>Image Gallery Uploader</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script>
$(function(){
$('#clearButton').click(function(){
     $(':input','#form')
.not(':button, :submit, :reset, :hidden')
.val('')
.removeAttr('checked')
.removeAttr('selected');
$("p").text("");
$('.error-text').text("");
$('.info-text').text("");
$('#error-box').hide();
$('.img-holder').hide();

});
});
</script>
</head>
<body> 
<form:form method="post" modelAttribute="formImage" id="form" enctype="multipart/form-data" >

 <div class="main-holder">

<form:errors path="*">
  <div id="error-box">
   <strong>Error Message(s):</strong><br>
  <form:errors path="*" class="error-text" />
  </div>
</form:errors>

 
  <label for="imageBinary" class="block-label">Image File</label>
  <input type="file" name="imageBinary" id="imageBinanry" tabindex="1">
  <br><br>
  <form:checkbox path="altCaptionEqualToFilename" tabindex="2" />
  <label for="altCaptionEqualToFilename">Default filename as alt_tag/caption</label>
    
  <div class="float-right">
    <label for="caption" class="block-label">caption</label>
    <form:input type="text" path="caption" id="caption" size="35" maxlength="200" tabindex="3" />
    
    <label for="alt" class="block-label">alt tag</label>
    <form:input type="text" path="alt" id="alt" size="35" maxlength="200" tabindex="4" />
  </div>
  <div class="clear-float">&nbsp;</div>
 </div>
 
 <div class="sub-holder">
   <div class="float-left"><button type="button" id="clearButton" >Add Another Image</button></div>
   <div class="sub-holder-upload"><input type="submit" name="upload" id="upload" tabindex="5" value="Upload" ></div>
   <div class="clear-float">&nbsp;</div>
 </div> 
 
 <c:if test="${not empty success}">
 <span class="info-text">
 Click <a href="${pageContext.request.contextPath}/image/lookup/${formImage.id}">&raquo;image lookup</a> to view  metadata XML for this  image.</a>
<br><br>
 <span>
 
 <span class="info-text">Sucessfully added image: ${formImage.filename}, alt=${formImage.alt}, caption=${formImage.caption}</span>
 <div class="img-holder">
 <img src="${pageContext.request.contextPath}/${imgPath}/${formImage.filename}" alt="${formImage.alt}" ><br>
 ${formImage.caption}
 </div>    
 
 </c:if>

  
</form:form>
      
</body>
</html>