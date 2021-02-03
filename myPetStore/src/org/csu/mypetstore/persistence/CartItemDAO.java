package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;

import java.util.List;

public interface CartItemDAO {
    List<CartItem> getCartItemsByCartId(int cartId);
    void insertCartItem(CartItem cartItem);
    void deleteCartItem(int cartId, String itemId);
    void updateCartItemQuantity(int cartId, String itemId, int quantity);
    void clearCartItemsByCartId(Cart cart);
}

