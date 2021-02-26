package com.javarush.games.racer;

import com.javarush.games.racer.road.RoadManager;

public class PlayerCar extends GameObject {

    private static int playerCarHeight = ShapeMatrix.PLAYER.length;
    private Direction direction;

    public int speed = 1;

    public PlayerCar() {
        super(RacerGame.WIDTH / 2 + 2, RacerGame.HEIGHT - playerCarHeight - 1, ShapeMatrix.PLAYER);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void move() {
        if (direction == Direction.LEFT) this.x--;
        else if (direction == Direction.RIGHT) this.x++;
        else if (this.x < RoadManager.LEFT_BORDER) this.x = RoadManager.LEFT_BORDER;
        else if (this.x > RoadManager.RIGHT_BORDER - width) this.x = RoadManager.RIGHT_BORDER - width;
    }
}
