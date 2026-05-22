
public class Main {

    public static void main(String[] args) {
        TeamLeader t = new TeamLeader();
        ProjectLeader p = new ProjectLeader();
        HR hr = new HR();
        Manager m = new Manager();

        t.setSupervisor(p);
        p.setSupervisor(hr);
        hr.setSupervisor(m);

        Leave l = new Leave(10, 2, Reason.REGULAR);
        System.out.println(t.applyLeave(l));
    }
}

enum Reason {
    REGULAR, // 0
    SPECIAL, // 1
    CRITICAL // 2
}

class Leave {

    int days;
    int tier;
    Reason r;

    public Leave(int days, int tier, Reason r) {
        this.days = days;
        this.tier = tier;
        this.r = r;
    }
}

abstract class LeaveHandler {

    public LeaveHandler s;

    public void setSupervisor(LeaveHandler s) {
        this.s = s;
    }

    abstract String applyLeave(Leave l);
}

class TeamLeader extends LeaveHandler {  //approve if days <= 7, tier >= 4

    public String applyLeave(Leave l) {
        if (l.days <= 7 && l.tier >= 4) {
            return "Team Leader Approval";
        } else {
            return s.applyLeave(l);
        }
    }
}

class ProjectLeader extends LeaveHandler { //approve if days <= 14, tier >= 3

    public String applyLeave(Leave l) {
        if (l.days <= 14 && l.tier >= 3) {
            return "Project Leader Approval";
        } else {
            return s.applyLeave(l);
        }
    }
}

class HR extends LeaveHandler { // approve if days <= 21, tier >= 3 and reasion == REGULAR

    public String applyLeave(Leave l) {
        if (l.days <= 21 && l.tier >= 3 && l.r == Reason.REGULAR) {
            return ("HR Approval");
        } else {
            return s.applyLeave(l);
        }
    }
}

class Manager extends LeaveHandler { //aprove if tier >= 2 and reason == SPECIAL

    public String applyLeave(Leave l) {
        if (l.tier >= 2 && l.r == Reason.SPECIAL) {
            return "Manager Approval";
        } else {
            return "Leave Request Denied";
        }
    }
}
