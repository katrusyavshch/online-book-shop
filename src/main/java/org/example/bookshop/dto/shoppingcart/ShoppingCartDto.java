package org.example.bookshop.dto.shoppingcart;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.example.bookshop.model.CartItem;
import org.example.bookshop.model.User;

@Getter
@Setter
public class ShoppingCartDto {
    private Long id;
    private User user;
    private Set<CartItem> cartItems;
}
