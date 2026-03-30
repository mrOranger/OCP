import java.time.Month;
import java.time.ZoneId;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.Period;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Application {
    
    public static void main (String[] args) {

        Application.stringPoolEquality();
        Application.normalStringEquality();
        Application.forceUseStringPool();
        Application.stringComparison();
        Application.handleStringBuilder();

        Application.localDate();
        Application.localTime();
        Application.localDateTime();
        Application.zonedDateTime();

        Application.period();
        Application.duration();

    }

    public static void stringPoolEquality () {

        final var aString = "Edoardo Oranger";
        final var anotherString = "Edoardo Oranger";

        System.out.println(aString == anotherString);

    }

    public static void normalStringEquality () {

        final var aString = "Edoardo Oranger";
        final var anotherString = new String("Edoardo Oranger");

        System.out.println(aString == anotherString);

    }

    public static void forceUseStringPool() {
        
        final var aString = "Edoardo Oranger";
        final var anotherString = new String("Edoardo Oranger").intern();

        System.out.println(aString == anotherString);

    }

    public static void stringComparison () {

        final var aString = "Edoardo Oranger";
        final var anotherString = new String("Edoardo Oranger");

        System.out.println("aString == anotherString = " + (aString == anotherString));
        System.out.println("aString.equals(anotherString) = " + (aString.equals(anotherString)));

    }

    public static void handleStringBuilder () {
    
        final var stringBuilder = new StringBuilder();

        stringBuilder.append("Hello World!").append('\n')
            .append("from 'append' method of 'StringBuilder' class!");

        System.out.println(stringBuilder);

    }

    public static void localDate () {
        
        final var aLocalDate = LocalDate.of(2000, Month.JANUARY, 1);

        System.out.println(aLocalDate.toString());

    }

    public static void localTime () {
        
        final var aLocalTime = LocalTime.of(0, 0, 0);

        System.out.println(aLocalTime);

    }

    public static void localDateTime () {
       
        final var aLocalDateTime = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0);

        System.out.println(aLocalDateTime);

    }

    public static void zonedDateTime () {
        final var aLocalDate = LocalDate.of(2000, 1, 1);
        final var aLocalTime = LocalTime.of(0, 0, 0);
        final var aZonedDateTime = ZonedDateTime.of(aLocalDate, aLocalTime, ZoneId.of("Europe/Rome"));

        System.out.println(aZonedDateTime);

    }

    public static void period () {
        final var aPeriod = Period.of(1, 0, 0);

        System.out.println(aPeriod.toString());
    }

    public static void duration () {
        final var aDuration = Duration.of(1, ChronoUnit.DAYS);

        System.out.println(aDuration);
    }

}
