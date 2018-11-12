package actions;

import gui.Gui;

public class Application {

    int x, y, width, height, basey;
    String name, exename;
    double stunden = 0, stundencurrent = 0;
    boolean active, hover, running;

    int index;

    public Application(int y, String name, String exename) {
        this.y = y;
        x = 0;
        width = Gui.width / 2;
        height = 50;
        this.name = name;
        this.basey = y;
        this.exename = exename;
        active = false;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBasey() {
        return basey;
    }

    public void setBasey(int basey) {
        this.basey = basey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStunden() {
        return stunden;
    }

    public void setStunden(double stunden) {
        this.stunden = stunden;
    }

    public String getExename() {
        return exename;
    }

    public void setExename(String exename) {
        this.exename = exename;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean inside(int x, int y) {
        if (x > this.x && x <= this.x + width && y > this.y && y <= this.y + height) return true;

        return false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public double getStundencurrent() {
        return stundencurrent;
    }

    public void setStundencurrent(double stundencurrent) {
        this.stundencurrent = stundencurrent;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void add(double ammount){
        this.stunden += ammount;
    }

    public void addcurrent(double ammount){
        this.stundencurrent += ammount;
    }
}
