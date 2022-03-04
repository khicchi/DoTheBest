package net.kicchi.functionality;

import net.kicchi.enums.EColumn;

public interface TableFunctionality {
    int getMaxValueOfTheColumn(EColumn targetColumn);

    int getMinValueOfTheColumn(EColumn targetColumn);

    String getPlayerName();
}
