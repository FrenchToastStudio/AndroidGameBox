package dev.cutie.gamebox.controller;

import dev.cutie.androidtoolbox.bootstrap.AppBootStrap;
import dev.cutie.androidtoolbox.store.facade.IStoreFacade;
import dev.cutie.gamebox.commands.executable.IAddPlayerExecutable;
import dev.cutie.gamebox.model.Player;
import dev.cutie.gamebox.store.AppStore;
import dev.cutie.gamebox.store.action.PlayersActions;
import dev.cutie.gamebox.store.selector.PlayersSelector;
import dev.cutie.gamebox.view.CreateLobbyFragment;
import dev.cutie.gamebox.view.interace.ICreateLobbyView;

import java.util.ArrayList;
import java.util.List;

public class CreateLobbyController implements IAddPlayerExecutable
{

    private AppStore store;
    private ICreateLobbyView view;

    public CreateLobbyController(CreateLobbyFragment view)
    {
        this.view = view;
        this.store = AppBootStrap.getInstance().getService(IStoreFacade.class);
        this.store.select(PlayersSelector.class).subscribe(this::setPlayers);
    }
    @Override
    public void addPlayer()
    {
        this.store.action(PlayersActions.addPlayer);
    }
    public void setView(ICreateLobbyView s)
    {
        this.view = view;
    }
    private void setPlayers(List<Player> players)
    {
        if(players != null) {
            List<String> strings = new ArrayList<>();
            for (Player player : players) {
                strings.add(player.getName());
            }
            view.setUsers(strings);
        }
    }
}
