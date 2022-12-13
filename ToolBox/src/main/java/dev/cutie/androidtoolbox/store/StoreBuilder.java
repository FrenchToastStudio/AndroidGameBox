package dev.cutie.androidtoolbox.store;

import dev.cutie.androidtoolbox.store.action.StoreAction;
import dev.cutie.androidtoolbox.store.effect.StoreEffect;
import dev.cutie.androidtoolbox.store.effect.StoreEffects;
import dev.cutie.androidtoolbox.store.model.IStoreState;
import dev.cutie.androidtoolbox.store.reducer.StoreReducer;
import dev.cutie.androidtoolbox.store.stategy.AndroidCacheStoreItemStrategy;
import dev.cutie.androidtoolbox.store.stategy.AndroidRamStoreItemStrategy;
import dev.cutie.androidtoolbox.store.stategy.IStoreItemStrategy;
import dev.cutie.androidtoolbox.tool.ObjectToolBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreBuilder
{

    private IStoreItemStrategy storeItemStrategy;
    private StoreContext context;
    private boolean isAndroid;
    private boolean useCache;
    public StoreBuilder()
    {

    }

    public StoreBuilder isAndroid()
    {
        isAndroid = true;
        return this;
    }

    public StoreBuilder setContext(StoreContext context)
    {
        this.context = context;

        return this;
    }

    public DevStore build()
    {
        setStoreItemStrategy();

        verify();
        return new DevStore(this);
    }

    public IStoreItemStrategy getStoreItemStrategy()
    {
        return this.storeItemStrategy;
    }

    public IStoreState getStoreState() {
        ObjectToolBox<IStoreState> actionObjectToolBox = new ObjectToolBox<>();
        return actionObjectToolBox.getFieldOfType(IStoreState.class,this.context,true);
    }

    public List<StoreAction> getAction() {
        ObjectToolBox<StoreAction> actionObjectToolBox = new ObjectToolBox<>();
        return actionObjectToolBox.getAllFieldOfType(StoreAction.class, this.context, true);
    }

    public List<StoreReducer> getReducer() {
        ObjectToolBox<StoreReducer> actionObjectToolBox = new ObjectToolBox<>();
        return actionObjectToolBox.getAllFieldOfType(StoreReducer.class, this.context, true);
    }
    public List<StoreEffects> getEffects() {
        ObjectToolBox<StoreEffects> actionObjectToolBox = new ObjectToolBox<>();
        return actionObjectToolBox.getAllFieldOfType(StoreEffects.class, this.context, true);
    }
    private void setStoreItemStrategy()
    {
        IStoreItemStrategy strategy = null;
        if(this.isAndroid)
        {
            if(this.useCache)
            {
                strategy = new AndroidCacheStoreItemStrategy();
            }
            else
            {
                strategy = new AndroidRamStoreItemStrategy();
            }
        }

        this.storeItemStrategy = strategy;
    }

    // TODO : Handle exception better
    private void  verify()
    {
        List<String> exceptions = new ArrayList<>();
        if(this.storeItemStrategy == null)
        {
            exceptions.add("Context");
        }

        if(exceptions.size() >0)
            launchException(exceptions);
    }
    // TODO : Make this as a custom exception
    private void launchException(List<String> exceptions)
    {
        String text = "The following items have not been set in the builder{";
        for (int i = 0; i < exceptions.size(); i++) {
            text += exceptions.get(i);
            if (exceptions.size()-1 != i)
                text+=",";
        }
        text+="}";
        throw  new RuntimeException(text);
    }



}
