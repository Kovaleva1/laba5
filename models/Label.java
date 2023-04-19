package models;

import exception.IncorrectDataException;

public class Label{
    private int sales;
    public Label(int sales) throws IncorrectDataException {
        if(this.sales <0 )throw new IncorrectDataException("Неверные поля объекта MusicBand");
        else this.sales= this.sales;
    }
    public Integer getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

}
