package org.example.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bookshop.dto.cartitem.CartItemDto;
import org.example.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.example.bookshop.mapper.ShoppingCartMapper;
import org.example.bookshop.model.User;
import org.example.bookshop.service.shoppingcart.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoint for managing shopping carts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Operation(summary = "Get a shopping cart", description = "Find a shopping cart for a user")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ShoppingCartDto getCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getByUserId(user.getId());
    }

    @Operation(summary = "Add a book to cart item",
            description = "Add a book to cart item for a user")
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ShoppingCartDto addBookToCart(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CartItemDto cartItemDto
    ) {
        return shoppingCartService.addBook(user.getId(), cartItemDto.getBookId(),
                cartItemDto.getQuantity());
    }

    @Operation(summary = "Update cart item", description = "Update cart item for a user")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/items/{id}")
    public ShoppingCartDto updateCartItem(@PathVariable Long id,
                               @RequestBody CartItemDto cartItemDto) {
        return shoppingCartService.updateCartItem(id, cartItemDto.getQuantity());
    }

    @Operation(summary = "Remove cart item", description = "Remove cart item for a user")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/items/{id}")
    public void removeCartItem(@PathVariable Long id) {
        shoppingCartService.removeCartItem(id);
    }
}
