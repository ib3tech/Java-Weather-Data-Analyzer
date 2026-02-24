import java.io.IOException;
import java.time.Month;
import java.util.List;

public class WeatherAnalyzer {
    private static final WeatherDataReader reader = new WeatherDataReader();
    private final List<WeatherData> weatherData;

    /**
     * Create a new weather analyser.
     *
     * @param weatherDataFile The file of weather data.
     * @throws IOException On any IO issue.
     */
    public WeatherAnalyzer(String weatherDataFile) throws IOException {
        this.weatherData = reader.readWeatherData(weatherDataFile);
    }

    /**
     * Print out each day's weather data.
     */
    public void printWeatherData() {
        weatherData.forEach(System.out::println);
    }

    /**
     * Print out the days with a specific weather condition.
     */
    public void printDaysWithCondition(String condition) {
        for (WeatherData data : weatherData) {
            if (data.weatherCondition().contains(condition)) {
                System.out.println(data);
            }
        }
    }

    /**
     * Print out the dates with a specific weather condition.
     */
    public void printDatesWithCondition(String condition) {
        weatherData.stream()
                .filter(data -> data.weatherCondition().contains(condition))
                .map(WeatherData::date)
                .forEach(System.out::println);
    }

    /**
     * Get the total rainfall.
     */
    public double getTotalRainfall() {
        return weatherData.stream()
                .mapToDouble(WeatherData::rainSum)
                .sum();
    }

    /**
     * Get the maximum temperature.
     */
    public double getMaxTemperature() {
        return weatherData.stream()
                .mapToDouble(WeatherData::maxTemp)
                .max()
                .orElse(Double.NaN);
    }

    /**
     * Get the maximum wind speed.
     */
    public double getMaxWindSpeed() {
        return weatherData.stream()
                .mapToDouble(WeatherData::maxWindSpeed)
                .max()
                .orElse(Double.NaN);
    }

    /**
     * Print details of the first day with snowfall.
     */
    public void printFirstDayWithSnow() {
        weatherData.stream()
                .filter(data -> data.snowfallSum() > 0)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No snow days found.")
                );
    }

    /**
     * Print details of the first three days with snowfall.
     */
    public void printFirstThreeDaysWithSnow() {
        weatherData.stream()
                .filter(data -> data.snowfallSum() > 0)
                .limit(3)
                .forEach(System.out::println);
    }

    /**
     * Print details of the first N days with snowfall.
     */
    public void printFirstNDaysWithSnow(int n) {
        weatherData.stream()
                .filter(data -> data.snowfallSum() > 0)
                .limit(n)
                .forEach(System.out::println);
    }

    /**
     * Get the maximum wind speed in the first 31 days.
     */
    public double getMaxWindSpeedInFirst31Days() {
        return weatherData.stream()
                .limit(31)
                .mapToDouble(WeatherData::maxWindSpeed)
                .max()
                .orElse(Double.NaN);
    }

    /**
     * Get the total rainfall after the first 31 days.
     */
    public double getTotalRainfallAfterFirst31Days() {
        return weatherData.stream()
                .skip(31)
                .mapToDouble(WeatherData::rainSum)
                .sum();
    }

    /**
     * Get the total rainfall in the next 28 days after the first 31 days.
     */
    public double getTotalRainfallInNext28DaysAfterFirst31Days() {
        return weatherData.stream()
                .skip(31)
                .limit(28)
                .mapToDouble(WeatherData::rainSum)
                .sum();
    }

    /**
     * Get the total rainfall for a given month.
     */
    public double getTotalRainfallForMonth(Month month) {
        return weatherData.stream()
                .filter(data -> data.date().getMonth() == month)
                .mapToDouble(WeatherData::rainSum)
                .sum();
    }
}