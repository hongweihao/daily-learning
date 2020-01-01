package mkii.designPattern.observer;

public interface Subject {
    // 注册，订阅
    void addObserver(Observer observer);
    // 取消订阅
    void removeObserver(Observer observer);
    // 通知订阅者
    void notifyObserver();
}
