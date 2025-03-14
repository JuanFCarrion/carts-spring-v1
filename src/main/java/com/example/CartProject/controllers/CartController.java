package com.example.CartProject.controllers;

import com.example.CartProject.DAO.Cart;
import com.example.CartProject.DAO.Product;
import com.example.CartProject.services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/cart/{id}")
    public Cart getCart(@PathVariable UUID id) {

        return cartService.getCart(id) ;
    }
    @DeleteMapping ("/deleteCart/{id}")
    public void deleteCart(@PathVariable UUID id) {

        cartService.deleteCart(id) ;
    }

    @PostMapping("/addCart")
    public Cart addCart() {

        return cartService.addCart() ;
    }

    @GetMapping("/getAllCarts")
    public List<Cart> getAllCarts() {

        return cartService.getAllCarts() ;
    }
    @PostMapping("cart/{id}/addProduct")
    public void  addProduct(@PathVariable UUID id, @RequestBody Product product) {

         cartService.addProduct(id,product);
    }


}
