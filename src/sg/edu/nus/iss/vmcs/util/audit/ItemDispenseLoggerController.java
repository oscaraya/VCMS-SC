package sg.edu.nus.iss.vmcs.util.audit;

import sg.edu.nus.iss.vmcs.system.Environment;

public class ItemDispenseLoggerController extends BaseLoggerController {

    @Override
    public boolean isEnabled() {
        return Environment.isAuditLogEnabled() && Environment.isAuditLogItemDispenseEnabled();
    }

}
