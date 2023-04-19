package models;

import exception.IncorrectDataException;

public class Coordinates {
    private int x;
    private Double y; //Поле не может быть  null

    public Coordinates(int x, Double y) throws IncorrectDataException {
        if (x > 272 ) throw new IncorrectDataException("Неверные поля объекта MusicBand");
        else this.x = x;
        if (y == null && y < -183) throw new IncorrectDataException("Неверные поля объекта MusicBand");
        else this.y = y;
    }

    public Coordinates(Integer x) {
    }

    public int getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
