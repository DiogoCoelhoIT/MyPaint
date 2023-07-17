public enum Movement {
    MOVE_UP("MOVE_UP"),
    MOVE_LEFT("MOVE_LEFT"),
    MOVE_RIGHT("MOVE_RIGHT"),
    MOVE_DOWN("MOVE_DOWN"),
    PRESS_SPACE("PRESS_SPACE"),
    RELEASE_SPACE("RELEASE_SPACE"),
    PRESS_0("PRESS_0"),
    PRESS_1("PRESS_1"),
    PRESS_2("PRESS_2"),
    PRESS_3("PRESS_3"),
    PRESS_4("PRESS_4"),
    PRESS_5("PRESS_5"),
    PRESS_6("PRESS_6");


    private String name;
    Movement(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
