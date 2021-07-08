package Server;

import javax.swing.*;
//import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Remote.ITrial;
import Graphics.*;

public class ServerImp extends UnicastRemoteObject implements ITrial{
    public ServerImp() throws RemoteException {
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        return a+b;
    }

    @Override
    public ArrayList<Circle> sendCircles() throws RemoteException {

        return Whiteboard.getMyBoard().components.getCirclePoints();

    }

    @Override
    public ArrayList<Rectangle> sendRects() throws RemoteException {
        return Whiteboard.getMyBoard().components.getRectangles();
    }

    @Override
    public ArrayList<LinePoint> sendLines() throws RemoteException {
        return Whiteboard.getMyBoard().components.getLine2DS();
    }

    @Override
    public ArrayList<TextAnywhere> sendTexts() throws RemoteException {
        return Whiteboard.getMyBoard().components.getTextAreas();
    }

    @Override
    public void setServerCircles(ArrayList<Circle> circles) throws RemoteException {
        Whiteboard.getMyBoard().components.setCirclePoints(circles);
    }

    @Override
    public void setServerRectangles(ArrayList<Rectangle> rectangles) throws RemoteException {
        Whiteboard.getMyBoard().components.setRectangles(rectangles);
    }

    @Override
    public void setServerLines(ArrayList<LinePoint> lines) throws RemoteException {
        Whiteboard.getMyBoard().components.setLine2DS(lines);
    }

    @Override
    public void sendMessageServer(String message) throws RemoteException {
        Whiteboard.getMyBoard().components.setMessageBox(message);
    }

    @Override
    public String getServerMessage() throws RemoteException {
        return "Server " + Whiteboard.getMyBoard().components.getChatText();
    }

    @Override
    public boolean getServerButton() throws RemoteException {
        return Whiteboard.getMyBoard().components.getCheckSubmit();
    }

    @Override
    public void setServerButton(boolean  b) throws RemoteException {
        Whiteboard.getMyBoard().components.setCheckSubmit(b);
    }

    @Override
    public void addMessage(String message) throws RemoteException {
        Whiteboard.getMessages().add(message);
    }

    @Override
    public String createId() throws RemoteException {
        String id = "Client"+Integer.toString(Whiteboard.getClientIDS().size());
        Whiteboard.getClientIDS().add(id);
        Whiteboard.getMessages().add("No");
        //System.out.println(Whiteboard.getClientIDS().get(0));
        return id;
    }

    @Override
    public void message(String message) throws RemoteException {
        Whiteboard.setClientMessage(message);

    }

    @Override
    public String setClientMessages() throws RemoteException {
        return Whiteboard.getMyBoard().components.getMessageBox().getText();
    }


}
