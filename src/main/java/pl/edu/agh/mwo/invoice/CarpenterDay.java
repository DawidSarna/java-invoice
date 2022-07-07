package pl.edu.agh.mwo.invoice;

import java.time.LocalDate;

public class CarpenterDay {
    public static boolean isCarpenterDay(LocalDate date) {
        LocalDate carpenterDay = LocalDate.of(2020,3,19);
        if (carpenterDay.getMonth().equals(date.getMonth()) && carpenterDay.getDayOfMonth() == date.getDayOfMonth()){
            return true;
        }
        return false;
    }
}