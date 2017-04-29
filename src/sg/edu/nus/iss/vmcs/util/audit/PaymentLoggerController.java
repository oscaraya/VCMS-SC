package sg.edu.nus.iss.vmcs.util.audit;

import sg.edu.nus.iss.vmcs.system.Environment;

public class PaymentLoggerController extends BaseLoggerController{

    @Override
    public boolean isEnabled() {
        return Environment.isAuditLogEnabled() && Environment.isAuditLogPaymentEnabled();
    }

}
