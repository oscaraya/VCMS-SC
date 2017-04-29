package sg.edu.nus.iss.vmcs.store;

/**
 * Created by jdon on 4/29/2017.
 */
public class UpdateCoinLowStock implements UpdateStrategy {
    @Override
    public void UpdateLowStock(String channelType, String name){
        System.out.println( channelType+" - "+name+" is in low stock" );
    }
}

