package dev.cutie.gamebox.store.action;

import dev.cutie.androidtoolbox.store.action.IStoreActions;
import dev.cutie.androidtoolbox.store.action.StoreAction;
import dev.cutie.gamebox.store.selector.PlayersSelector;

public class PlayersActions implements IStoreActions
{
    public static StoreAction addPlayer = new StoreAction("[Players] Add Player", PlayersSelector.class);
}
