package me.threesixtyfour;

public enum Month {
    JANUARY((byte) 1),
    FEBRUARY((byte) 2),
    MARCH((byte) 3),
    APRIL((byte) 4),
    MAY((byte) 5),
    JUNE((byte) 6),
    JULY((byte) 7),
    AUGUST((byte) 8),
    SEPTEMBER((byte) 9),
    OCTOBER((byte) 10),
    NOVEMBER((byte) 11),
    DECEMBER((byte) 12);

    private byte monthIndex;

    Month(byte monthIndex) {
        this.monthIndex = monthIndex;
    }
    public byte getMonthIndex() {
        return monthIndex;
    }
    public static Month fromIndex( byte index) {
        if (index < 0 || index > Month.values().length) {
            return null;
        }

        for (Month m : Month.values()) {
            if (m.getMonthIndex() == index) {
                return m;
            }
        }

        return null;
    }
}
