package mkii.designPattern.observer;

public interface Observer {
    void subscribe();
    void unSubscribe();
    void update(float temperature, float humidity, float pressure);
}
