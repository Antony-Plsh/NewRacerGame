package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {

    public final static int WIDTH = 64;
    public final static int HEIGHT = 64;
    public final static int CENTER_X = WIDTH / 2;
    public final static int ROADSIDE_WIDTH = 14;

    private RoadMarking roadMarking;
    private PlayerCar player;

    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();

    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        player.draw(this);
    }

    private void drawField() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (x == CENTER_X)  setCellColor(CENTER_X, y, Color.WHITE);
                else if (x >= ROADSIDE_WIDTH && x < (WIDTH - ROADSIDE_WIDTH)) setCellColor(x, y, Color.DIMGRAY);
                else setCellColor(x, y, Color.GREEN);
            }
        }
    }

    public void setCellColor(int x, int y, Color color) {
        if (x < 0 || x > WIDTH - 1 || y < 0 || y > HEIGHT - 1) return;
        super.setCellColor(x, y, color);
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        player.move();
    }

    @Override
    public void onTurn(int step) {
        moveAll();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.RIGHT) player.setDirection(Direction.RIGHT);
        else if (key == Key.LEFT) player.setDirection(Direction.LEFT);
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.RIGHT && player.getDirection() == Direction.RIGHT) player.setDirection(Direction.NONE);
        else if (key == Key.LEFT && player.getDirection() == Direction.LEFT) player.setDirection(Direction.NONE);
    }
}

