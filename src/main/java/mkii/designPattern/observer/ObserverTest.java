package mkii.designPattern.observer;

public class ObserverTest {
    public static void main(String[] args) {
        // 有1个气象站主题
        WeatherData weatherData = new WeatherData();

        // 有3个布告板观察者，订阅了weatherData主题
        Observer currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        Observer statisticsDisplay = new StatisticsDisplay(weatherData);
        Observer forecastDisplay = new ForecastDisplay(weatherData);

        // 气象站数据发送变化
        weatherData.change(12, 13, 14);

        // 一个布告板取消了气象站的订阅
        forecastDisplay.unSubscribe();
        weatherData.change(1, 2, 3);
    }
}
