<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="viewCart">Return to Shopping Cart</a>
</div>

<div id="Catalog">

<form action="newOrderForm" method="post">
	<table>
		<h2>Checkout Order</h2>

		<table>

			<tr>
				<th><b>Item ID</b></th>
				<th><b>Product ID</b></th>
				<th><b>Description</b></th>
				<th><b>In Stock?</b></th>
				<th><b>Quantity</b></th>
				<th><b>List Price</b></th>
				<th><b>Total Cost</b></th>
			</tr>

			<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
				<tr>
					<td>
						<a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
					</td>
					<td>${cartItem.item.product.productId}</td>
					<td>
						${cartItem.item.attribute1} ${cartItem.item.attribute2}
						${cartItem.item.attribute3} ${cartItem.item.attribute4}
						${cartItem.item.attribute5} ${cartItem.item.product.name}
					</td>
					<td>${cartItem.inStock}</td>
					<td>${cartItem.quantity}</td>
					<td>
						<fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
					</td>
					<td>
						<fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7">
					Sub Total: <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
				</td>
			</tr>
		</table>
		<br/>
		<td>
			<input type="submit" value="Submit Order"/>
		</td>
	</table>
</form>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>