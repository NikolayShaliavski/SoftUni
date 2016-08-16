package bg.softuni.main.contracts.modelContracts;

import bg.softuni.main.enums.FragmentType;

/**
 * <b>Fragment</b> interface defines the behaviours of one of the models
 * in this application - fragments. Fragments are primary components of
 * objects of <b>Core</b> type
 */
public interface Fragment {

    /**
     * @return the name of a current object of <b>Fragment</b> type
     */
    String getName();

    /**
     * @return the type of a current fragment
     * @see FragmentType
     */
    FragmentType getFragmentType();

    /**
     * Apply fragment's pressure affection on core which holds it.
     * This affects directly core's durability
     *
     * @return fragment's pressure affection
     */
    int applyFragmentPressureAffection();
}
