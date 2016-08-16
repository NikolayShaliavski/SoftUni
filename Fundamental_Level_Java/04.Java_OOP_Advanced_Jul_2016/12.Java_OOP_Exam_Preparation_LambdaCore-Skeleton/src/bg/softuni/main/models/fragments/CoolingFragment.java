package bg.softuni.main.models.fragments;

import bg.softuni.main.enums.FragmentType;

public class CoolingFragment extends BaseFragment {

    private static final FragmentType COOLING_FRAGMENT_TYPE = FragmentType.Cooling;

    public CoolingFragment(String name, int pressureAffection) {
        super(name, COOLING_FRAGMENT_TYPE);
        this.setPressureAffection(pressureAffection);
    }

    @Override
    protected void setPressureAffection(int value) {
        super.setPressureAffection(value * 3);
    }

    @Override
    public int applyFragmentPressureAffection() {
        return super.getPressureAffection();
    }
}
