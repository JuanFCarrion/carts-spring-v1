package com.example.CartProject.services;


import com.example.CartProject.DAO.Cart;
import com.example.CartProject.DAO.Product;
import com.example.CartProject.exceptions.CartNotFoundException;
import com.example.CartProject.exceptions.ProductNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartService {

    public List<Cart> carts = new ArrayList<>();


    public Cart getCart(UUID id) {


        return carts.stream().filter(cart -> cart.getId().equals(id)).findFirst().orElse(null);

    }

    public void deleteCart(UUID id) {
        Cart cart = carts.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);

        if (cart == null) {
            throw new CartNotFoundException("Carrito no encontrado con ID: " + id);
        }

        carts.removeIf(c -> c.getId().equals(id));

    }

    public List<Cart> getAllCarts() {

        return carts;

    }

    public Cart addCart() {

        Cart cart = new Cart();
        carts.add(cart);
        return cart;

    }

    public void addProduct(UUID id, Product product) {

        if (product == null) {
            throw new ProductNotFoundException("El producto no existe");
        }

        Cart cart = getCart(id);

        if (cart != null) {

            cart.addProduct(product);


        } else {

            throw new CartNotFoundException("Carrito no encontrado con ID: " + id);

        }


    }

    @Scheduled(fixedRate = 60000)
    public void eliminarCarritosExpirados() {
        for (Cart cart : carts) {
            Duration duration = Duration.between(cart.getCreationTime(), LocalDateTime.now());

            if (duration.toMinutes() >= 10) {
                carts.remove(cart);  // Esto eliminaría el carrito de la lista
                System.out.println("Carrito con ID " + cart.getId() + " ha sido eliminado por expiración.");
            }
        }
    }
}