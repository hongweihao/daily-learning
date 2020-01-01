package mkii.designPattern.observer;

public class CurrentConditionDisplay implements Observer {

    private Subject subject;

    public CurrentConditionDisplay(Subject subject) {
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
        System.out.println("current condition\ntemperature: " + temperature + "\nhumidity: " + humidity);
        System.out.println("----------------------------------------");
    }
}
