import org.boyamihungry.managedvalues.ManagedValueManager;
import org.boyamihungry.managedvalues.ManagedValuesControlPanel;
import org.boyamihungry.managedvalues.ValueController;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNull;

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

    @Test
    public void view_that_does_not_exist_returns_null() {
        ManagedValuesControlPanel view = manager.getView("does not exist");

        assertNull("view should be uill" + view, view);
    }


}
