import org.boyamihungry.managedvalues.ManagedValueManager;
import org.boyamihungry.managedvalues.controllers.ValueController;
import org.junit.BeforeClass;

/**
 * Created by patwheaton on 10/13/16.
 */
public class ManagedValueManagerTest {

    ManagedValueManager manager = new ManagedValueManager() {
        @Override
        public String getName() {
            return "test";
        }

        @Override
        public <T extends Number> boolean validateController(ValueController<T> controller) {
            return true;
        }
    };

    @BeforeClass
    public static void setup() {

    }



}
