package sg.edu.nus.iss.vmcs.util.audit;


import java.time.LocalDateTime;

public abstract class BaseLoggerController implements LoggerController {


    @Override
    public void info(String content) {
        if (!this.isEnabled()) {
            return;
        }
        System.out.println(LocalDateTime.now().toString() + " - [INFO] " + content);

    }

    @Override
    public void debug(String content) {
        if (!this.isEnabled()) {
            return;
        }
        System.out.println(LocalDateTime.now().toString() + " -[DEBUG] " + content);

    }

    @Override
    public void warn(String content) {
        if (!this.isEnabled()) {
            return;
        }
        System.out.println(LocalDateTime.now().toString() + " -[WARN] " + content);

    }

    @Override
    public void error(String content) {
        if (!this.isEnabled()) {
            return;
        }
        System.out.println(LocalDateTime.now().toString() + " -[ERROR] " + content);

    }
}
