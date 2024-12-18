package data.menu;

public enum HeaderMenuData {
    LESSON("Обучение");

    private String name;

    HeaderMenuData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
