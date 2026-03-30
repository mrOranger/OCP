public class Application {
    
    public static void main(String[] args) {
        Application.patternMatching(12);
        Application.enhachedPatternMatching(10);
    }

    public static void patternMatching (Number randomNumber) {
        
        if (randomNumber instanceof Integer) {
            final Integer parsedNumber = (Integer)randomNumber;

            System.out.println(randomNumber + " is an instance of Integer class!");
            return;
        }

        System.out.println(randomNumber + " is an instance of another class!");

    }

    public static void enhachedPatternMatching (Number randomNumber) {
        
        if (randomNumber instanceof Integer parsedNumber && parsedNumber > 10) {
            System.out.println(randomNumber + " is an instance of Integer class, and is greater than 10!");
            return;
        }

        if (randomNumber instanceof Integer parsedNumber && parsedNumber <= 10) {
            System.out.println(randomNumber + " is an instance of Integer class, and is smaller or equal than 10!");
            return;

        }

        System.out.println(randomNumber + " is an instance of another class!");

    }


    public static void invalidPatternMatching(Number randomNumber) {
        
        if (randomNumber instanceof Integer parsedNumber && parsedNumber > 10) {
            System.out.println(randomNumber + " is an instance of Integer class, and is greater than 10!");
            return;
        }

        if (randomNumber instanceof Integer parsedNumber && parsedNumber <= 10) {
            System.out.println(randomNumber + " is an instance of Integer class, and is smaller or equal than 10!");
            return;
        }

        if (randomNumber instanceof String parsedNumber) {
            System.out.println(randomNumber + " is an instance of String!");
            return;
        }

        System.out.println(randomNumber + " is an instance of another class!");

    }

}
