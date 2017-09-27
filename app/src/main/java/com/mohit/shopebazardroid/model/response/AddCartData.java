package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 17/8/16.
 */
public class AddCartData {

    private String shoppingcartid;
    private String items_qty;
    private String items_count;
//    private CartDetails cart;

    @Override
    public String toString() {
        return "AddCartData{" +
                "shoppingcartid='" + shoppingcartid + '\'' +
                '}';
    }

    public String getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(String shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    /*public CartDetails getCart() {
        return cart;
    }

    public void setCart(CartDetails cart) {
        this.cart = cart;
    }*/

    public String getItems_qty() {
        return items_qty;
    }

    public void setItems_qty(String items_qty) {
        this.items_qty = items_qty;
    }

    public String getItems_count() {
        return items_count;
    }

    public void setItems_count(String items_count) {
        this.items_count = items_count;
    }
}
