package com.example.CustomerService.listener;

import com.example.CustomerService.model.dto.CustomerDTO;
import com.example.CustomerService.model.dto.NewCustomerOrder;
import com.example.CustomerService.model.dto.OrderCancelled;
import com.example.CustomerService.model.dto.SystemOrder;
import com.example.CustomerService.model.entity.CustomerOrder;
import com.example.CustomerService.model.enums.OrderStatus;
import com.example.CustomerService.service.CustomerOrderService;
import com.example.CustomerService.service.CustomerService;
import com.example.CustomerService.model.enums.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaListeners {
    private final CustomerService customerService;
    private final CustomerOrderService customerOrderService;

    @KafkaListener(
            topics = "new_order_placed",
            groupId = "new_order_id"
    )
    void newOrderPlacedListener(NewCustomerOrder data) {
        CustomerDTO customer = customerService.getCustomerById(data.getCustomerId());

        CustomerOrder customerOrder = customerOrderService.createCustomerOrder(customer, data);

        customerOrderService.emptyCart(data.getCustomerId());

        customerOrderService.updateSystemOrder(OrderStatus.PENDING, data.getId());

        customerService.notifyCustomer(customer, Topic.new_order_placed);
    }

    @KafkaListener(
            topics = "order_accepted",
            groupId = "order_accepted_id"
    )
    void orderAcceptedListener(SystemOrder data) {
        customerOrderService.updateSystemOrder(OrderStatus.IN_PROGRESS, data.getSystemOrderId());

        Optional<CustomerOrder> customerOrder = customerOrderService.findCustomerOrderBySystemOrderId(data.getSystemOrderId());

        CustomerDTO customer = new CustomerDTO(customerOrder);

        customerService.notifyCustomer(customer, Topic.customer_notification);
    }

    @KafkaListener(
            topics = "order_ready",
            groupId = "order_reader_id"
    )
    void orderReadyListener(SystemOrder data) {
        customerOrderService.updateSystemOrder(OrderStatus.READY, data.getSystemOrderId());

        Optional<CustomerOrder> customerOrder = customerOrderService.findCustomerOrderBySystemOrderId(data.getSystemOrderId());

        CustomerDTO customer = new CustomerDTO(customerOrder);

        customerService.notifyCustomer(customer, Topic.customer_notification);
    }

    @KafkaListener(
            topics = "order_cancelled",
            groupId = "order_cancelled_id"
    )
    void orderCanceledListener(OrderCancelled data) {
        customerOrderService.updateSystemOrder(OrderStatus.CANCELLED, data.getSystemOrderId());

        Optional<CustomerOrder> customerOrder = customerOrderService.findCustomerOrderBySystemOrderId(data.getSystemOrderId());

        CustomerDTO customer = new CustomerDTO(customerOrder);

        customer.setReason(data.getReason());

        customerService.notifyCustomer(customer, Topic.customer_notification);
    }

    @KafkaListener(
            topics = "order_picked_up",
            groupId = "order_picked_up_id"
    )
    void orderPickedUpListener(SystemOrder data) {
        customerOrderService.updateSystemOrder(OrderStatus.PICKED_UP, data.getSystemOrderId());

        Optional<CustomerOrder> customerOrder = customerOrderService.findCustomerOrderBySystemOrderId(data.getSystemOrderId());

        CustomerDTO customer = new CustomerDTO(customerOrder);

        customerService.notifyCustomer(customer, Topic.customer_notification);
    }

    @KafkaListener(
            topics = "order_delivered",
            groupId = "order_delivered_id"
    )
    void orderDeliveredListener(SystemOrder data) {
        customerOrderService.updateSystemOrder(OrderStatus.COMPLETED, data.getSystemOrderId());

        Optional<CustomerOrder> customerOrder = customerOrderService.findCustomerOrderBySystemOrderId(data.getSystemOrderId());

        CustomerDTO customer = new CustomerDTO(customerOrder);

        customerService.notifyCustomer(customer, Topic.customer_notification);
    }

    @KafkaListener(
            topics = "order_claimed",
            groupId = "order_claimed_id"
    )
    void orderClaimedListener(SystemOrder data) {
        customerOrderService.updateSystemOrder(OrderStatus.COMPLETED, data.getSystemOrderId());

        Optional<CustomerOrder> customerOrder = customerOrderService.findCustomerOrderBySystemOrderId(data.getSystemOrderId());

        CustomerDTO customer = new CustomerDTO(customerOrder);

        customerService.notifyCustomer(customer, Topic.customer_notification);
    }
}