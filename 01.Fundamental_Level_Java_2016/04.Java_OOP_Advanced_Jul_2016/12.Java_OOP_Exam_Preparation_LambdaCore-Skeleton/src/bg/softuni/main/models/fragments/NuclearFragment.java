package bg.softuni.main.models.fragments;

import bg.softuni.main.enums.FragmentType;

public class NuclearFragment extends BaseFragment {

    private static final FragmentType NUCLEAR_FRAGMENT_TYPE = FragmentType.Nuclear;

    public NuclearFragment(String name, int pressureAffection) {
        super(name, NUCLEAR_FRAGMENT_TYPE);
        this.setPressureAffection(pressureAffection);
    }

    @Override
    protected void setPressureAffection(int value) {
        super.setPressureAffection(value * 2);
    }

    @Override
    public int applyFragmentPressureAffection() {
        return super.getPressureAffection() * -1;
    }
}
