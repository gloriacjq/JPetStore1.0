package org.csu.mypetstore.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.*;
import org.csu.mypetstore.persistence.impl.*;

public class OrderService {
    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private OrderStatusDAO orderStatusDAO;
    private SequenceDAO sequenceDAO;
    private LineItemDAO lineItemDAO;

    public OrderService(){
        itemDAO = new ItemDAOImpl();
        orderDAO = new OrderDAOImpl();
        orderStatusDAO = new OrderStatusDAOImpl();
        sequenceDAO = new SequenceDAOImpl();
        lineItemDAO = new LineItemDAOImpl();
    }

    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrder(orderId);
        order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemDAO.getItem(lineItem.getItemId());
            item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }
        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDAO.getOrdersByUsername(username);
    }


    public void insertOrder(Order order) {
        order.setOrderId(getOrderId());
        Map<String, Object> param = new HashMap<String, Object>();
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);

            String itemId = lineItem.getItemId();
            Integer increment = lineItem.getQuantity();
            param.put(itemId, increment);

            lineItem.setOrderId(order.getOrderId());
            lineItemDAO.insertLineItem(lineItem);
        }
        itemDAO.updateInventoryQuantity(param);
        orderDAO.insertOrder(order);
        orderStatusDAO.insertOrderStatus(order);
    }


    public int getNextId(String name) {
      Sequence sequence = new Sequence(name, -1);
      sequence = (Sequence) sequenceDAO.getSequence(sequence);
      if (sequence == null) {
        throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
            + " sequence).");
      }
      Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
      sequenceDAO.updateSequence(parameterObject);
      return sequence.getNextId();
    }

    public int getOrderId(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        return Integer.parseInt(dateFormat.format(new Date().getTime()));
    }

}
