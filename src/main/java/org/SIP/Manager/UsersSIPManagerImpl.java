package org.SIP.Manager;

import org.SIP.Entity.SIP;
import org.SIP.Enum.States;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UsersSIPManagerImpl implements UserSIPManager{
    private ConcurrentHashMap<String, List<SIP>> userSIP; //userId - List<SIP> mapping.
    public UsersSIPManagerImpl() {
        this.userSIP = new ConcurrentHashMap<>();
    }

    @Override
    public boolean endSIP(String userId, int sipId) {
        isValidUser(userId);
        isEmptySIP(userId);
        List<SIP> sips = userSIP.get(userId);
        for (SIP sip : sips) {
            if(sip.getSipId() == sipId){
                sip.setStatus(States.stopped);
            }
        }
        return true;
    }

    @Override
    public boolean pauseSIP(String userId, int sipId) {
        isValidUser(userId);
        isEmptySIP(userId);
        List<SIP> sips = userSIP.get(userId);
        for (SIP sip : sips) {
            if(sip.getSipId() == sipId){
                sip.setStatus(States.paused);
            }
        }
        return true;
    }

    @Override
    public boolean resumeSIP(String userId, int sipId) {
        isValidUser(userId);
        isEmptySIP(userId);
        List<SIP> sips = userSIP.get(userId);
        for (SIP sip : sips) {
            if(sip.getSipId() == sipId){
                sip.setStatus(States.active);
            }
        }
        return true;
    }

    @Override
    public List<SIP> getAllSIP(String userId) {
        isValidUser(userId);
        isEmptySIP(userId);
        return this.userSIP.get(userId);
    }

    @Override
    public boolean addSIP(String userId, SIP sip) {
        return this.userSIP.computeIfAbsent(userId, k -> new ArrayList<>()).add(sip);
    }

    public void isValidUser(String userId){
        if(!this.userSIP.containsKey(userId))
            throw new RuntimeException("User not found");
    }
    public void isEmptySIP(String userId){
        if(this.userSIP.get(userId).isEmpty())
            throw new RuntimeException("No SIP found for the user: " + userId);
    }

}
