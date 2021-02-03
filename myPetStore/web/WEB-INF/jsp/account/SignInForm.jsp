<%@ include file="../common/IncludeTop.jsp"%>
<script type="text/javascript" src="js/verificationImgRefresh.js"></script>

<div id="Catalog">
	<form action="signIn" method="post" >
		<p>Please enter your username and password.</p>
		<p>
				Username: <input type="text" name="username" value="${requestScope.username}" /> <br /><br />
				Password: <input type="password" name="password" value="${requestScope.password}"/> <br /><br />
				Verification Code: <input type="text" name="verifyCode" size="5"/>
				<img id="verificationImg" src="/verificationCode"/>
			    <a id="verificationTips">can't see clearly?</a> <br /><br />
			<%
				String msg = (String)request.getAttribute("msg");
				if(msg != null){
					out.println("<font color=red>" + request.getAttribute("msg") + "</font>");
					//request.setAttribute("msg", ""); 局部保存，但刷新后仍读取原request值
				}
			%>
			<%
				String message = (String)request.getAttribute("signInFailedMessage");
				if(message != null){
					out.println("<font color=red>" + message + "</font>");
				}
			%>

		</p>
		<input type="submit" value="Sign In"/>
	</form>
	Need a user name and password?
	<a href="newAccountForm">Register Now!</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

