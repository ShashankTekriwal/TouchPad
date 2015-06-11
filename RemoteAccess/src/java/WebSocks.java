/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shashank
 */
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import java.io.IOException;
@ServerEndpoint("/WebSocks")
public class WebSocks {
    
    static{
        System.out.println("WEBSOCK STATIC BLOCK CALLED!");
        //System.setProperty("java.awt.headless", "false");
        System.out.println(java.awt.GraphicsEnvironment.isHeadless());
    }
    static boolean connected = false;
    Autobot bot = new Autobot();
    @OnOpen()
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection");
        connected = true;
        try {
            session.getBasicRemote().sendText("Connection Established to ShashankPC");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            //session.getBasicRemote().sendText(message);
            bot.handleMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
}
    }

    /**
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
        connected = false;
    }
}
