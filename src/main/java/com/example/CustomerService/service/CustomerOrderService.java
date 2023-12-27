package com.example.CustomerService.service;

import com.example.CustomerService.model.dto.CartDTO;
import com.example.CustomerService.model.dto.CustomerDTO;
import com.example.CustomerService.model.dto.NewCustomerOrder;
import com.example.CustomerService.model.dto.NewOrder;
import com.example.CustomerService.model.entity.Cart;
import com.example.CustomerService.model.entity.CartItem;
import com.example.CustomerService.model.entity.Customer;
import com.example.CustomerService.model.entity.CustomerOrder;
import com.example.CustomerService.model.enums.OrderStatus;
import com.example.CustomerService.repository.CartRepository;
import com.example.CustomerService.repository.CustomerOrderRepository;
import com.example.CustomerService.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CamundaService camundaService;
    //private final RestaurantClient restaurantClient; //TODO: gRPC

    @Transactional
    public CustomerOrder createCustomerOrder(CustomerDTO customer, NewCustomerOrder data) {
        //TODO: Price should scale with distance of delivery - for now default 29 kr.
        data.setDeliveryPrice(29.0);

        CustomerOrder customerOrder = new CustomerOrder(data);

        Customer newCustomer = customerRepository.findById(data.getCustomerId())
                .orElseThrow();

        customerOrder.setCustomer(newCustomer);

        newCustomer.getCustomerOrder().add(customerOrder);

        return customerOrderRepository.save(customerOrder);
    }

    @Transactional
    public int updateSystemOrder(OrderStatus orderStatus, int id) {
        return customerOrderRepository.setCustomerOrderStatus(orderStatus, id);
    }

    public CartDTO addItemToCart(String customerId, Cart cart) {
        int parsedCustomerId = Integer.parseInt(customerId);

        cart.setCustomerId(parsedCustomerId);
        Cart newCustomerCart = findCartForCustomer(cart);

        //TODO: gRPC
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

    public String purchaseOrder(String customerId) {
        int parsedCustomerId = Integer.parseInt(customerId);
        Cart cart = cartRepository.findById(parsedCustomerId).orElseThrow();

        cart.setCustomerId(parsedCustomerId);

        cartRepository.save(cart);

        return camundaService.startOrderProcess(customerId, new NewOrder(cart));
    }

    public Optional<CustomerOrder> findCustomerOrderBySystemOrderId(int systemOrderId) {
        Optional<CustomerOrder> customerOrder = Optional.of(customerOrderRepository.findCustomerOrderBySystemOrderId(systemOrderId)).orElseThrow();

        return customerOrder;
    }

    public Map<String, Boolean> emptyCart(int customerId) {
        cartRepository.deleteById(customerId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }
}