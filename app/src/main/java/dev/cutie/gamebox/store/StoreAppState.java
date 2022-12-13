package dev.cutie.gamebox.store;

import dev.cutie.androidtoolbox.store.model.IStoreState;
import dev.cutie.androidtoolbox.store.annotation.StoreField;
import dev.cutie.gamebox.model.Player;

import java.util.ArrayList;
import java.util.List;

public class StoreAppState extends IStoreState {
    @StoreField("playerList")
    public List<Player> playerList = new ArrayList<>();
}
