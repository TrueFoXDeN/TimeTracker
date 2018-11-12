package actions;

import gui.Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getX() <= Gui.width / 2){
            for(int i = 0; i< Gui.apps.size(); i++){
                if(Gui.apps.get(i).inside(e.getX(),e.getY())){
                    Gui.apps.get(i).setActive(true);
                }else{
                    Gui.apps.get(i).setActive(false);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
