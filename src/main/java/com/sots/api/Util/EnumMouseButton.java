package com.sots.api.util;

public enum EnumMouseButton {
    RIGHTCLICK(0),
    MIDDELCLICK(1),
    LEFTCLICK(2),
    OTHERCLICK(-1);

    EnumMouseButton(int buttonID) {
        this.buttonID = buttonID;
    }

    private int buttonID;

    public int getButtonID() {
        return buttonID;
    }

    public EnumMouseButton setButtonID(int buttonID) {
        this.buttonID = buttonID;
        return this;
    }

    public boolean isRightClick(){
        return this == RIGHTCLICK;
    }

    public boolean isMiddelClick(){
        return this == MIDDELCLICK;
    }

    public boolean isLeftClick(){
        return this == LEFTCLICK;
    }

    public static EnumMouseButton getEnumButtonFromID(int mouseButton){
        switch (mouseButton){
            case 0:
                return EnumMouseButton.RIGHTCLICK;
            case 1:
                return EnumMouseButton.MIDDELCLICK;
            case 3:
                return EnumMouseButton.LEFTCLICK;
            default:
                return EnumMouseButton.OTHERCLICK.setButtonID(mouseButton);
        }
    }

}
