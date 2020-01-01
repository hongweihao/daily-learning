package mkii.designPattern.observer;

import java.util.LinkedList;
import java.util.List;

public class WeatherData implements Subject {

    // 温度、湿度、气压
    private float temperature;
    private float humidity;
    private float pressure;
    // 订阅者对象列表  --组合
    private List<Observer> observers;

    public WeatherData(){
        observers = new LinkedList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        //System.out.println("registered subject!");
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(getTemperature(), getHumidity(), getPressure());
        }
    }

    // 模拟数据发送变化
    public void change(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        notifyObserver();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
