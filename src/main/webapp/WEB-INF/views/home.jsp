<%@include file="header.jsp"%>

<fieldset class="border m-4 p-4">
	<legend class="w-auto">Employee Listings</legend>
	<div class="row">
		<div class="col-md-7 col-sm-2"></div>
		<a href="${pageContext.request.contextPath}/save"><button
				class="btn btn-primary m-1">Add New Employee Details</button></a> <a
			href="${pageContext.request.contextPath}/download"><button
				class="btn btn-primary m-1">Download Employee Details</button></a>

	</div>
	<table class="table table-bordered m-4 mx-auto">
		<tr>
			<th>Employee Code</th>
			<th>Employee Name</th>
			<th>Email</th>
			<th>Date Of Birth</th>
			<th>Location</th>
			<th>Action</th>
		</tr>


		<c:forEach items="${allEmployee}" var="employee">

			<tr>
				<td>${employee.employeeCode }</td>
				<td>${employee.employeeName }</td>
				<td>${employee.email }</td>
				<td>${employee.dateOfBirth }</td>
				<td>${employee.location }</td>
				<td>
					<form action="update" method="post">
						<input type="hidden" name="employeeCode"
							value="${employee.employeeCode }">
						<button type="submit" class="btn btn-warning m-1">Update</button>
					</form>

					<form action="delete" method="post">
						<input type="hidden" name="employeeCode"
							value="${employee.employeeCode }">
						<button type="submit" class="btn btn-danger m-1">Delete</button>
					</form>
				</td>


			</tr>

		</c:forEach>

	</table>
</fieldset>
</body>
</html>
