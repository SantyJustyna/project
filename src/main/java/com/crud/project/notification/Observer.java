package com.crud.project.notification;

import com.crud.project.domain.Order;

public interface Observer {
    void update(Order order);
}
