import java.util.Arrays;
public class ShoppingCartTester {
    /**
     * Checks whether ShoppingCart.lookupProductByName() and
     * ShoppingCart.lookupProductById() methods work as expected.
     * @return true when this test verifies a correct functionality,
     * and false otherwise
     */
    public static boolean testLookupMethods() {
// define test scenarios.
// 1. The item to find is at index 0 of the marketItems array
        String expectedOutput = "4390 Apple $1.59";
        if(!ShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed Apple as input");
            return false;
        }
        if(!ShoppingCart.lookupProductById(4390).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the id "
                    + "of 4390 as input");
            return false;
        }
// TODO complete the following tester scenarios
// 2. The item to find is at the last non-null position of
// the marketItems array
        expectedOutput = "4688 Tomato $1.79";
        if (!ShoppingCart.lookupProductByName("Tomato").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed Tomato as input");
            return false;
        }
        if(!ShoppingCart.lookupProductById(4688).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the id "
                    + "of 4688 as input");
            return false;
        }

        // 3. The item to find is at an arbitrary position of the
        // middle of the marketItems array
        expectedOutput = "4071 Chocolate $3.19";
        if (!ShoppingCart.lookupProductByName("Chocolate").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed Chocolate as input");
            return false;
        }
        if(!ShoppingCart.lookupProductById(4071).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the id "
                    + "of 4071 as input");
            return false;
        }

        // 4. The item to find is not found in the market
        expectedOutput = "No match found";
        if(!ShoppingCart.lookupProductByName("NOT FOUND").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed the name of "
                    + "a product not found in the market.");
            return false;
        }
        if(!ShoppingCart.lookupProductById(1000).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the identifier"
                    + "of a product not found in the market.");
            return false;
        }
// You may add other test scenarios
        return true; // NO BUGS detected by this tester method
    }

    /**
     * Checks the correctness of ShoppingCart.getProductPrice() method
     * @return true when this test verifies a correct functionality,
     * and false otherwise
     */
    public static boolean testGetProductPrice() {
// define test scenarios
// first test scenario: get the price of Apple
        double expectedPrice = 1.59;
        if(Math.abs(ShoppingCart.getProductPrice("Apple") - expectedPrice) > 0.001) {
            return false;
        }
        expectedPrice = 1.79;
        if (Math.abs(ShoppingCart.getProductPrice("Tomato") - expectedPrice) > 0.001) {
            return false;
        }
        expectedPrice = 3.19;
        if (Math.abs(ShoppingCart.getProductPrice("Chocolate") - expectedPrice) > 0.001) {
            return false;
        }

        if (testLookupMethods() == false) {
            return false;
        }
        return true;
    }

    // This tester method checks the correctness of addItemToCart,
// contains, and nbOccurrences methods defined in the ShoppingCart class
    public static boolean testAddItemToCartContainsNbOccurrences() {
        String itemToAdd = "Lettuce";
        String[] cart = new String[5];
        int size = 0;
        int actualSize = 1;
        if (ShoppingCart.addItemToCart(itemToAdd, cart, size) != actualSize) {
            System.out.println("Problem detected: Your addItemToCart() method " +
                    " failed to return the expected output when trying to add " +
                    " an item to the cart");
            return false;
        }

        cart = new String[] {"Banana", "Carrot", "Blueberry", "Chocolate"};
        size = 4;
        actualSize = 4;
        if (ShoppingCart.addItemToCart(itemToAdd, cart, size) != actualSize) {
            System.out.println("Problem detected: Your addItemToCart() method " +
                    " failed to return the expected output when trying to add " +
                    " to a full cart");
            return false;
        }

        cart = new String[] {"Banana", "Carrot", "Blueberry", "Chocolate", null, null};
        size = 4;
        actualSize = 5;
        if (ShoppingCart.addItemToCart(itemToAdd, cart, size) != actualSize) {
            System.out.println("Problem deteced: Your addItemToCart() method " +
                    " failed to return the expected output when trying to add " +
                    "to a non-empty cart");
            return false;
        }
        if (!cart[4].equals("Lettuce")) {
            System.out.println("Problem deteced: Your addItemToCart() method " +
                    " failed to return the expected output when trying to add " +
                    "to a non-empty cart");
        }
//want to test for failure
        String item = "Eggs";
        cart = new String[] {"Eggs", "Milk", "Chocolate", "Eggs", "Grape"};
        size = 5;
        int numOfOccurrences = 2;
        if (ShoppingCart.nbOccurrences(item, cart, size) != numOfOccurrences) {
            System.out.println("Problem detected: Your nbOccurrences() method " +
                    " failed to return the expected output");
            return false;
        }

        item = "Blueberry";
        cart = new String[] {"Eggs", "Milk", "Chocolate", "Eggs", "Grape"};
        size = 5;
        numOfOccurrences = 0;
        if (ShoppingCart.nbOccurrences(item, cart, size) != numOfOccurrences) {
            System.out.println("Problem detected: Your nbOccurrences() method " +
                    " failed to return the expected output");
            return false;
        }

        item = "Blueberry";
        cart = new String[] {"Eggs", "Milk", "Chocolate", "Blueberry", "Grape"};
        size = 5;
        if (!ShoppingCart.contains(item, cart, size)) {
            System.out.println("Problem detected: Your contains() method " +
                    " failed to return the expected output");
            return false;
        }
        return true;
    }

    // This tester method checks the correctness of removeItem() method
