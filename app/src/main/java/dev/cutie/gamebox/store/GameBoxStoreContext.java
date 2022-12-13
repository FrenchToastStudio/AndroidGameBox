package dev.cutie.gamebox.store;

import dev.cutie.androidtoolbox.store.StoreBuilder;
import dev.cutie.androidtoolbox.store.StoreContext;
import dev.cutie.gamebox.store.effects.PlayersEffects;
import dev.cutie.gamebox.store.reducers.PlayersReducer;

public class GameBoxStoreContext extends StoreContext
{

    // Declare Reducers
    public PlayersReducer playersReducer;
    public PlayersEffects playersEffect;

    public StoreAppState appState;

    public GameBoxStoreContext()
    {
    }
    @Override
    public void OnStoreBuilding(StoreBuilder storeBuilder)
    {
         super.OnStoreBuilding(storeBuilder);

         storeBuilder.setContext(this);
         storeBuilder.isAndroid();
    }
}
