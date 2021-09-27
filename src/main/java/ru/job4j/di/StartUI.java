package ru.job4j.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StartUI {
    private Store store;
    @Autowired
    private ConsoleInput input;

    @Autowired
    public void setStore(Store store) {
        this.store = store;
    }

    public void add() {
        store.add(input.read());
    }

    public void add(String str) {
        store.add(str);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
