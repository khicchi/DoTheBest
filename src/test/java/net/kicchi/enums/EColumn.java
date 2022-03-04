package net.kicchi.enums;

import lombok.Getter;

@Getter
public enum EColumn {

    YDS("Yds", 2),
    ATT("Att", 2);

    private String columnName;
    private int index;

    private EColumn(String columnName, int index){
        this.columnName = columnName;
        this.index = index;
    }
}
