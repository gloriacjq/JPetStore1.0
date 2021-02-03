<%@ include file="../common/IncludeTop.jsp"%>

<style>
	.okTips{
		color: green;
	}

	.errorTips{
		color: red;
	}
</style>

<div id="Catalog">
	<form action="newAccount" method="post">
		<h3>User Information</h3>

		<table class="AccountForm">
			<tr>
				<td>User ID:</td>
				<td>
					<input type="text" name="username" id="username" required/>
					<span class="star">*</span>
					<span id="usernameTips"></span>
					<script type="text/javascript" src="js/checkUsername.js"></script>
				</td>

			</tr>
			<tr>
				<td>New password:</td>
				<td>
					<input type="password" id="signUpPwd" name="password" required/>
					<span class="star">*</span>
				</td>
			</tr>

			<tr>
				<td>Repeat password:</td>
				<td>
					<input type="password" id="rptSignUpPwd" name="repeatedPassword" required/>
					<span class="star">*</span>
					<span id="passwordTips"></span>
					<script type="text/javascript" src="js/checkPassword.js"></script>
				</td>
			</tr>

<%--			<c:if test="${sessionScope.pwdMessage != null}">--%>
<%--			<tr>--%>
<%--				<td colspan="2">--%>
<%--						<h4 color="red">${sessionScope.pwdMessage}</h4>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			</c:if>--%>

		</table>

		<%@ include file="IncludeAccountFields.jsp"%>


		<input type="submit" name="newAccount" value="Sign Up" />

	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>