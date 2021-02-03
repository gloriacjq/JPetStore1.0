<%@ include file="../common/IncludeTop.jsp" %>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">
	<br/>
	<h2 style="text-align: center">${sessionScope.category.name}</h2>

	<table>
		<tr>
			<th></th>
			<th>Product ID</th>
			<th>Name</th>
		</tr>
		<c:forEach var="product" items="${sessionScope.productList}">
			<tr>
				<td>${product.description}</td>
				<td>
					<a href="viewProduct?productId=${product.productId}">${product.productId}</a>
				</td>
				<td>${product.name}</td>
			</tr>
		</c:forEach>
	</table>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>


