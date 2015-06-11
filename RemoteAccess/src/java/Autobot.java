/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shashank
 */
import java.awt.*;
import java.awt.Robot;
import java.awt.MouseInfo;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import messages.Constants;

public class Autobot {
    
    static{
        System.out.println("AUTOBOT STATIC BLOCK CALLED!!");
        //System.setProperty("java.awt.headless", "false");
        System.out.println(java.awt.GraphicsEnvironment.isHeadless());
    }
    
    private Robot robot;
    int lastXpos;
    int lastYpos;
    int lastScrollY;
    
    
    public Autobot(){
        try{
            System.setProperty("java.awt.headless", "false");
            robot = new Robot();
        }catch(Exception e){
            System.out.println("Cannot create Robot!");
            e.printStackTrace();
        }
        lastXpos = 0;
        lastYpos = 0;
        lastScrollY = 0;
    }

    void handleMessage(String message) {
        if(0==message.length()){
            return;
        }
        char ch = message.charAt(0);
        switch(ch){
            case 'M' :
                handleMouseMessage(message);
                break;
            case 'K' :
                handleKeyboardMessage(message);
                break;
            case 'N' :
                break;
            default :
                System.out.println("Invalid Message "+message);
        }
    }

    private void handleMouseMessage(String message) {
        char ch = message.charAt(1);
        switch (ch) {
            case Constants.LEFTCLICK :
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                break;
            case Constants.RIGHTCLICK :
                robot.mousePress(InputEvent.BUTTON3_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_MASK);
                break;
            case Constants.LEFTMOUSEDOWN :
                robot.mousePress(InputEvent.BUTTON1_MASK);
                break;
            case Constants.LEFTMOUSEUP :
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                break;
            case Constants.RIGHTMOUSEDOWN :
                robot.mousePress(InputEvent.BUTTON3_MASK);
                break;
            case Constants.RIGHTMOUSEUP :
                robot.mouseRelease(InputEvent.BUTTON3_MASK);
                break;
            case Constants.SCROLLUP :
                robot.mouseWheel(-1);
                break;
            case Constants.SCROLLDOWN :
                robot.mouseWheel(1);
                break;
            case Constants.MOUSEMOVE :
                moveMouseHandle(message);
                break;
            default :
                System.out.println("Invalid Mouse Handle message: "+message);
                break;
        }
    }

    private void handleKeyboardMessage(String message) {
        
    }

    private void moveMouseHandle(String message) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        int currentX = p.x;
        int currentY = p.y;
        //p.translate(currentY, currentY);
        message = message.substring(2);
        String newX = message.substring(0,message.indexOf("*"));
        String newY = message.substring(message.indexOf("*")+1);
        if(newX.indexOf('.') != -1){
            newX = newX.substring(0,newX.indexOf('.'));
        }
        if(newY.indexOf('.') != -1){
            newY = newY.substring(0,newY.indexOf('.'));
        }
        int dX = 4*Integer.parseInt(newX);
        int dY = 4*Integer.parseInt(newY);
        //System.out.println(newX+","+newY);
        p.translate(dX, dY);
        System.out.println("("+currentX+","+currentY+")->"+"("+p.x+","+p.y+")");
        robot.mouseMove(p.x, p.y);
    }
    
}
