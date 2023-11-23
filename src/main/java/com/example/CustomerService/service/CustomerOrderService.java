package com.example.CustomerService.service;

import com.example.CustomerService.model.dto.CartDTO;
import com.example.CustomerService.model.dto.CustomerDTO;
import com.example.CustomerService.model.dto.NewCustomerOrder;
import com.example.CustomerService.model.entity.Cart;
import com.example.CustomerService.model.entity.CartItem;
import com.example.CustomerService.model.entity.Customer;
import com.example.CustomerService.model.entity.CustomerOrder;
import com.example.CustomerService.repository.CartRepository;
import com.example.CustomerService.repository.CustomerOrderRepository;
import com.example.CustomerService.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    //private final RestaurantClient restaurantClient; //TODO: gRPC

//    public CustomerOrder createCustomerOrder(CustomerDTO customer, NewCustomerOrder data) {
//        //TODO: Price should scale with distance of delivery - for now default 29 kr.
//        data.setDeliveryPrice(29.0);
//
//        CustomerOrder customerOrder = new CustomerOrder(data);
//
//        Customer newCustomer = customerRepository.findById(data.getCustomerId()).orElseThrow();
//
//        // add to customer order entity
//        customerOrder.setCustomer(customer);
//        // add to customer entity
//        customer.getCustomerOrder().add(customerOrder);
//        // save customer order
//        return customerOrderRepository.save(customerOrder);
//    }

    public CartDTO addItemToCart(String customerId, Cart cart) {
        int parsedCustomerId = Integer.parseInt(customerId);

        cart.setCustomerId(parsedCustomerId);
        Cart newCustomerCart = findCartForCustomer(cart);

        //newCustomerCart.setTotalPrice(restaurantClient.restaurantCalculateTotalPrice(cart));

        newCustomerCart.getItems().clear();

        for (CartItem item : cart.getItems()) {
            newCustomerCart.getItems().add(item);
        }

        CartDTO cartDTO = new CartDTO(cartRepository.save(newCustomerCart));
        return cartDTO;
    }

    private Cart findCartForCustomer(Cart cart) {
        return cartRepository.findById(cart.getCustomerId()).orElseGet(() -> cartRepository.save(cart));
    }
}
