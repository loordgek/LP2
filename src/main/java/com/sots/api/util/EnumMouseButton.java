package com.sots.api.util;

public enum EnumMouseButton {
    RIGHTCLICK(0),
    MIDDlECLICK(1),
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
        if (this != EnumMouseButton.OTHERCLICK)
            throw new IllegalStateException("you can only set the mousebutton for OTHERCLICK");
        this.buttonID = buttonID;
        return this;
    }

    public boolean isRightClick(){
        return this == RIGHTCLICK;
    }

    public boolean isMiddleClick(){
        return this == MIDDlECLICK;
    }

    public boolean isLeftClick(){
        return this == LEFTCLICK;
    }

    public static EnumMouseButton getButtonFromID(int mouseButton){
        switch (mouseButton){
            case 0:
                return EnumMouseButton.RIGHTCLICK;
            case 1:
                return EnumMouseButton.MIDDlECLICK;
            case 3:
                return EnumMouseButton.LEFTCLICK;
            default:
                return EnumMouseButton.OTHERCLICK.setButtonID(mouseButton);
        }
    }
}
