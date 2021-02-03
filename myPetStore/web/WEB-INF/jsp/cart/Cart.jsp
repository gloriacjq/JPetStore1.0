<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="/main">Return to Main Menu</a>
</div>

<script type="text/javascript" src="js/updateCart.js"></script>

<div id="Catalog">

<div id="Cart">

<h2>Shopping Cart</h2>
	<form id="cartForm" action="updateCartQuantities" method="post">
		<table>
			<c:if test="${sessionScope.cart.numberOfItems > 0}">
			<tr>
				<th><b>Item ID</b></th>
				<th><b>Product ID</b></th>
				<th><b>Description</b></th>
				<th><b>In Stock?</b></th>
				<th colspan="3"><b>Quantity</b></th>
				<th><b>List Price</b></th>
				<th><b>Total Cost</b></th>
				<th>&nbsp;</th>
			</tr>
			</c:if>

			<c:if test="${sessionScope.cart.numberOfItems == 0}">
					 <br/><span><b>Your cart is empty.</b></span>

			</c:if>

			<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
				<tr>
					<td>
						<a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
					</td>
					<td>
						${cartItem.item.product.productId}
					</td>
					<td>
						${cartItem.item.attribute1} ${cartItem.item.attribute2}
						${cartItem.item.attribute3} ${cartItem.item.attribute4}
						${cartItem.item.attribute5} ${cartItem.item.product.name}
					</td>
					<td>
						${cartItem.inStock}
					</td>
					<td>
						<input class="min" type="button" value="-" />
					</td><td>
						<input class="cartItemQuantity" type="text" name="${cartItem.item.itemId}" value="${cartItem.quantity}" size="6px"/>
					</td><td>
					<input class="add" type="button" value="+" />
					</td>
					<td>
						<fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
					</td>
					<td>
						<fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />
					</td>
					<td>
						<a class="Button" href="removeItemFromCart?workingItemId=${cartItem.item.itemId}">Remove</a>
					</td>
				</tr>
			</c:forEach>

			<c:if test="${sessionScope.cart.numberOfItems > 0}">
			<tr>
				<td colspan="9">Sub Total: <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
<%--					<input type="submit" value="Update Cart"/>--%>
				</td>
				<td>&nbsp;</td>
			</tr>
			</c:if>
		</table>
	</form>

	<c:if test="${sessionScope.cart.numberOfItems > 0}">
      	<a class="Button" href="checkOut">Proceed to Checkout</a>
	</c:if>
</div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>