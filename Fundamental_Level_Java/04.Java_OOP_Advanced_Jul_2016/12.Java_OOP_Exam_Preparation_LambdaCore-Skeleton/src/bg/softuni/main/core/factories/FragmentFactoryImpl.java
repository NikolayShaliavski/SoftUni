package bg.softuni.main.core.factories;

import bg.softuni.main.contracts.factoryContracts.FragmentFactory;
import bg.softuni.main.contracts.modelContracts.Fragment;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Constructor;

public class FragmentFactoryImpl implements FragmentFactory {

    private static final String FRAGMENT_PACKAGE = "bg.softuni.main.models.fragments.";
    private static final String FRAGMENT_SUFFIX = "Fragment";

    @Override
    @SuppressWarnings("unchecked")
    public Fragment createFragment(String[] fragmentParams)
            throws ReflectiveOperationException, InvalidArgumentException {
        Fragment fragment = null;
        String fragmentType = fragmentParams[1];
        String fragmentName = fragmentParams[2];
        int fragmentPressureAffection = Integer.valueOf(fragmentParams[3]);
        if (fragmentPressureAffection < 0) {
            throw new InvalidArgumentException(fragmentParams);
        }
        Class<Fragment> fragmentClass =
                (Class<Fragment>) Class.forName(FRAGMENT_PACKAGE + fragmentType + FRAGMENT_SUFFIX);
        Constructor fragmentCtor =
                fragmentClass.getDeclaredConstructor(String.class, int.class);
        fragment =
                (Fragment) fragmentCtor.newInstance(fragmentName, fragmentPressureAffection);
        return fragment;
    }
}
