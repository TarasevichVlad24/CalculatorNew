package entity;

import java.util.Objects;

public class Operation {
    private int id = 1;
    private double num1;
    private double num2;
    private double result;
    private String type;
    private User owner;


    public Operation(double num1, double num2, String type,User owner) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", type='" + type +
                ", result=" + result +
                ", id='" + getId() +'\'' +
                '}';
    }
}
