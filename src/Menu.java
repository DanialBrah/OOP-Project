public class Menu {
    private int menuId;
    private String menuName;
    private double price;
    private String category;

    public Menu(int menuId, String menuName, double price, String category) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void displayMenu() {
        System.out.println("Menu: " + menuName + " | Price: $" + price + " | Category: " + category);
    }
}
