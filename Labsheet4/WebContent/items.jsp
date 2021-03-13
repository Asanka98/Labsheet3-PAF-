<%@page import="com.PAF.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%
		//Insert item---------------------------------
		if (request.getParameter("itemCode") != null)
		 {
			
			 Item itemObj = new Item();
			 String stsMsg = itemObj.insertItem(request.getParameter("itemCode"),
			 request.getParameter("itemName"),
			 request.getParameter("itemPrice"),
			 request.getParameter("itemDesc"));
			 session.setAttribute("statusMsg", stsMsg);
			 
		 }
		else{
			
			session.setAttribute("statusMsg", "");
		}

		//Delete item----------------------------------
		if (request.getParameter("itemID") != null)
		{
			
			Item itemObj = new Item();
			String stsMsg = itemObj.deleteItem(request.getParameter("itemID"));
			session.setAttribute("statusMsg", stsMsg);
			
		}
		else{
			
			session.setAttribute("statusMsg", "");
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="VIEWS/css/bootstrap.min.css">
<title>Items Management</title>
</head>
<body>
<div class="container">
	<div class="text-center">
		<h1 style="margin-top:70px;margin-bottom:70px">Items Management</h1>
	</div>
</div>

	<div class="container">
		<form method="post" action="items.jsp">
			<div class="row">
				<div class="col">
				 Item code:
				</div>
				<div class="col">
				 <input name="itemCode" type="text"><br>
				</div>
			</div>
			<div class="row">
				<div class="col">
				 Item name:
				</div>
				<div class="col">
				 <input name="itemName" type="text"><br>
				</div>
			</div>
			<div class="row">
				<div class="col">
				 Item price:
				</div>
				<div class="col">
				 <input name="itemPrice" type="text"><br>
				</div>
			</div>
			<div class="row">
				<div class="col">
				 Item description:
				</div>
				<div class="col">
				 <input name="itemDesc" type="text"><br>
				</div>
			</div>
			<div class="row">
				<div class="col">
			 	 <input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">
			 	</div>
		 	</div>
		</form>
	<div class="row">
		<%
		 out.print(session.getAttribute("statusMsg"));
		%>
	</div>
	</div>
		<br>
		<%
		Item itemObj = new Item();
		out.print(itemObj.readItems());
		%>
		
</body>
</html>