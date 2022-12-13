package dev.cutie.androidtoolbox.store.stategy;

import dev.cutie.androidtoolbox.store.stategy.model.StoringSecurity;

public interface IStoreItemStrategy
{

    void store(String name, Object obj);
    void store(String name, Object obj, StoringSecurity security);
}
