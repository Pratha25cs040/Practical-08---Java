class OutOfStockException extends Exception {
    private int shortfall;

    public OutOfStockException(int shortfall) {
        super("Not enough stock available.");
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private String item;
    private int stock;

    public Warehouse(String item, int stock) {
        this.item = item;
        this.stock = stock;
    }

    public void issue(int quantity)
            throws OutOfStockException, InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero.");
        }

        if (quantity > stock) {
            throw new OutOfStockException(quantity - stock);
        }

        stock -= quantity;
        System.out.println("Issued " + quantity + " units of " + item);
        System.out.println("Remaining stock: " + stock);
    }
}

public class WarehouseDemo {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse("Laptop", 10);
        int[] requests = {5, 8, 0};

        for (int quantity : requests) {
            try {
                warehouse.issue(quantity);
            } catch (OutOfStockException e) {
                System.out.println(e.getMessage());
                System.out.println("Shortfall: " + e.getShortfall());
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }
    }
}
