package edu.eci.arsw.blacklistvalidator;

import java.util.ArrayList;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;


public class CheckSegment implements Runnable{
    
    private ArrayList<Integer> blackListOcurrence;
    private int start; 
    private int end;
    private String ipaddress;
    public Thread hilo;
    private HostBlacklistsDataSourceFacade skds;
    private boolean finished;

    public CheckSegment (int start, int end, String ipaddress){
        hilo = new Thread(this);
        this.start = start;
        this.end = end;
        this.ipaddress = ipaddress;
        blackListOcurrence = new ArrayList<>();
        skds=HostBlacklistsDataSourceFacade.getInstance();
        finished = false;
    }

    public ArrayList<Integer> getBlackListOcurrence() {
        return blackListOcurrence;
    }

    @Override
    public void run(){
        for (int i = start; i<= end; i++){
            if (skds.isInBlackListServer(i, ipaddress)){
                blackListOcurrence.add(i);
            }
        }
        System.out.println("se encontraron: "+blackListOcurrence.size());
    }    
    



}
