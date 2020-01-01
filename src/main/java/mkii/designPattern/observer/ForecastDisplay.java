package mkii.designPattern.observer;

public class ForecastDisplay implements Observer {
    private Subject subject;

    public ForecastDisplay(Subject subject) {
        this.subject = subject;
        subscribe();
    }

    @Override
    public void subscribe() {
        subject.addObserver(this);
    }

    @Override
    public void unSubscribe() {
        subject.removeObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("forecast\ntemperature: " + temperature + "\npressure: " + pressure);
        System.out.println("----------------------------------------");
    }
}
