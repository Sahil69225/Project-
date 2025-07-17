import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalTime;
import java.time.Duration;

public class MyGoalReminder {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("What is your goal for today?");
        String goal = input.nextLine();

        System.out.println("At what time should I remind you? (Format: HH:MM)");
        String timeStr = input.nextLine();

        try {
            LocalTime reminderTime = LocalTime.parse(timeStr);
            long delayMs = calculateDelay(reminderTime);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("Reminder: Don't forget to " + goal + "!");
                    timer.cancel();
                }
            }, delayMs);

            System.out.println("Reminder set successfully.");
        } catch (Exception e) {
            System.out.println("Oops! Time format wrong. Please use HH:MM like 14:30.");
        }

        input.close();
    }
    
    public static long calculateDelay(LocalTime time) {
        LocalTime now = LocalTime.now();
        long seconds = Duration.between(now, time).getSeconds();

        if (seconds < 0) {
            seconds += 86400;
        }

        return seconds * 1000;
    }
}

