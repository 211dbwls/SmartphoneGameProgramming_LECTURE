package com.example.samplegame;

public class MainGame {
    private static MainGame singleton;
    public static MainGame getInstance() {
        if(singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }
}
