package view.resources;

import java.awt.*;

public enum Palette {
    BLUE1("#E3F2FD"),
    BLUE2("#BBDEFB"),
    BLUE3("#90CAF9"),
    BLUE4("#64B5F6"),
    BLUE5("#42A5F5"),
    BLUE6("#2196F3"),
    BLUE7("#1E88E5"),
    BLUE8("#1976D2"),
    BLUE9("#1565C0"),
    BLUE10("#0D47A1");

    private final String color;

    Palette(String color){
        this.color = color;
    }

    public Color color(){
        return Color.decode(color);
    }
}
