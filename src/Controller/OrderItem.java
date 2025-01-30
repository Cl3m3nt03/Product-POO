package Controller;

public class OrderItem {

    private int productId;
    private int quantity;
    private String productName;


    public OrderItem(int productId, int quantity, String productName) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getProductName() {
        return productName;
    }
}
