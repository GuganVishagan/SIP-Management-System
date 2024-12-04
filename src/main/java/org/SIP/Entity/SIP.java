package org.SIP.Entity;

import lombok.Getter;
import lombok.Setter;
import org.SIP.Enum.Mode;
import org.SIP.Enum.States;

import java.time.LocalDate;

@Getter
@Setter
public class SIP {
    private int sipId;
    private Mode mode;
    private double amount;
    private LocalDate startDate;
    private LocalDate recurringDate;
    private States status;
    private MutualFund mutualFund;

    public SIP(int sipId, Mode mode, double amount, LocalDate startDate, States status, MutualFund mutualFund) {
        this.sipId = sipId;
        this.mode = mode;
        this.amount = amount;
        this.startDate = startDate;
        this.recurringDate = startDate;
        this.status = status;
        this.mutualFund = mutualFund;
    }
}
