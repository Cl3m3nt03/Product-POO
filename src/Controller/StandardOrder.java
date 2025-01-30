package Controller;

import java.util.List;

public class StandardOrder extends Order {

    public StandardOrder(List<OrderItem> items) {
        super(items);
    }

    @Override
    public void processOrder(Pharmacy pharmacy) {
        if (!isValidated) {
            System.out.println("Order is invalid");
            return;
        }

        for (OrderItem item : items) {
            pharmacy.reduceStock(item.getProductId(), item.getQuantity());
        }
        System.out.println("Order processed");
    }
}
