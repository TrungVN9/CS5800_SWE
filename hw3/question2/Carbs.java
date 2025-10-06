package hw3;

public abstract class Carbs {
    public abstract void selectCarbs();
 }

class Cheese extends Carbs {
    @Override
    public void selectCarbs() {
        System.out.println("Selected Cheese as Carbs");
    }
}
class Bread extends Carbs {
    @Override
    public void selectCarbs() {
        System.out.println("Selected Bread as Carbs");
    }
}
class Lentils extends Carbs {
    @Override
    public void selectCarbs() {
        System.out.println("Selected Lentils as Carbs");
    }
}

class Pistachio extends Carbs {
    @Override
    public void selectCarbs() {
        System.out.println("Selected Pistachio as Carbs");
    }
}