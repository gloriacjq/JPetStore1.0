package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;

public interface OrderStatusDAO {
    void  CreateNewOrder(Order order);
    void  OrderPaid(Order order);
    void insertOrderStatus(Order order);
}
