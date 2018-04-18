package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public class Window extends JFrame implements MouseListener, MouseMotionListener {
    private boolean mouseExcited;
    private boolean mouseMovedNearlyY;
    private boolean mouseMovedNearlyX;
    private boolean mousePressed;
    private boolean mousePressedcolor;
    private  boolean mouseDragger;
    private Color brushColor = Color.BLACK;
    private Seek seek;
    private int brushwidth = 1;
    private LinkedList <Point> coor = new LinkedList<>();//null
    private int mouseY;
    private int mouseX;
    private LinkedList <CustomizeLinkedList<Point>> coorLine = new LinkedList<>();
    private CustomizeLinkedList <Point> currentLine;

    public Window() {
        setSize(420,500);
        System.out.println("Constructor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(this);
        addMouseMotionListener(this);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(Color.BLACK);
        if (mousePressed){
            g.setColor(Color.WHITE);
        }
        g.fillRect(15,40,25,25);
        if (mousePressedcolor){
            g.setColor(Color.WHITE);
        }
        g.setColor(new Color(11,249,10));
        g.fillRect(20,80,15,15);
        g.setColor(new Color(213,55,128));
        g.fillRect(20,110,15,15);
        g.setColor(new Color(56, 2, 249));
        g.setColor(Color.CYAN);
        drawingSeekBar(g);
        drawingLineNearly(g);
        drawingAfterExited(g);
        drawingPoints(g);
        drawingLine(g);
    }
    private void drawingSeekBar(Graphics g) {
        if(seek == null){
            seek = new Seek();
            seek.setCoordinatesOfStartX(30);
            seek.setCoordinatesOfStartY(440);
            seek.setWidthVerticalLine(10);
            seek.setLengthOfAllSeekBar(350);
        }
        for (int i = seek.getCoordinatesOfStartX() + 14; i < seek.getCoordinatesOfStartX() + seek.getLengthOfAllSeekBar(); i += seek.getLengthOfRedPointLine()){
            g.setColor(Color.RED);
            g.drawLine(i,seek.getCoordinatesOfStartY() - 7,i,seek.getCoordinatesOfStartY() + 7);
        }
        g.setColor(Color.CYAN);
        g.drawLine(seek.getCoordinatesOfStartX(),seek.getCoordinatesOfStartY(),seek.getCoordinatesOfStartX() + seek.getLengthOfAllSeekBar(),seek.getCoordinatesOfStartY());
        g.setColor(Color.blue);
        g.drawLine(seek.getCoordinatesOfStartX(),seek.getCoordinatesOfStartY() - seek.getWidthVerticalLine(),seek.getCoordinatesOfStartX(),seek.getCoordinatesOfStartY() + seek.getWidthVerticalLine());
        g.drawLine(seek.getCoordinatesOfStartX() + seek.getLengthOfAllSeekBar(),seek.getCoordinatesOfStartY() - seek.getWidthVerticalLine(),seek.getCoordinatesOfStartX() + seek.getLengthOfAllSeekBar(),seek.getCoordinatesOfStartY() + seek.getWidthVerticalLine());
        g.setColor(Color.ORANGE);
        g.fillOval(seek.getForCenterX(),seek.getForCenterY(),seek.gethWOfSeek() ,seek.gethWOfSeek() );
    }

    private void drawingLine(Graphics g) {
        //g.setColor(brushColor);//BLACK
        for (CustomizeLinkedList<Point> linkedList: coorLine) {
            g.setColor(linkedList.getColor());
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(linkedList.getWidth()));
            for (int i = 0; i < linkedList.size() - 1; i++) {

                g2.drawLine(linkedList.get(i).x, linkedList.get(i).y, linkedList.get(i + 1).x, linkedList.get(i + 1).y);

            }
        }
    }

    private void drawingLineNearly(Graphics g) {
        for (int i = 0; i < coor.size(); i++) {
            if (mouseMovedNearlyX) {
                if (coor.get(i).y - 5 < mouseY && mouseY < coor.get(i).y + 5) {
                    g.drawLine(0, coor.get(i).y, getWidth(), coor.get(i).y);
                }
            }
            if (mouseMovedNearlyY) {
                if (coor.get(i).x - 5 < mouseX && mouseX < coor.get(i).x + 5) {
                    g.drawLine(coor.get(i).x, 0, coor.get(i).x, getHeight());
                }
            }
        }
    }

    private void drawingAfterExited(Graphics g) {
        if (mouseExcited) {

            for (int i = 0; i < coor.size() - 1; i++) {
                g.drawLine(coor.get(i).x, coor.get(i).y, coor.get(i + 1).x, coor.get(i + 1).y);
            }
            g.drawLine(coor.getFirst().x,coor.getFirst().y,coor.getLast().x,coor.getLast().y);
        }
    }

    private void drawingPoints(Graphics g) {
        for (Point point:coor) {
           g.fillRect(point.x,point.y,2,2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {//полноценный клик
        if (e.getY() > 40 && e.getY() < 65 && e.getX() > 15 && e.getX() < 40) {//clear rect
            System.out.println("in square");
            coor.clear();
            coorLine.clear();
            repaint();
        }else if (e.getY() > 80 && e.getY() < 95 && e.getX() > 20 && e.getX() < 35 ){//green rect
            brushColor = Color.GREEN;
        }else if (e.getY() > 110 && e.getY() < 125 && e.getX() > 20 && e.getX() < 35){
            brushColor = Color.MAGENTA;
        }
        else {
            coor.add(e.getPoint());

            System.out.println("new string");
            repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {//нажали
        System.out.println("mousePressed");
        if (e.getY() > 40 && e.getY() < 65 && e.getX() > 15 && e.getX() < 40){
            mousePressed = true;
            repaint();
        } else if (e.getY() > 80 && e.getY() < 95 && e.getX() > 20 && e.getX() < 35 ){
            mousePressedcolor = true;
            repaint();
        } else if (e.getY() > 110 && e.getY() < 125 && e.getX() > 20 && e.getX() < 35){
            mousePressedcolor = true;
            repaint();
        } else if (e.getY() > seek.getForCenterY() && e.getY() < seek.getForCenterY() + seek.gethWOfSeek() && e.getX() > seek.getForCenterX() && e.getX() < seek.getForCenterX() + seek.gethWOfSeek()){
            mouseDragger = true;
        } else {
            currentLine = new CustomizeLinkedList<>();
            currentLine.setColor(brushColor);
            currentLine.setWidth(brushwidth);
            coorLine.add( currentLine);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {//отпустили
        System.out.println("mouseReleased");
        if (e.getY() > 40 && e.getY() < 65 && e.getX() > 15 && e.getX() < 40) {
            mousePressed = false;
            repaint();
        } else if (e.getY() > 80 && e.getY() < 95 && e.getX() > 20 && e.getX() < 35 ){
            mousePressedcolor = false;
            repaint();
        } else if (e.getY() > 110 && e.getY() < 125 && e.getX() > 20 && e.getX() < 35){
            mousePressedcolor = false;
            repaint();
        } else if (e.getY() > seek.getForCenterY() && e.getY() < seek.getForCenterY() + seek.gethWOfSeek() && e.getX() > seek.getForCenterX() && e.getX() < seek.getForCenterX() + seek.gethWOfSeek()){
           mouseDragger = false;
           repaint();
    } else {
            mouseDragger = false;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {//возврате в окно
        mouseExcited = false;
        repaint();
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {//при выходе мыши из окна
        if (coor.size() > 1){
            mouseExcited = true;
            repaint();
        }
//        brushwidth ++;
        System.out.println("mouseExited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    //System.out.println("mouse Dragged "+ "x = " + e.getX() + "y = " + e.getY());
        if (mouseDragger){
            brushwidth = e.getX()/seek.getLengthOfLine();
            if (e.getX() < seek.getCoordinatesOfStartX()){
                seek.setX(seek.getCoordinatesOfStartX());
            }
            else if (e.getX() > seek.getCoordinatesOfStartX() + seek.getLengthOfAllSeekBar()){
                seek.setX(seek.getCoordinatesOfStartX() + seek.getLengthOfAllSeekBar());
            } else {
                float tmp = e.getX()/14;
                Math.round(tmp);
                seek.setX(Math.round(tmp) * 14);
            }
        } else{
            if(currentLine!=null)
                currentLine.add(e.getPoint());
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {//мув
        for (int i = 0; i < coor.size(); i++) {
            if (coor.get(i).y - 5 < e.getY() && e.getY() < coor.get(i).y + 5) {
                mouseY = e.getY();
                mouseMovedNearlyX = true;
                repaint();
                break;
            }else { if (mouseMovedNearlyX) {
                    mouseMovedNearlyX = false;
                    repaint();
                }
            }

            if (coor.get(i).x - 5 < e.getX() && e.getX() < coor.get(i).x + 5) {
                mouseX = e.getX();
                mouseMovedNearlyY = true;
                repaint();
                break;
            } else {
                if (mouseMovedNearlyY) {
                    mouseMovedNearlyY = false;
                    repaint();
                }
            }

        }
    }
}