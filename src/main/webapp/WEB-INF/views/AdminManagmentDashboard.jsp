<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<%@include file="HeaderFile.jsp"%>
	<link rel="stylesheet" href="/resources/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="/resources/assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/assets/libs/css/style.css">
    <link rel="stylesheet" href="/resources/assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="/resources/assets/vendor/datatables/css/dataTables.bootstrap4.css">
    <link rel="stylesheet" type="text/css" href="/resources/assets/vendor/datatables/css/buttons.bootstrap4.css">
    <link rel="stylesheet" type="text/css" href="/resources/assets/vendor/datatables/css/select.bootstrap4.css">
    <link rel="stylesheet" type="text/css" href="/resources/assets/vendor/datatables/css/fixedHeader.bootstrap4.css">
<title>Admin Management</title>
<style>
.popup-overlay {
	/*Hides pop-up when there is no "active" class*/
	visibility: hidden;
	position: absolute;
	background: #ffffff;
	border: 3px solid #666666;
	width: 50%;
	height: fit-content;
	left: 25%;
	z-index: +25;
}

.popup-overlay.active {
	/*displays pop-up when "active" class is present*/
	visibility: visible;
	text-align: center;
}

.popup-content {
	/*Hides pop-up content when there is no "active" class */
	visibility: hidden;
}

.popup-content.active {
	/*Shows pop-up content when "active" class is present */
	visibility: visible;
}

.close {
	display: inline-block;
	vertical-align: middle;
	border-radius: 30px;
	margin: .20rem;
	font-size: 1rem;
	color: #666666;
	background: #ffffff;
	border: 1px solid #666666;
}
</style>
</head>
<body>
	<div class="dashboard-main-wrapper">
		<%@include file="AdminNavBar.jsp"%>
		<%@include file="AdminSideBar.jsp" %>
		<div class="dashboard-wrapper">
			<div class="container-fluid dashboard-content ">
				<div class="row"></div>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">Admin Management</h2>
							<p class="pageheader-text">Nulla euismod urna eros, sit amet
								scelerisque torton lectus vel mauris facilisis faucibus at enim
								quis massa lobortis rutrum.</p>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="adminDashboard"
											class="breadcrumb-link">Admin Dashboard</a></li>
										<li class="breadcrumb-item active" aria-current="page">Admin
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
						<button class="btn bg-dark text-white open">Add Admin</button>
					</div>
				</div>
							<div class="popup-overlay">
				<div class="popup-content">
					<i class="fas fa-window-close close" style="float: right"></i><br>
					<br>
					<form id="addAdmin" action="addAdmin" method="post">
						<div class="card">
							<div class="card-header">
								<h3 class="mb-1">Admin Signup Form</h3>
								<p>Please Enter Admin Information</p>
							</div>
							<div class="card-body">
								<div class="form-group">
									<input class="form-control form-control-lg" type="text"
										name="name" id="name" placeholder="Admin Name"
										autocomplete="off" onkeyup="checkSpace(this)" required>

								</div>
								<div class="form-group">
									<input class="form-control form-control-lg" type="email"
										name="emailID" required placeholder="E-mail"
										autocomplete="off">
								</div>
								<div class="form-group">
									<input class="form-control form-control-lg" id="pass1"
										name="password" autocomplete="off" type="password"
										placeholder="Password" required>
								</div>
								<div class="form-group">
									<input class="form-control form-control-lg"
										placeholder="Confirm" autocomplete="off" oninput="check(this)"
										required>
								</div>
								<div class="form-group pt-2">
									<button class="btn btn-block btn-primary" type="submit">Register
										Admin</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
				<br>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Admin Table</h5>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered first">
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
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>



		</div>
	</div>
	<%@include file="AdminFooter.jsp"%>
		<%@include file="SideBarActive.jsp" %>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="/resources/assets/vendor/datatables/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script src="/resources/assets/vendor/datatables/js/buttons.bootstrap4.min.js"></script>
    <script src="/resources/assets/vendor/datatables/js/data-table.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>

</body>
</html>