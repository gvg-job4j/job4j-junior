package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Valeriy Gyrievskikh
 * @since 22.11.2018
 */
public class RectangleMove implements Runnable {
    /**
     * Перемещаемая фигура.
     */
    private final Rectangle rect;
    /**
     * Максимальный размер по Х.
     */
    private int maxX;
    /**
     * Максимальный размер по Y.
     */
    private int maxY;
    /**
     * Изменение текущей позиции по X.
     */
    private int deltaX = 5;
    /**
     * Изменение текущей позиции по Y.
     */
    private int deltaY = 5;

    /**
     * Конструктор, задаеет начальные значения.
     *
     * @param rect Перемещаемая фигура.
     * @param maxX Максимальный размер по Х.
     * @param maxY Максимальный размер по Y.
     */
    public RectangleMove(Rectangle rect, int maxX, int maxY) {
        this.rect = rect;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Метод выполняет изменение координат фигуры с течением времени.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                this.rect.setX(this.rect.getX() + deltaX);
                this.rect.setY(this.rect.getY() + deltaY);
                Thread.sleep(50);
                if (this.rect.getX() >= maxX || this.rect.getX() <= 0) {
                    deltaX = -deltaX;
                }
                if (this.rect.getY() >= maxY || this.rect.getY() <= 0) {
                    deltaY = -deltaY;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
