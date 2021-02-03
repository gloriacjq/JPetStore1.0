<h3>Account Information</h3>

<table class="AccountForm">
	<tr>
		<td>First name:</td>
		<td><input type="text" name="firstName" value="${sessionScope.account.firstName}"/></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="lastName" value="${sessionScope.account.lastName}"/></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" size="40" name="email" value="${sessionScope.account.email}"/></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" value="${sessionScope.account.phone}"/></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" size="40" name="address1" value="${sessionScope.account.address1}"/></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" size="40" name="address2" value="${sessionScope.account.address2}"/></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="city" value="${sessionScope.account.city}"/></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="4" name="state" value="${sessionScope.account.state}"/></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" size="10" name="zip" value="${sessionScope.account.state}"/></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" size="15" name="country" value="${sessionScope.account.country}"/></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table class="AccountForm">
	<tr>
		<td>Language Preference:</td>
		<c:set var="option01" value=""/>
		<c:set var="option02" value=""/>
		<c:set var="option03" value=""/>
		<c:if test="${sessionScope.account.favouriteCategoryId eq 'Chinese'}">
			<c:set var="option01" value="selected=\"selected\""/>
		</c:if><c:if test="${sessionScope.account.favouriteCategoryId eq 'English'}">
			<c:set var="option02" value="selected=\"selected\""/>
		</c:if><c:if test="${sessionScope.account.favouriteCategoryId eq 'Japanese'}">
			<c:set var="option03" value="selected=\"selected\""/>
		</c:if>
		<td>
			<select name="languagePreference">
				<option value="Chinese" ${option01}>Chinese</option>
				<option value="English" ${option02}>English</option>
				<option value="Japanese" ${option03}>Japanese</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<c:set var="option1" value=""/>
		<c:set var="option2" value=""/>
		<c:set var="option3" value=""/>
		<c:set var="option4" value=""/>
		<c:set var="option5" value=""/>
		<c:if test="${sessionScope.account.favouriteCategoryId eq 'FISH'}">
			<c:set var="option1" value="selected=\"selected\""/>
		</c:if><c:if test="${sessionScope.account.favouriteCategoryId eq 'DOGS'}">
			<c:set var="option2" value="selected=\"selected\""/>
		</c:if><c:if test="${sessionScope.account.favouriteCategoryId eq 'REPTILES'}">
			<c:set var="option3" value="selected=\"selected\""/>
		</c:if><c:if test="${sessionScope.account.favouriteCategoryId eq 'CATS'}">
			<c:set var="option4" value="selected=\"selected\""/>
		</c:if><c:if test="${sessionScope.account.favouriteCategoryId eq 'BIRDS'}">
			<c:set var="option5" value="selected=\"selected\""/>
		</c:if>
		<td>
			<select name="favouriteCategoryId">
				<option ${option1} value="FISH">FISH</option>
				<option ${option2} value="DOGS">DOGS</option>
				<option ${option3} value="REPTILES">REPTILES</option>
				<option ${option4} value="CATS">CATS</option>
				<option ${option5} value="BIRDS">BIRDS</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<c:set var="ischecked1" value=""/>
		<c:if test="${sessionScope.account.listOption == true}">
			<c:set var="ischecked1" value="checked"/>
		</c:if>
		<td><input type="checkbox" name="listOption" ${ischecked1}/></td>


	</tr>
	<tr>
		<c:set var="ischecked2" value=""/>
		<c:if test="${sessionScope.account.bannerOption == true}">
			<c:set var="ischecked2" value="checked"/>
		</c:if>
		<td>Enable MyBanner</td>
		<td><input type="checkbox" name="bannerOption" ${ischecked2}/></td>
	</tr>

</table>
