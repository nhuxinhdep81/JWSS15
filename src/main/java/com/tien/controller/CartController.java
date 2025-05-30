package com.tien.controller;

import com.tien.model.Cart;
import com.tien.model.Order;
import com.tien.model.OrderDetail;
import com.tien.service.CartService;
import com.tien.service.OrderService;
import com.tien.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public String addToCart(@RequestParam("userId") int userId,
                            @RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity) {
        cartService.addOrUpdateCart(userId, productId, quantity);
        return "redirect:/cart/view?userId=" + userId;
    }

    // Hiển thị giỏ hàng
    @GetMapping("/view")
    public String viewCart(@RequestParam("userId") int userId, Model model) {
        List<Cart> carts = cartService.getCartByUserId(userId);

        double totalPrice = carts.stream()
                .mapToDouble(c -> c.getQuantity() * c.getProductPrice())
                .sum();

        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("userId", userId);
        return "cart";
    }

    // Trang checkout
    @GetMapping("/checkout")
    public String showCheckoutPage(@RequestParam("userId") int userId, Model model) {
        List<Cart> cartItems = cartService.getCartByUserId(userId);

        model.addAttribute("cartItems", cartItems);

        double total = cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getProductPrice())
                .sum();

        model.addAttribute("total", total);
        model.addAttribute("order", new Order());
        model.addAttribute("userId", userId);
        return "checkout";
    }


    @PostMapping("/checkout")
    public String processCheckout(@RequestParam("userId") int userId,
                                  @ModelAttribute Order order,
                                  Model model) {
        // Gán userId cho order
        order.setUserId(userId);

        // Lấy giỏ hàng
        List<Cart> cartItems = cartService.getCartByUserId(userId);

        // Chuyển Cart thành OrderDetail
        List<OrderDetail> orderDetails = cartItems.stream().map(cart -> {
            OrderDetail detail = new OrderDetail();
            detail.setProductId(cart.getProductId());
            detail.setQuantity(cart.getQuantity());
            detail.setCurrentPrice(cart.getProductPrice());
            return detail;
        }).toList();

        // Gọi service lưu đơn hàng và chi tiết
        orderService.createOrder(order, orderDetails);

        // Xoá giỏ hàng
        cartService.clearCartByUser(userId);

        return "redirect:/cart/view?userId=" + userId + "&success=true";
    }

    @GetMapping("/orders")
    public String listOrdersByUser(@RequestParam("userId") int userId, Model model) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        model.addAttribute("orders", orders);
        model.addAttribute("userId", userId);
        return "order-list";  // View hiển thị danh sách đơn hàng
    }

    // Hiển thị chi tiết đơn hàng theo orderId
    @GetMapping("/orders/{orderId}")
    public String showOrderDetails(@PathVariable("orderId") int orderId, Model model) {
        List<OrderDetail> orderDetails = orderService.getOrderDetailsByOrderId(orderId);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("orderId", orderId);
        return "order-detail"; // View hiển thị chi tiết đơn hàng
    }
}
