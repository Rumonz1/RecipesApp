package pro.sky.recipesapplication.model;

public class Ingredient {
    private String title;
    private int number;
    private String measureUnit;

    public Ingredient(String title, int number, String measureUnit) {
        this.title = title;
        this.number = number;
        this.measureUnit = measureUnit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}
