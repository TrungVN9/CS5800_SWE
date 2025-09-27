package hw3;
/**
 *
 * @author trungvong
 */

abstract class Fats {
    abstract void selectFats();
 }

//  Create concrete classes for various types of Fats
class Avocado extends Fats {
    @Override
    void selectFats() {
        System.out.println("Selected Avocado as Fats");
    }
}
class SourCream extends Fats {
    @Override
    void selectFats() {
        System.out.println("Selected Sour Cream as Fats");
    }
}
class Tuna extends Fats {
    @Override
    void selectFats() {
        System.out.println("Selected Tuna as Fats");
    }
}
class Peanuts extends Fats {
    @Override
    void selectFats() {
        System.out.println("Selected Peanuts as Fats");
    }
}