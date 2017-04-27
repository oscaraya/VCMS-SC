package sg.edu.nus.iss.vmcs.util.audit;

public interface LoggerController {

    boolean isEnabled();

    void info(String content);

    void debug(String content);

    void warn(String content);

    void error(String content);

}
