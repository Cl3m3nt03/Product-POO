package Controller;

import java.util.List;

public class UrgentOrder extends Order {

    public UrgentOrder(List<OrderItem> items) {
        super(items);

    }

    @Override
    public void processOrder(Pharmacy pharmacy) {

        if(!isValidated) {
            System.out.println("Order is invalid");
            return;
        }

        System.out.println("Urgent Order in proceed...");
        for(OrderItem item : items) {
            pharmacy.reduceStock(item.getProductId(), item.getQuantity());
        }
        System.out.println("Urgent Order processed!");

    }
}
