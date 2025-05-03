package org.example.bookshop.dto.shoppingcart;

import java.util.Set;
import lombok.Data;
import org.example.bookshop.model.CartItem;
import org.example.bookshop.model.User;

@Data
public class ShoppingCartDto {
    private Long id;
    private User user;
    private Set<CartItem> cartItems;
}
