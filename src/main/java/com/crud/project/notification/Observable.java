package com.crud.project.notification;

import com.crud.project.domain.Order;

public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Order order);
}
