<%@include file="header.jsp"%>

<div class="mx-auto emp">
	<form method="post" action="save" modelAttribute="employee">
		<fieldset class="border p-4 m-4">
			<legend class="w-auto">Employee</legend>

			<div class="form-group">
				<label for="exampleInputEmail1">Employee Code</label> <input
					class="form-control" name="employeeCode" type="number"
					readonly="readonly" value="${employee.employeeCode}" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Employee Name</label> <input
					class="form-control" name="employeeName"
					value="${employee.employeeName}" type="text" required />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Email</label> <input
					class="form-control" name="email" value="${employee.email}"
					type="email" required="required" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Location</label> <input
					class="form-control" name="location" type="text"
					value="${employee.location}" required />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Date of Birth</label> <input
					class="form-control" name="dateOfBirth"
					value="${employee.dateOfBirth}" type="date" />
			</div>
			<button type="submit" class="btn btn-primary m-1">Save</button>




		</fieldset>
	</form>


</div>



</body>
</html>