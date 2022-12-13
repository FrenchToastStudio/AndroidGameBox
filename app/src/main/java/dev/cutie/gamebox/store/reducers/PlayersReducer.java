package dev.cutie.gamebox.store.reducers;

import dev.cutie.androidtoolbox.store.reducer.StoreReducer;
import dev.cutie.gamebox.model.Player;
import dev.cutie.gamebox.store.action.PlayersActions;
import dev.cutie.gamebox.store.selector.PlayersSelector;

import java.util.ArrayList;

public class PlayersReducer extends StoreReducer
{
    public PlayersReducer() {
        super("playerList", "[Players] addPlayer", PlayersSelector.class, new ArrayList<Player>()
        {
            {
                add(new Player("test"));
            }
        });
    }
}
