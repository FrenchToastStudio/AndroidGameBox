package dev.cutie.androidtoolbox.store.facade;

import dev.cutie.androidtoolbox.store.model.Subject;
import dev.cutie.androidtoolbox.store.model.IStoreState;

public interface IStoreFacade
{
    Subject select(Class selector);
}
