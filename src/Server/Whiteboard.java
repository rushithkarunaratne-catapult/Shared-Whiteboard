package Server;

import Remote.ITrial;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Whiteboard extends UnicastRemoteObject implements Runnable{
    private static MyBoard myBoard;
    static ITrial iTrial;
    private static String serverMessage = "Hey";
    private static String clientMessage;
    private  static  ArrayList<String> messages = new ArrayList<>();
    private static ArrayList<String> clientIDS = new ArrayList<>();
    private static  String prevMessage = "None";
    private static Management management;
    //static IClientSide iClientSide;
    protected Whiteboard() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
        myBoard = new MyBoard();
        management = new Management();
        Thread thread = new Thread(new Whiteboard());
        thread.start();
        iTrial = new ServerImp();
        //messages.add("None");

        Registry registry = LocateRegistry.getRegistry();
        registry.bind("trial",iTrial);
        System.out.println("hmm");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println("afafadfva");
                String out = "Server -->" + "\n";
                for (int i = 0; i < clientIDS.size(); i++) {
                    out = out + clientIDS.get(i) + "-->" + "\n";
                }
                myBoard.components.setMessageBoxAct(out);
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(clientMessage);
                    if (clientMessage != null ) {
                        System.out.println(clientIDS.size());
                        String arr[] = clientMessage.split(" ", 2);
                        /*if (myBoard.components.getCheckSubmit()){
                            myBoard.components.setCheckSubmit(false);
                            serverMessage = myBoard.components.getChatText();
                        }*/
                        out = "Server -->" + myBoard.components.getChatText() + "\n";
                        if (!clientMessage.equals(prevMessage)) {
                            prevMessage = clientMessage;
                            System.out.println("j,fsndiv");

                            for (int i = 0; i < clientIDS.size(); i++) {
                                //System.out.println(clientIDS.get(i));
                                if (arr[0].equals(clientIDS.get(i))) {
                                    out = out + clientIDS.get(i) + "--> " + arr[1] + "\n";
                                    messages.set(i, arr[1]);
                                } else {
                                    out = out + clientIDS.get(i) + "-->" + messages.get(i) + "\n";
                                }

                            }
                            myBoard.components.setMessageBoxAct(out);
                        }
                        /*else if (myBoard.components.getCheckSubmit()){
                            myBoard.components.setCheckSubmit(false);
                            //serverMessage = myBoard.components.getChatText();
                        }*/
                    }

                    if (myBoard.components.getCheckSubmit()){
                        out = "Server -->" + myBoard.components.getChatText() + "\n";
                        myBoard.components.setCheckSubmit(false);
                        for (int i = 0; i < clientIDS.size(); i++) {
                            out = out + clientIDS.get(i) + "-->" + messages.get(i) + "\n";
                        }
                        myBoard.components.setMessageBoxAct(out);
                    }



                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while  (true){
                    System.out.println(clientIDS.size());
                    if (clientIDS.size() > count){
                        management.peers.addButton();
                    }
                    count = clientIDS.size();
                    String out = "";
                    for (int i = 0; i < clientIDS.size(); i++) {
                        out = out + clientIDS.get(i) + "\n";
                    }
                    //System.out.println(out);
                    management.peers.setjTextArea(out);
                    //management.peers.addButton();
                }
            }
        });
        thread2.start();
    }


    public static MyBoard getMyBoard() {
        return myBoard;
    }




    public static ArrayList<String> getMessages() {
        return messages;
    }

    public static ArrayList<String> getClientIDS() {
        return clientIDS;
    }

    public static void setClientMessage(String clientMessage) {
        Whiteboard.clientMessage = clientMessage;
    }


    @Override
    public void run() {

    }
}
///Users/rushithkarunaratne/Desktop/Distributed/Assignment2/out/production/Assignment2
