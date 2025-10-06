package hw3;

class NutAllergyFactory extends MacronutrientFactory {
    private static NutAllergyFactory instance = null;

    private NutAllergyFactory() {
    }

    public static NutAllergyFactory getInstance() {
        if (instance == null) {
            instance = new NutAllergyFactory();
        }
        return instance;
    }
    
    @Override
    public String getPlanName() {
        return "Nut Allergy Option";
    }
    
    @Override
    Carbs createCarbs() {
        int rand = (int) (Math.random() * 3);
        return switch (rand) {
            case 0 -> new Cheese();
            case 1 -> new Bread();
            case 2 -> new Lentils();
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
        int rand = (int) (Math.random() * 3);
        return switch (rand) {
            case 0 -> new Avocado();
            case 1 -> new SourCream();
            case 2 -> new Tuna();
            default -> null;
        };
    }
    
}
