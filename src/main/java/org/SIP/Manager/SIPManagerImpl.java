package org.SIP.Manager;

import org.SIP.Entity.MutualFund;
import org.SIP.Entity.SIP;
import org.SIP.Enum.Mode;
import org.SIP.Enum.States;
import org.SIP.Strategy.MonthlySIPStrategy;
import org.SIP.Strategy.QuartelySIPStrategy;
import org.SIP.Strategy.SIPStrategy;
import org.SIP.Strategy.WeeklySIPStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SIPManagerImpl implements SIPManager {

    private List<SIP> sipList;
    private int sipCounter = 0;
    private States sipState = States.active;
    private SIPStrategy sipStrategy;
    private ConcurrentHashMap<Integer, Boolean> sipExecution; //sipId - execution Mapping
    private UserSIPManager userSIPManager;
    public SIPManagerImpl(UserSIPManager userSIPManager) {
        this.sipList = new ArrayList<>();
        this.sipExecution = new ConcurrentHashMap<>();
        this.userSIPManager = new UsersSIPManagerImpl();
    }

    @Override
    public SIP createSIP(String userId, MutualFund mutualFund, double amount, Mode mode, LocalDate startDate) {
        SIP sip = new SIP(this.sipCounter++, mode, amount, startDate, this.sipState, mutualFund);
        this.sipList.add(sip);
        return sip;
    }

    @Override
    public void executeSIP() {
        for (SIP sip : this.sipList) {
            switch (sip.getMode()){
                case weekly -> {
                    this.sipStrategy = new WeeklySIPStrategy();
                    this.sipExecution.put(sip.getSipId(), this.sipStrategy.executeSip(sip));
                }
                case monthly -> {
                    this.sipStrategy = new MonthlySIPStrategy();
                    this.sipExecution.put(sip.getSipId(), this.sipStrategy.executeSip(sip));
                }
                case quarterly -> {
                    this.sipStrategy = new QuartelySIPStrategy();
                    this.sipExecution.put(sip.getSipId(), this.sipStrategy.executeSip(sip));
                }

            }
        }
    }

}
