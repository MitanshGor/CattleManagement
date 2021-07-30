<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<%@include file="HeaderFile.jsp"%>
<title>Template Management</title>
</head>
<body>
	<div class="dashboard-main-wrapper">
		<%@include file="AdminNavBar.jsp"%>
		<%@include file="AdminSideBar.jsp" %>
		<div class="dashboard-wrapper">
			<div class="container-fluid dashboard-content ">
			 <div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			 		<div class="page-header">       
						<h2 class="pageheader-title">Message Templates</h2>
						  <p class="pageheader-text">Nulla euismod urna eros, sit amet scelerisque torton lectus vel mauris facilisis faucibus at enim quis massa lobortis rutrum.</p>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="adminDashboard" class="breadcrumb-link">Admin Dashboard</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">Message Template</li>
                                        </ol>
                                    </nav>
                           		</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="AdminFooter.jsp"%>
		</div>
	</div>
</body>
</html>