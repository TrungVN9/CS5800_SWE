package hw3;
/**
 *
 * @author trungvong
 */

abstract class Protein {
    abstract void selectProtein();
 }

// Create concrete classes for various types of Protein
class Fish extends Protein {
    @Override
    void selectProtein() {
        System.out.println("Selected Fish as Protein");
    }
}
class Chicken extends Protein {
    @Override
    void selectProtein() {
        System.out.println("Selected Chicken as Protein");
    }
}

class Beef extends Protein {
    @Override
    void selectProtein() {
        System.out.println("Selected Beef as Protein");
    }
}

class Tofu extends Protein {
    @Override
    void selectProtein() {
        System.out.println("Selected Tofu as Protein");
    }
}