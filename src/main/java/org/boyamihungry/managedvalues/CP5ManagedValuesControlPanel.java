package org.boyamihungry.managedvalues;

import processing.core.PVector;

import java.util.*;

/**
 * Created by patwheaton on 10/11/16.
 */
public class CP5ManagedValuesControlPanel implements ManagedValuesControlPanel {

    private final String name;
    private int locationX;
    private int locationY;

    private final int MARGIN = 10;

    static final PVector ORIGIN = new PVector(0,0);


    public CP5ManagedValuesControlPanel(String name, int x, int y) {
        this.name = name;
        locationX = x;
        locationY = y;
    }

    public String getName() {
        return name;
    }

    private Set<ManagedValue> values = new HashSet<>();

    private Map<ManagedValue, List<ManagedValueControlPanel>> activePresenters = new HashMap<>();

    /**
     * map of <managed value class, presenter classes> showing what presenter types are available to what managed value types.
     */
    private Map<Class, Set<Class>> availablePresenters = new HashMap<>();

    /**
     * Renders the view.
     * @param origin
     * @param size
     */
    @Override
    public void show(PVector origin, PVector size) {
        //calc size for each presenter
        PVector sizeLimit = new PVector(size.x, (size.y - (MARGIN * (activePresenters.size() + 1))) / activePresenters.size());
        PVector currentOrigin = origin.copy();
        currentOrigin.x += MARGIN;
        currentOrigin.y += MARGIN;

        //herehere does this work?
        for ( ManagedValue value : values) {
            if (activePresenters.containsKey(value)) {
                for ( ManagedValueControlPanel presenter : activePresenters.get(value) ) {
                    currentOrigin.add(presenter.draw(currentOrigin, ORIGIN, sizeLimit));
                }
                currentOrigin.y += MARGIN;
            }
        }

    }

    /**
     * Adds a value to be displayed in the view with the first presenter for the class of the value.
     *
     * @param value
     */
    @Override
    public void addManagedValue(ManagedValue value) {
        values.add(value);
    }

    /**
     * Removes a value from being displayed in the view.
     *
     * @param value
     */
    @Override
    public void removeManagedValue(ManagedValue value) {
        values.remove(value);
    }

    /**
     * Retrieves a list of all the managed values in the view.
     *
     * @return
     */
    @Override
    public Collection<ManagedValue> getManagedValues() {
        return Collections.unmodifiableSet(values);
    }

    /**
     * Sets the class to use as the presenter for this
     *  @param value
     * @param presenter
     * @param makeItTheOnlyPresenter
     */
//    @Override
//    public <T extends Number> void setPresenterForValue(ManagedValue<T> value, ManagedValueControlPanel presenter, boolean makeItTheOnlyPresenter) {
//
//        // validate presenter is a cp5 presenter
//        if ( ! (presenter instanceof CP5Controller) ) {
//            throw new IllegalArgumentException("The presenter " + presenter + " is not a subtype of " + CP5Controller.class.getName());
//        }
//        CP5Controller cp5Controller = (CP5Controller)presenter;
//
//        // add it to the list of available presenters for that managed value type
//        if ( availablePresenters.containsKey(value.getClass())) {
//            availablePresenters.get(value.getClass()).add(presenter.getClass());
//        } else {
//            availablePresenters.put(value.getClass(), Stream.of(cp5Controller.getClass()).collect(Collectors.toSet()));
//        }
//
//        if ( (! makeItTheOnlyPresenter) || activePresenters.containsKey(value) ) {
//            activePresenters.get(value).add(cp5Controller);
//        } else {
//            activePresenters.put(value, Stream.of(cp5Controller).collect(Collectors.toList()));
//        }
//
//        // "redraw" the presenters
//        layoutPresenters();
//
//    }

