package Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public abstract class Order {

    protected List<OrderItem> items;
    protected boolean isValidated;
    protected Date orderDate;


    public Order(List<OrderItem> items) {
        this.items = items;
        this.isValidated = false;
        this.orderDate = new Date();

    }

    public boolean validateOrder(Pharmacy pharmacy) {
        for(OrderItem item : items) {
            if(!pharmacy.isStockAvailable(item.getProductId(), item.getQuantity())) {
                System.out.println("Not enough stock for product " + item.getProductId());
                return false;
            }
        }
        this.isValidated = true;
        return true;
    }

    public abstract void processOrder(Pharmacy pharmacy);

    public Date getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }


}
