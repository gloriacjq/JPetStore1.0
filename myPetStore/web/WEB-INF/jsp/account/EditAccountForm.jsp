<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog"><form action="editAccount" method="post">

	<h3>User Information</h3>

	<table class="AccountForm">
		<tr>
			<td>User ID:</td>
			<td>${sessionScope.account.username}</td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repeatedPassword" /></td>
		</tr>

		<c:if test="${sessionScope.pwdMessage != null}">
		<tr>
			<td colspan="2">
				<h4><font color=red>${sessionScope.pwdMessage}</font></h4>
			</td>
		</tr>
		</c:if>

	</table>
	<%@ include file="IncludeAccountFields.jsp"%>
	<br/>
	<input type="submit" name="editAccount" value="Save Account Information" />

</form>
	<a href="viewListOrders?username=${sessionScope.account.username}">My Orders</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
