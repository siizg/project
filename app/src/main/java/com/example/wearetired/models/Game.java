package com.example.wearetired.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Game {
    public String id;
    public String idPlayer1;
    public String idPlayer2;
    public int turn;
    public String status;
    public ArrayList<Integer> gameMap = new ArrayList<Integer>();
    public int amount;
    public int winner;

    public Game(String idPlayer1, String idPlayer2, int turn, String status, int amount, int winner) {
        this.id = idPlayer1 + "-" + idPlayer2;
        this.amount = amount;
        this.winner = winner;
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.turn = turn;
        this.status = status;
        for(int i = 0; i < 9; i++) {
            gameMap.add(0);
        }

    }
    public Game() {
        //
    }



    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", idPlayer1='" + idPlayer1 + '\'' +
                ", idPlayer2='" + idPlayer2 + '\'' +
                ", turn=" + turn +
                ", status='" + status + '\'' +
                ", gameMap=" + gameMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return turn == game.turn && Objects.equals(id, game.id) && Objects.equals(idPlayer1, game.idPlayer1) && Objects.equals(idPlayer2, game.idPlayer2) && Objects.equals(status, game.status) && Objects.equals(gameMap, game.gameMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPlayer1, idPlayer2, turn, status, gameMap);
    }
}
