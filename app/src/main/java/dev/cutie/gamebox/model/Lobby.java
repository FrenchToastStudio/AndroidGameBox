package dev.cutie.gamebox.model;

import java.util.ArrayList;
import java.util.List;

public class Lobby
{
    private String code;
    private List<Player> players;

    public Lobby(String code) {
        this.code = code;
        this.players = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
