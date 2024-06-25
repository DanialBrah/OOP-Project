import java.util.ArrayList;

public class Order{
    private ArrayList<Menu> foodOrder;
    private String orderId;
    private double orderPrice;

    public Order(String orderId) {
        this.orderId = orderId;
        this.foodOrder = new ArrayList<>();
    }

    public void addMenuItem(Menu menu) {
        foodOrder.add(menu);
        orderPrice += menu.getPrice();
    }

    public ArrayList<Menu> getOrder() {
        return foodOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public double calculateTotal() {
        double total = 0;
        for (Menu menu : foodOrder) {
            total += menu.getPrice();
        }
        return total;
    }

    //display order information
    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        for (Menu menu : foodOrder) {
            System.out.println("Menu: " + menu.getMenuName() + " | Price: $" + menu.getPrice() + " | Category: " + menu.getCategory());
        }
        System.out.println("Total: $" + calculateTotal());
        System.out.println();
    }
}
