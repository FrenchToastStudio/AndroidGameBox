package dev.cutie.androidtoolbox.store;

import dev.cutie.androidtoolbox.store.action.StoreAction;
import dev.cutie.androidtoolbox.store.facade.IStoreFacade;
import dev.cutie.androidtoolbox.store.model.Subject;

public abstract class StoreFacade implements IStoreFacade
{
    public StoreFacade(StoreContext context)
    {
        StoreBuilder builder = new StoreBuilder();
        builder.setContext(context);
        context.OnStoreBuilding(builder);
        builder.build();
    }
    public Subject select(Class selector)
    {
        return DevStore.getInstance().select(selector);
    }

    public void action(StoreAction action)
    {
        DevStore.getInstance().execute(action);
    }
}
