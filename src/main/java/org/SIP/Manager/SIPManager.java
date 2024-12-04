package org.SIP.Manager;

import org.SIP.Entity.MutualFund;
import org.SIP.Entity.SIP;
import org.SIP.Enum.Mode;

import java.time.LocalDate;
import java.util.List;

public interface SIPManager {
    public SIP createSIP(String userId, MutualFund mutualFund, double amount, Mode mode, LocalDate startDate);
    public void executeSIP();
}
