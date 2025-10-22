package Exceptions.BusinessLogic;

public class InvalidCategoryException extends BusinessLogicException {
    public InvalidCategoryException(String category) {
        super(String.format("Invalid category: %s. Please choose a valid category.", category),
              "INVALID_CATEGORY");
    }
}
