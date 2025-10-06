package hw3;

public abstract class Protein {
    public abstract void selectProtein();
 }

class Fish extends Protein {
    @Override
    public void selectProtein() {
        System.out.println("Selected Fish as Protein");
    }
}

class Chicken extends Protein {
    @Override
    public void selectProtein() {
        System.out.println("Selected Chicken as Protein");
    }
}

class Beef extends Protein {
    @Override
    public void selectProtein() {
        System.out.println("Selected Beef as Protein");
    }
}

class Tofu extends Protein {
    @Override
    public void selectProtein() {
        System.out.println("Selected Tofu as Protein");
    }
}