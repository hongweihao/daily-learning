package mkii.designPattern.observer;

public class StatisticsDisplay implements Observer {
    private Subject subject;

    public StatisticsDisplay(Subject subject) {
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
        System.out.println("Statistics\nhumidity: " + humidity + "\npressure: " + pressure);
        System.out.println("----------------------------------------");
    }
}
