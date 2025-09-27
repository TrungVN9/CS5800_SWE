package hw3;

class PaleoFactory extends MacronutrientFactory{
    @Override
    Carbs createCarbs()
    {
        return new Pistachio();
    }

    @Override
    Protein createProtein() {
        int rand = (int) (Math.random() * 3);
        return switch (rand) {
            case 0 -> new Fish();
            case 1 -> new Chicken();
            case 2 -> new Beef();
            default -> null;
        };
    }

    @Override
    Fats createFats() {
        int rand = (int) (Math.random() * 3);

        return switch (rand){
            case 0 -> new Avocado();
            case 1 -> new Tuna();
            case 2 -> new Peanuts();
            default -> null;
        };
    }
}
