package com.example.CartProject.servicesTest;

import com.example.CartProject.DAO.Cart;
import com.example.CartProject.DAO.Product;
import com.example.CartProject.exceptions.CartNotFoundException;
import com.example.CartProject.exceptions.ProductNotFoundException;
import com.example.CartProject.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {


    @Mock
    private Cart cart;

    @Mock
    private Product product;


    @InjectMocks
    private CartService cartService;

    private UUID cartId;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        cartId = UUID.randomUUID();

    }

    @Test
    void testAddCart() {

        Cart newCart = cartService.addCart();
        assertNotNull(newCart);

    }

    @Test
    void testGetCart() {

        Cart cart = mock(Cart.class);
        UUID cartId = UUID.randomUUID();

        when(cart.getId()).thenReturn(cartId);
        cartService.carts.add(cart);

        Cart retrievedCart = cartService.getCart(cartId);

        assertNotNull(retrievedCart);
        assertEquals(cartId, retrievedCart.getId());

    }

    @Test
    void testGetCartNotFound() {

        Cart retrievedCart = cartService.getCart(UUID.randomUUID());
        assertNull(retrievedCart);

    }

    @Test
    void testDeleteCart() {

        Cart cart = cartService.addCart();
        cartService.deleteCart(cart.getId());
        assertTrue(cartService.getAllCarts().isEmpty());

    }

    @Test
    void testDeleteCartNotFound() {

        assertThrows(CartNotFoundException.class, () -> cartService.deleteCart(UUID.randomUUID()));

    }

    @Test
    void testAddProductToCart() {

        Cart cart = mock(Cart.class);
        UUID cartId = UUID.randomUUID();

        when(cart.getId()).thenReturn(cartId);

        Product product = mock(Product.class);

        when(product.getId()).thenReturn(1);
        cartService.carts.add(cart);
        cartService.addProduct(cartId, product);
        verify(cart, times(1)).addProduct(product);
    }

    @Test
    void testAddProductToCartProductNotFound() {

        assertThrows(ProductNotFoundException.class, () -> cartService.addProduct(cartId, null));

    }

    @Test
    void testAddProductToCartCartNotFound() {

        int productId = 123;
        when(product.getId()).thenReturn(productId);
        UUID nonExistentCartId = UUID.randomUUID();
        assertThrows(CartNotFoundException.class, () -> cartService.addProduct(nonExistentCartId, product));

    }

    @Test
    void testEliminarCarritosExpirados() {

        Cart expiredCart = mock(Cart.class);
        when(expiredCart.getCreationTime()).thenReturn(LocalDateTime.now().minusMinutes(11));
        when(expiredCart.getId()).thenReturn(UUID.randomUUID());

        Cart validCart = mock(Cart.class);
        when(validCart.getCreationTime()).thenReturn(LocalDateTime.now().minusMinutes(5));
        when(validCart.getId()).thenReturn(UUID.randomUUID());

        cartService.carts.add(expiredCart);
        cartService.carts.add(validCart);
        cartService.eliminarCarritosExpirados();

        assertEquals(1, cartService.getAllCarts().size());
        assertTrue(cartService.getAllCarts().contains(validCart));
        assertFalse(cartService.getAllCarts().contains(expiredCart));
    }
}