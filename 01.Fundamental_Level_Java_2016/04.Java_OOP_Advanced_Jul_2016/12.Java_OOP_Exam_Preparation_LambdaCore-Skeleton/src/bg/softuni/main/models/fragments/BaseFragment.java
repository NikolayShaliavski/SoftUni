package bg.softuni.main.models.fragments;

import bg.softuni.main.contracts.modelContracts.Fragment;
import bg.softuni.main.enums.FragmentType;

public abstract class BaseFragment implements Fragment {

    private String name;
    private int pressureAffection;
    private FragmentType fragmentType;

    protected BaseFragment(String name,
                           FragmentType fragmentType) {
        this.setName(name);
        this.setFragmentType(fragmentType);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public FragmentType getFragmentType() {
        return this.fragmentType;
    }

    private void setFragmentType(FragmentType fragmentType) {
        this.fragmentType = fragmentType;
    }

    int getPressureAffection () {
        return this.pressureAffection;
    }

    protected void setPressureAffection (int pressureAffection) {
        this.pressureAffection = pressureAffection;
    }
}
