package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Cart;

public interface CartDAO {
    Cart getCartByUsername(String username);
    void insertCart(Cart cart);
    //void updateCart(Cart cart);
}
