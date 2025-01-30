package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public abstract class Order {

    protected List<OrderItem> items;
    protected boolean isValidated;
    protected String orderDate;


    public Order(List<OrderItem> items) {
        this.items = items;
        this.isValidated = false;
        this.orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) { // Permettre le chargement depuis JSON
        this.orderDate = orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }


}
