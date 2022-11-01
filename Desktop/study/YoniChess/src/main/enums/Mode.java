package main.enums;

public enum Mode {
    M("move"),
    E("eat");
    private String name;


    Mode(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
