package org.example.bookshop.service.shoppingcart;

import org.example.bookshop.model.ShoppingCart;
import org.example.bookshop.model.User;

public interface ShoppingCartService {
    ShoppingCart getByUserId(Long userId);

    void addBook(Long userId, Long bookId, int quantity);

    void updateCartItem(Long cartItemId, int quantity);

    ShoppingCart createShoppingCart(User user);

    void removeCartItem(Long cartItemId);
}
