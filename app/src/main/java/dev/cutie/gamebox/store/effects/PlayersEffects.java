package dev.cutie.gamebox.store.effects;

import dev.cutie.androidtoolbox.store.effect.StoreEffect;
import dev.cutie.androidtoolbox.store.effect.StoreEffects;
import dev.cutie.androidtoolbox.store.model.IStoreState;
import dev.cutie.gamebox.model.Player;
import dev.cutie.gamebox.model.interpreter.PlayerInterpreter;
import dev.cutie.gamebox.store.StoreAppState;

import java.util.ArrayList;
import java.util.List;

public class PlayersEffects  extends StoreEffects
{

    public PlayersEffects() {
        super();

        List<StoreEffect> effectList = new ArrayList<>();

        StoreEffect addPlayer = new StoreEffect("[Players] Add Player") {
            @Override
            public void launchEffect(IStoreState appState) {
                StoreAppState state = (StoreAppState) appState;
                PlayerInterpreter playerInterpreter = new PlayerInterpreter(state.playerList);
                playerInterpreter.addPlayer();
            }
        };

        effectList.add(addPlayer);


        this.setStoreEffects(effectList);
    }

    private void onAddPlayersAction()
    {

    }
}
