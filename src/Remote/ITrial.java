package Remote;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Graphics.Circle;
import Graphics.Rectangle;
import Graphics.LinePoint;
import Graphics.TextAnywhere;

public interface ITrial extends Remote {
    public double add(double a, double b) throws RemoteException;
    public  ArrayList<Circle> sendCircles() throws RemoteException;
    public ArrayList<Rectangle> sendRects() throws RemoteException;
    public ArrayList<LinePoint> sendLines() throws RemoteException;
    public ArrayList<TextAnywhere> sendTexts() throws RemoteException;
    public void setServerCircles(ArrayList<Circle> circles) throws RemoteException;
    public void setServerRectangles(ArrayList<Rectangle> rectangles) throws RemoteException;
    public void setServerLines(ArrayList<LinePoint> lines) throws RemoteException;
    public void sendMessageServer(String message) throws RemoteException;
    public String getServerMessage() throws RemoteException;
    public boolean getServerButton() throws RemoteException;
    public void setServerButton(boolean b) throws RemoteException;
    public void addMessage(String message) throws RemoteException;//Needs update
    public String createId() throws RemoteException;
    public void message(String message) throws RemoteException;
    public String setClientMessages() throws RemoteException;

}
