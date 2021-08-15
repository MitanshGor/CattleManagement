<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="HeaderFile.jsp"%>

<link href='/resources/assets/vendor/full-calendar/css/fullcalendar.css'
	rel='stylesheet' />
<link
	href='/resources/assets/vendor/full-calendar/css/fullcalendar.print.css'
	rel='stylesheet' media='print' />
<title>Personal Counselling</title>
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
							<h2 class="pageheader-title">Personal Counselling</h2>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="adminDashboard"
											class="breadcrumb-link">Admin Dashboard</a></li>
										<li class="breadcrumb-item active" aria-current="page">Personal
											Counselling</li>
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
						<a href="bookedSlots" class="btn bg-dark text-white open">View
							All Booked Slots</a>&nbsp;
						<a href="viewBookingSlotRequest" class="btn bg-dark text-white open">View
							Booking Slots Request</a>
								
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<div class="card-body">
								<div id='calendar1'></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="modal" tabindex="-1" id="modalDemo" role="dialog">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modal-title"></h5>
										<button type="button" class="close" id="closeButton"
											data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<div class="displayData" id="displayData">
											<p class="col-form-label text-sm-left" id="counsellingTitle"></p>
											<p class="col-form-label text-sm-left" id="startTimePrint"></p>
											<p class="col-form-label text-sm-left" id="endTimePrint"></p>
											<p class="col-form-label text-sm-left" id="modeDisplay"></p>
											<p class="col-form-label text-sm-left" id="consuellingLink"></p>
											<p class="text-danger col-form-label text-sm-left"
												id="bookingStatus"></p>
										</div>

										<form method="post" action="addTimeSlot" id="timeSlotForm">
											<div class="form-group" id="sTime">
												<label for="start" class="col-form-label">Starting
													Time</label> <input id="start" name="startTime"
													type="datetime-local" class="form-control" required>
											</div>
											<div class="form-group" id="eTime">
												<label for="end" class="col-form-label">Ending
													Time</label> <input id="end" name="endTime"
													type="datetime-local" class="form-control" required>
											</div>
											<div class="form-group" id="modeSelection">
												<label class="col-form-label text-sm-right">Counselling
													Type : </label> <label
													class="custom-control custom-radio custom-control-inline">
													<input name="counsellingType" value="Online" type="radio"
													class="custom-control-input"><span
													class="custom-control-label">Online</span>
												</label> <label 
													class="custom-control custom-radio custom-control-inline">
													<input type="radio" name="counsellingType" value="Offline"
													class="custom-control-input"><span
													class="custom-control-label">Offline</span>
												</label>
											</div>
												<input type="submit" id="submitButton" class="btn btn-primary"
											value="Save Changes" />
										</form>
									</div>
									<div class="modal-footer">
									

										<a id="cancelAppointment" class="btn btn-primary">Cancel
											Appointment</a> <a href="#" id="viewUser" class="btn btn-primary">View
											User</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<%@include file="AdminFooter.jsp"%>
		</div>
	</div>

	<script src='/resources/assets/vendor/full-calendar/js/moment.min.js'></script>
	<script src='/resources/assets/vendor/full-calendar/js/fullcalendar.js'></script>
	<script
		src='/resources/assets/vendor/full-calendar/js/jquery-ui.min.js'></script>
	<script src='/resources/assets/vendor/full-calendar/js/calendar.js'></script>
</body>
</html>