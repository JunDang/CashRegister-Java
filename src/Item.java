public class Item {

    protected String description;
    protected Float price;
    protected int quantity;

    public Item(String description, Float price, int quantity)
    {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString()
    {
        return this.quantity + " " + this.description + " at price " + this.price + ".";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
