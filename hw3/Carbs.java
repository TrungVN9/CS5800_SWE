package hw3;
/**
 *
 * @author trungvong
 */

//  Product interface for Carbs
abstract class Carbs {
    abstract void selectCarbs();
 }


//  Create concrete classes for various types of Carbs
class Cheese extends Carbs {
    @Override
    void selectCarbs() {
        System.out.println("Selected Cheese as Carbs");
    }
}
class Bread extends Carbs {
    @Override
    void selectCarbs() {
        System.out.println("Selected Bread as Carbs");
    }
}
class Lentils extends Carbs {
    @Override
    void selectCarbs() {
        System.out.println("Selected Lentils as Carbs");
    }
}

class Pistachio extends Carbs {
    @Override
    void selectCarbs() {
        System.out.println("Selected Pistachio as Carbs");
    }
}