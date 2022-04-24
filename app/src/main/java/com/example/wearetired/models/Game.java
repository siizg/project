package com.example.wearetired.models;

import java.util.Arrays;
import java.util.Objects;

public class Game {
    String id;
    String idPlayer1;
    String idPlayer2;
    int turn;
    String status;
    int[][] gameMap = new int[3][3];

    public Game(String id, String idPlayer1, String idPlayer2, int turn, String status) {
        this.id = id;
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.turn = turn;
        this.status = status;
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameMap[i][j] = 0;
            }
        }
        //this.gameMap = gameMap;
    }
    public Game() {
        //
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return turn == game.turn && Objects.equals(id, game.id) && Objects.equals(idPlayer1, game.idPlayer1) && Objects.equals(idPlayer2, game.idPlayer2) && Objects.equals(status, game.status) && Arrays.equals(gameMap, game.gameMap);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, idPlayer1, idPlayer2, turn, status);
        result = 31 * result + Arrays.hashCode(gameMap);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", idPlayer1='" + idPlayer1 + '\'' +
                ", idPlayer2='" + idPlayer2 + '\'' +
                ", turn=" + turn +
                ", status='" + status + '\'' +
                ", gameMap=" + Arrays.toString(gameMap) +
                '}';
    }
}
