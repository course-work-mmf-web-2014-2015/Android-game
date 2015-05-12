package com.bsu.mmf.web.course_work.gameworld;

/**
 * Created by DemonStore on 12.05.2015.
 */
public class MenuWorld {
    private boolean downBtn;
    private boolean enabledSound;
    private boolean enabledAccelerom;
    private boolean downExit;

    public MenuWorld() {
        downBtn = false;
        enabledSound = true;
        enabledAccelerom = true;
        downExit = false;
    }

    public boolean isDownBtn() {
        return downBtn;
    }

    public void setDownBtn(boolean downBtn) {
        this.downBtn = downBtn;
    }

    public boolean isEnabledSound() {
        return enabledSound;
    }

    public void setEnabledSound(boolean enabledSound) {
        this.enabledSound = enabledSound;
    }

    public boolean isEnabledAccelerom() {
        return enabledAccelerom;
    }

    public void setEnabledAccelerom(boolean enabledAccelerom) {
        this.enabledAccelerom = enabledAccelerom;
    }

    public boolean isDownExit() {
        return downExit;
    }

    public void setDownExit(boolean downExit) {
        this.downExit = downExit;
    }
}
