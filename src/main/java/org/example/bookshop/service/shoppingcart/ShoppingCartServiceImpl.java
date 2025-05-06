package org.example.bookshop.service.shoppingcart;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.example.bookshop.exception.EntityNotFoundException;
import org.example.bookshop.mapper.ShoppingCartMapper;
import org.example.bookshop.model.Book;
import org.example.bookshop.model.CartItem;
import org.example.bookshop.model.ShoppingCart;
import org.example.bookshop.model.User;
import org.example.bookshop.repository.book.BookRepository;
import org.example.bookshop.repository.cartitem.CartItemRepository;
import org.example.bookshop.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDto getByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
                .map(shoppingCart -> shoppingCartMapper.toDto(shoppingCart))
                .orElseThrow(()
                        -> new EntityNotFoundException("Cart not found for user id: " + userId));
    }

    @Override
    public ShoppingCartDto addBook(Long userId, Long bookId, int quantity) {
        ShoppingCart shoppingCart = findByIdAndShoppingCartId(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book not found")
        );
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cart item not found: " + cartItemId)
                );
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        ShoppingCart shoppingCart = cartItem.getShoppingCart();
        return shoppingCartMapper.toDto(shoppingCart);
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

    private ShoppingCart findByIdAndShoppingCartId(Long userId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shopping cart not found for user id: " + userId));
    }
}