    private void layoutPresenters() {

//        int currentY = locationY;
//
//
//        int currentX = ui_sys_margin;
//                int controlCount = 0;
//
//                // --------------------------------
//                // ---- exit size
//                String varName = "exitSize";
//                cp5.addToggle(UI_TOGGLE + varName)
//                        .setPosition(currentX, (ui_sys_margin + ui_sys_controlHeight) * ++controlCount)
//                        .setSize(ui_sys_toggleWidth, ui_sys_controlHeight)
//                        .setValue(ui_exitSize)
//                        .setLabelVisible(false);
//                currentX += ui_sys_toggleWidth;
//                cp5.addLabel("Exit size")
//                        .setPosition(currentX, (ui_sys_margin + ui_sys_controlHeight) * controlCount)
//                        .setSize(ui_sys_labelWidth, ui_sys_controlHeight)
//                        .setFont(arialNar16);
//                currentX += ui_sys_labelWidth;
//                ControlP5Helper.addControlToGroup( varName + "-false",
//                        cp5.addSlider(varName)
//                                .setPosition(currentX, (ui_sys_margin + ui_sys_controlHeight) * controlCount)
//                                .setSize(ui_sys_sliderWidth, ui_sys_controlHeight)
//                                .setRange(1, 100)
//                                .setValue(exitSize)
//                                .setLabelVisible(false)
//                                .setVisible(!ui_exitSize),
//                        ui_controlGroups);
//
//                // don't update currentX, stay in the same place
//                ControlP5Helper.addControlToGroup(varName + "-true",
//                        cp5.addTextfield(UI_VAL_FROM + varName)
//                                .setPosition(currentX, (ui_sys_margin + ui_sys_controlHeight) * controlCount)
//                                .setSize(ui_sys_textFieldWidth, ui_sys_controlHeight)
//                                .setValue(ui_fromExitSizeInitial)
//                                .setLabel("")  // setting label non-visible does not work
//                                .setVisible(ui_exitSize),
//                        ui_controlGroups);
//                currentX += ui_sys_textFieldWidth;
//                ControlP5Helper.addControlToGroup(varName + "-true",
//                        cp5.addLabel("to")
//                                .setPosition(currentX, (ui_sys_margin + ui_sys_controlHeight) * controlCount)
//                                .setSize(ui_sys_toLabelWidth, ui_sys_controlHeight)
//                                .setFont(arialNar16)
//                                .setVisible(ui_exitSize),
//                        ui_controlGroups);
//                currentX += ui_sys_toLabelWidth;
//                ControlP5Helper.addControlToGroup(varName + "-true",
//                        cp5.addTextfield(UI_VAL_TO + varName)
//                                .setPosition(currentX, (ui_sys_margin + ui_sys_controlHeight) * controlCount)
//                                .setSize(ui_sys_textFieldWidth, ui_sys_controlHeight)
//                                .setValue(ui_toExitSizeInitial)
//                                .setLabel("")  // setting label non-visible does not work
//                                .setVisible(ui_exitSize),
//                        ui_controlGroups);
//                currentX += ui_sys_textFieldWidth + ui_sys_margin;
//
//                ControlP5Helper.addControlToGroup(varName + "-true",
//                        cp5.addScrollableList(UI_OSCIL_SELECTOR + varName)
//                                .setPosition(currentX, (ui_sys_margin + ui_sys_controlHeight) * controlCount)
//                                .setSize(ui_sys_dropdownWidth, ui_sys_dropdown_height)
//                                .setBarHeight(ui_sys_controlHeight)
//                                .setItemHeight(ui_sys_controlHeight)
//                                .addItem(NEW_OSCILLATOR,NEW_OSCILLATOR)
//                                .addItems(ui_oscillators.keySet().stream().collect(Collectors.toList()))
//                                .setFont(arialNar12)
//                                .setLabel("select oscillator...")  // setting label non-visible does not work
//                                .setVisible(ui_exitSize)
//                                .setOpen(false)
//                                .bringToFront(),
//                        ui_controlGroups);
//
//                // --------------------------------
//
//




    }


    /**
     * Convienience method to sets the class to use as the presenter for a value, from the key for the value.
     *
     * @param valueKey
     * @param presenter
     * @param makeItTheOnlyPresenter
     */
//    @Override
//    public void setPresenterForValue(String valueKey, ManagedValueControlPanel presenter, boolean makeItTheOnlyPresenter) {
//        ManagedValue value = values.stream().filter(s -> s.getKey().equals(valueKey)).findFirst().orElse(null);
//        if ( null != value ) {
//            setPresenterForValue(value, presenter, makeItTheOnlyPresenter);
//        } else {
//            throw new ManagedValueManager.NoSuchManagedValueException("There is no managed value with the name " + valueKey + " in this view: " + this);
//        }
//    }

//    @Override
//    public Collection<Class> getAvailablePresenters(@NonNull Class clazz) {
//        return Collections.unmodifiableSet(availablePresenters.get(clazz));
//    }
//
//    @Override
//    public Collection<Class> getAvailablePresenters(@NonNull ManagedValue value) {
//        return getAvailablePresenters(value.getClass());
//    }
//
//    @Override
//    public Collection<ManagedValueControlPanel> getPresenterForValue(ManagedValue value) {
//        return activePresenters.get(value );
//    }
}
