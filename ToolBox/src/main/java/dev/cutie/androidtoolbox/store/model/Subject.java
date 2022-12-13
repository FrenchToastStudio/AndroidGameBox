package dev.cutie.androidtoolbox.store.model;

import dev.cutie.androidtoolbox.store.model.StoreSubscription;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Subject {

    private List<StoreObserverSubscription>  subjectSubscription;

    public <T> void subscribe(StoreObserverSubscription<T> subscription)
    {
        if(subjectSubscription == null)
        {
            subjectSubscription = new ArrayList<>();
        }
        subjectSubscription.add(subscription);

        subscription.accept((T)getState());
    }

    public void notifyObserverOfChange()
    {
        if(subjectSubscription == null)
            return;
        for (StoreObserverSubscription subscription: subjectSubscription) {
            subscription.accept(getState());
        }
    }

    public abstract Object getState();
}
