package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.CartItemDAO;
import org.csu.mypetstore.persistence.impl.CartDAOImpl;
import org.csu.mypetstore.persistence.impl.CartItemDAOImpl;

import java.util.List;

public class CartService {
    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;

    public CartService(){
        cartDAO = new CartDAOImpl();
        cartItemDAO = new CartItemDAOImpl();
    }

    public Cart getCart(String username){
        Cart cart = cartDAO.getCartByUsername(username);
        CatalogService catalogService = new CatalogService();
        List<CartItem> itemList = cartItemDAO.getCartItemsByCartId(cart.getCartId());
        for (CartItem cartitem : itemList) {
            boolean isInStock = catalogService.isItemInStock(cartitem.getItemId());
            Item item = catalogService.getItem(cartitem.getItemId());
            cart.addItem(item, isInStock);
            cart.setQuantityByItemId(item.getItemId(), cartitem.getQuantity());
        }
        return cart;
    }

    public void updateCart(Cart cart){
        List<CartItem> itemList = cart.getCartItemList();
        for (CartItem cartItem : itemList) {
            //cartItem.setCartId();
            cartItemDAO.insertCartItem(cartItem);
        }
    }

    public Cart mergeCart(Cart usercart, Cart cart){
        List<CartItem> itemList = cart.getCartItemList();
        //CatalogService catalogService = new CatalogService();
        for (CartItem cartItem : itemList) {
            String itemId = cartItem.getItemId();
            if(usercart.containsItemId(itemId)){
                cartItemDAO.updateCartItemQuantity(usercart.getCartId(), itemId,
                        usercart.getCartItem(itemId).getQuantity() + cartItem.getQuantity());
            }else{
                cartItem.setCartId(usercart.getCartId());
                cartItemDAO.insertCartItem(cartItem);
            }
            usercart.addCartItem(cartItem);
        }
        return usercart;
    }

    public void addItem(Cart cart, CartItem item){
        //item.setCartId(cart.getCartId());
        cartItemDAO.insertCartItem(item);
    }

    public void updateItem(Cart cart, String itemId, int quantity){
        cartItemDAO.updateCartItemQuantity(cart.getCartId(), itemId, quantity);
    }

    public void deleteItem(Cart cart, String itemId) {
        try {
            cartItemDAO.deleteCartItem(cart.getCartId(), itemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearCart(Cart cart){
        cartItemDAO.clearCartItemsByCartId(cart);
    }

    public void createCart(String username){
        Cart cart = new Cart();
        cart.setUsername(username);
        cartDAO.insertCart(cart);
    }
}
