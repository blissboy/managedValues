package org.boyamihungry.managedvalues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ManagedValueManager {

    /*
     *
     *
     * I dont think we need any of this
     */



    List<ManagedView> views = new ArrayList<>();

    public <T> T getValue(String valueKey);

    public void addOptionForValue(String optionName, ManagedValue option);

    public List<ManagedValue> getOptionsForKey(String key);

    public ManagedValue removeOptionForKey(String key);

    public ManagedView getView(String viewKey);

    public List<ManagedView> getViews();

    public void addView(ManagedView view);

}
