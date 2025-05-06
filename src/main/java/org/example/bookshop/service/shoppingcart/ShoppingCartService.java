package org.example.bookshop.service.shoppingcart;

import org.example.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.example.bookshop.model.ShoppingCart;
import org.example.bookshop.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getByUserId(Long userId);

    ShoppingCartDto addBook(Long userId, Long bookId, int quantity);

    ShoppingCartDto updateCartItem(Long cartItemId, int quantity);

    ShoppingCart createShoppingCart(User user);

    void removeCartItem(Long cartItemId);
}
