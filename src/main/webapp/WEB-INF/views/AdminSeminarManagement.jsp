<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="HeaderFile.jsp"%>
<title>Seminar Management</title>
<style>

</style>
</head>
<body>
	<div class="dashboard-main-wrapper">
		<%@include file="AdminNavBar.jsp"%>
		<%@include file="AdminSideBar.jsp"%>
		<div class="dashboard-wrapper">
			<div class="container-fluid dashboard-content ">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">Seminar Management</h2>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="adminDashboard"
											class="breadcrumb-link">Admin Dashboard</a></li>
										<li class="breadcrumb-item active" aria-current="page">Seminar
											Management</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-6">
						<div id="alert" class="alert alert-primary">
							<span id="msg">${msg }</span>
						</div>
					</div>
					<div class="col-6 d-flex justify-content-end">
						<a href="addSeminar" class="btn bg-dark text-white open">Add Seminar</a>
					</div>
				</div>
				
				<br>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Seminar Table</h5>
							<div class="card-body">
								<div class="table-responsive">
<%-- 									<table class="table table-striped table-bordered first">
										<thead>
											<tr>
												<th>Admin ID</th>
												<th>Admin Name</th>
												<th>Email ID</th>
												<th>Create At</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${admins}" var="a">
												<tr>
													<th>${a.adminID }</th>
													<th>${a.name }</th>
													<th>${a.emailID}</th>
													<th>${a.createdAt }</th>
													<th><a href="deleteAdmin/${a.adminID}" class="btn bg-dark text-white">Delete Admin</a>
												</tr>

											</c:forEach>

										</tbody>
									</table> --%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="AdminFooter.jsp"%>
		</div>
	</div>
	<%@include file="SideBarActive.jsp"%>

	<script
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script
		src="/resources/assets/vendor/datatables/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
	<script
		src="/resources/assets/vendor/datatables/js/buttons.bootstrap4.min.js"></script>
	<script src="/resources/assets/vendor/datatables/js/data-table.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>

</body>
</html>