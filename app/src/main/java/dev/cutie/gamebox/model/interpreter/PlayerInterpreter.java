package dev.cutie.gamebox.model.interpreter;

import dev.cutie.gamebox.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerInterpreter
{
    public List<Player> players;

    public PlayerInterpreter(List<Player> players)
    {
        this.players =players;
    }

    public void addPlayer()
    {
        if(players == null)
        {
            players = new ArrayList<>();
        }
        players.add(new Player("Test"));
    }

}
