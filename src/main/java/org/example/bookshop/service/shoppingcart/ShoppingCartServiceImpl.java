package org.example.bookshop.service.shoppingcart;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.exception.EntityNotFoundException;
import org.example.bookshop.model.Book;
import org.example.bookshop.model.CartItem;
import org.example.bookshop.model.ShoppingCart;
import org.example.bookshop.model.User;
import org.example.bookshop.repository.book.BookRepository;
import org.example.bookshop.repository.cartitem.CartItemRepository;
import org.example.bookshop.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
                .orElseThrow(()
                        -> new EntityNotFoundException("Cart not found for user id: " + userId));
    }

    @Override
    public void addBook(Long userId, Long bookId, int quantity) {
        ShoppingCart shoppingCart = getByUserId(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book not found")
        );
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cart item not found: " + cartItemId)
                );
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public ShoppingCart createShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void validateCartItemOwnership(Long userId, Long cartItemId)
            throws AccessDeniedException {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart item not found: " + cartItemId));

        if (!cartItem.getShoppingCart().getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Cart item with id " + cartItemId
                    + " doesn't belong to user " + userId);
        }
    }
}
