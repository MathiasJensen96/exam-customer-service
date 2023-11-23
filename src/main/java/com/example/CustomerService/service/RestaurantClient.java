package com.example.CustomerService.service;

import com.example.CustomerService.model.entity.Cart;
import com.example.CustomerService.model.entity.CartItem;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class RestaurantClient {
//    @GrpcClient("grpc-restaurant-service")
//    private RestaurantGrpc.RestaurantBlockingStub synchronousCall;
//
//    public double restaurantCalculateTotalPrice(Cart entity) {
//        Order.Builder orderBuilder = Order.newBuilder()
//                .setRestaurantId(entity.getRestaurantId())
//                .setTotalPrice(entity.getTotalPrice());
//
//        List<OrderItem> itemList = new ArrayList();
//
//        for (int i = 0; i < entity.getItems().size(); i++) {
//            CartItem cartItem = entity.getItems().get(i);
//            OrderItem orderItemRequest = OrderItem.newBuilder()
//                    .setMenuItemId(cartItem.getMenuItemId())
//                    .setQuantity(cartItem.getQuantity())
//                    .setPrice(0.00)
//                    .build();
//            itemList.add(orderItemRequest);
//        }
//
//        Iterable<OrderItem> itemIterable = itemList;
//        orderBuilder.addAllItems(itemIterable);
//
//        Order response = synchronousCall.calculateOrderPrice(orderBuilder.build());
//
//        return response.getTotalPrice();
//    }
}
