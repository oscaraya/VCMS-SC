package sg.edu.nus.iss.vmcs.store;

/**
 * Created by jdon on 4/28/2017.
 */
public abstract class Observer {
    protected StoreController storeController;
    public abstract void updateDrink(String name);
    public abstract void updateCoin(String name);
}
