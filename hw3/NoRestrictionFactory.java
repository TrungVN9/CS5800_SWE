package hw3;

/**
 *
 * @author trungvong
 */

//  Implement the NoRestrictionFactory class that creates any type of Carbs, Protein, and Fats
class NoRestrictionFactory extends MacronutrientFactory {
    @Override
    public String getPlanName() {
        return "No Restriction Option";
    }

    @Override
    Carbs createCarbs() {
        int rand = (int) (Math.random() * 4);
        return switch (rand) {
            case 0 -> new Cheese();
            case 1 -> new Bread();
            case 2 -> new Lentils();
            case 3 -> new Pistachio();
            default -> null;
        };
    }

    @Override
    Protein createProtein() {
        int rand = (int) (Math.random() * 4);
        return switch (rand) {
            case 0 -> new Fish();
            case 1 -> new Chicken();
            case 2 -> new Beef();
            case 3 -> new Tofu();
            default -> null;
        };
    }

    @Override
    Fats createFats() {
        int rand = (int) (Math.random() * 4);
        return switch (rand) {
            case 0 -> new Avocado();
            case 1 -> new SourCream();
            case 2 -> new Tuna();
            case 3 -> new Peanuts();
            default -> null;
        };
    }
}