package hw3.question2;

class VeganFactory extends MacronutrientFactory {
    private static VeganFactory instance = new VeganFactory();

    private VeganFactory() {
    }

    public static VeganFactory getInstance() {
        if (instance == null) {
            instance = new VeganFactory();
        }
        return instance;
    }

    @Override
    public String getPlanName() {
        return "Vegan Option";
    }
    
    @Override
    Carbs createCarbs() {
        int rand = (int) (Math.random() * 2);
        return switch (rand) {
            case 0 -> new Bread();
            case 1 -> new Lentils();
            default -> null;
        };
    }

    @Override
    Protein createProtein() {
        return new Tofu();
    };

    @Override
    Fats createFats() {
        int rand = (int) (Math.random() * 2);
        return switch (rand) {
            case 0 -> new Avocado();
            case 1 -> new Peanuts();
            default -> null;
        };
    }
}