// defined in the ShoppingCart class
    public static boolean testRemoveItem() {
        String itemToRemove = "Eggs";
        String[] cart = new String[] {"Eggs", "Milk", "Apple", "Banana", "Pizza"};
        int size = 5;
        int expectedSize = 4;
        if (ShoppingCart.removeItem(cart, itemToRemove, size) != expectedSize ) {
            System.out.println("Problem detected: Your removeItem() method " +
                    " failed to return the expected output when Eggs passed as input");
            return false;
        }
        if (cart[0] != "Pizza") {
            System.out.println("Problem detected: Your removeItem() method " +
                    " failed to return the expected output when Eggs passed as input");
            return false;
        }
        cart = new String[] {"Eggs", "Milk", "Apple", "Banana", "Pizza"};
        size = 5;
        expectedSize = 4;
        if (ShoppingCart.removeItem(cart, "Pizza", size) != expectedSize) {
            System.out.println("Problem detected: Your removeItem() method " +
                    " failed to return the expected output when Pizza passed as input");
            return false;
        }
        if (cart[4] != null) {
            System.out.println("Problem detected: Your removeItem() method " +
                    " failed to return the expected output when Pizza passed as input");
            return false;
        }
        cart = new String[] {"Eggs", "Milk", "Apple", "Banana", "Pizza"};
        size = 5;
        expectedSize = 4;
        if (ShoppingCart.removeItem(cart, "Banana", size) != expectedSize) {
            System.out.println("Problem detected: Your removeItem() method " +
                    " failed to return the expected output when Banana passed as input");
            return false;
        }

        if (cart[3].equals("Chocolate")) {
            System.out.println("Problem detected: Your removeItem() method " +
                    " failed to return the expected output as item is non-existing in cart");
            return false;
        }
        cart = new String[5];
        size = 0;
        expectedSize = 0;
        if (ShoppingCart.removeItem(cart, "Apple", size) != expectedSize) {
            System.out.println("Problem detected: Your removeItem() method " +
                    " failed to return the expected output as cart is empty");
            return false;
        }
        return true;
    }

    // This tester method checks the correctness of checkout and
// getCartSummary() methods defined in the ShoppingCart class
    public static boolean testCheckoutGetCartSummary() {
        String[] cart = new String[10];
        int size = 0;
        if (ShoppingCart.getCartSummary(cart, size) != "") {
            System.out.println("Problem detected: Your getCartSummary() method " +
                    " failed to return the expected output when the cart is empty");
            return false;
        }
        cart = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
        size = 4;
        String expectedOutput = "(1) Milk\n (1) Apple\n (1) Banana\n (1) Pizza\n";
        if (ShoppingCart.getCartSummary(cart, size) != expectedOutput) {
            System.out.println("Problem detected: Your getCartSummary() method " +
                    " failed to return the expected output when the cart contains unique items");
            return false;
        }
        cart = new String[] {"Milk", "Milk", "Banana", "Banana", "Milk", "Apple"};
        size = 6;
        expectedOutput = "(2) Milk\n (2) Banana\n (1) Milk\n (1) Apple\n";
        if (ShoppingCart.getCartSummary(cart, size) != expectedOutput) {
            System.out.println("Problem detected: Your getCartSummary() method " +
                    " failed to return the expected output when the cart contains multiple items");
            return false;
        }
        return true;
    }

    // This tester runs all the tester methods defined in this tester class.
// For instance, if this tester class defines three tester methods
// named test1(), test2() and test3(), it will return test1() && test2() && test3()
// Returns false if any of the tester methods fails, and true if all
// the tests are passed.
    public static boolean runAllTests() {
        if (!(testAddItemToCartContainsNbOccurrences() && testRemoveItem() && testLookupMethods()
        && testGetProductPrice() && testCheckoutGetCartSummary())) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("runAllTests: " + runAllTests());
    }
}
