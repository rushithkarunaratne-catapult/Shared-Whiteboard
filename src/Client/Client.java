package Client;

import Remote.ITrial;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable{
    private static MyBoard myBoard;
    private static ITrial iTrial;
    private static Thread t1;
    private String clientName;
    private final String myId;

    public Client() throws RemoteException {
        myId = iTrial.createId();
        System.out.println("my id "+myId);
    }

    //private static IClientSide iClientSide;
    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        myBoard = new MyBoard();

        Registry registry = LocateRegistry.getRegistry("localhost");
        iTrial = (ITrial) registry.lookup("trial");


        double addResult = iTrial.add(5.0, 3.0);
        System.out.println("5.0 + 3.0 = " + addResult);

        //System.out.println(iTrial.sendCircles());
        //myBoard.components.setCirclePoints(iTrial.sendCircles());
        t1 = new Thread(new Client());
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(myBoard.components.getCirclePoints().size() + "j");
                    try {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //if (myBoard.components.getCirclePoints().size() != 0) {
                            iTrial.setServerCircles(myBoard.components.getCirclePoints());
                            iTrial.setServerRectangles(myBoard.components.getRectangles());
                            iTrial.setServerLines(myBoard.components.getLine2DS());
                            //iTrial.sendTexts(myBoard.components.getTextAreas());


                            System.out.println("j");
                        //}
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("check" + myBoard.components.getCheckSubmit());
            try {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                myBoard.components.setCirclePoints(iTrial.sendCircles());
                myBoard.components.setRectangles(iTrial.sendRects());
                myBoard.components.setLine2DS(iTrial.sendLines());
                myBoard.components.setTextAreas(iTrial.sendTexts());
                myBoard.components.setMessageBoxAct(iTrial.setClientMessages());

                if (myBoard.components.getCheckSubmit()) {
                    myBoard.components.setCheckSubmit(false);
                    iTrial.message(myId +" "+ myBoard.components.getChatText());
                    //myBoard.components.setMessageBoxAct("Client " + myBoard.components.getChatText());
                }


                //iTrial.setCirclesServer(7);
                //iTrial.setColour(Color.GREEN);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}

