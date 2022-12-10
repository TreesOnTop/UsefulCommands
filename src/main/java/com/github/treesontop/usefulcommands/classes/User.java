package com.github.treesontop.usefulcommands.classes;

import java.util.List;

public class User {
    private boolean vanished = false;
    private boolean god = false;
    private boolean frozen = false;
    private long muted = 0;
    private List<Home> homes = null;

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(boolean vanished) {
        this.vanished = vanished;
    }

    public boolean isGod() {
        return god;
    }

    public void setGod(boolean god) {
        this.god = god;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public long getMuted() {
        return muted;
    }

    public void setMuted(long muted) {
        this.muted = muted;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void addHome(Home home) {
        homes.add(home);
    }

    public void removeHome(Home home) {
        homes.remove(home);
    }
}
