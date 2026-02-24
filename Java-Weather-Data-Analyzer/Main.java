import java.io.IOException;
import java.time.Month;

/**
 * The main class of the project.
 */
public class Main {
    /**
     * Print out the days with clear sky.
     * @param args The command line arguments.
     */
    public static void main(String[] args) throws IOException {
        String weatherDataFile;
        if (args.length == 0) {
            weatherDataFile = "data/new-york.csv";
        } else {
            weatherDataFile = args[0];
        }
        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer(weatherDataFile);
        weatherAnalyzer.printDaysWithCondition("overcast");
        double totalRainfall = weatherAnalyzer.getTotalRainfall();
        System.out.println("Total Rainfall: " + totalRainfall);

        double maxTemperature = weatherAnalyzer.getMaxTemperature();
        System.out.println("Max Temperature: " + maxTemperature);

        weatherAnalyzer.printFirstDayWithSnow();
        weatherAnalyzer.printFirstThreeDaysWithSnow();
        weatherAnalyzer.printFirstNDaysWithSnow(5); // Example with 5 days

        double maxWindSpeedInFirst31Days = weatherAnalyzer.getMaxWindSpeedInFirst31Days();
        System.out.println("Max Wind Speed in First 31 Days: " + maxWindSpeedInFirst31Days);

        double totalRainfallAfterFirst31Days = weatherAnalyzer.getTotalRainfallAfterFirst31Days();
        System.out.println("Total Rainfall After First 31 Days: " + totalRainfallAfterFirst31Days);

        double totalRainfallInNext28Days = weatherAnalyzer.getTotalRainfallInNext28DaysAfterFirst31Days();
        System.out.println("Total Rainfall in Next 28 Days After First 31 Days: " + totalRainfallInNext28Days);

        double totalRainfallForApril = weatherAnalyzer.getTotalRainfallForMonth(Month.APRIL);
        System.out.println("Total Rainfall for April: " + totalRainfallForApril);

        double totalRainfallForOctober = weatherAnalyzer.getTotalRainfallForMonth(Month.OCTOBER);
        System.out.println("Total Rainfall for October: " + totalRainfallForOctober);
    }
}