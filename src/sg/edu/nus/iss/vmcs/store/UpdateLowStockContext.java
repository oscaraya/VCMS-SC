package sg.edu.nus.iss.vmcs.store;

/**
 * Created by jdon on 4/29/2017.
 */
public class UpdateLowStockContext {
    private UpdateStrategy strategy;

    public UpdateLowStockContext(UpdateStrategy strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(String channelType, String name){
        strategy.UpdateLowStock(channelType, name);
    }
}
