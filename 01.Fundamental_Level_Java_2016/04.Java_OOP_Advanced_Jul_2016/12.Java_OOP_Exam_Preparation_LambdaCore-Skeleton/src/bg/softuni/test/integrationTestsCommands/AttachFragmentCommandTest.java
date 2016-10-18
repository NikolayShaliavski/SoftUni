package bg.softuni.test.integrationTestsCommands;

import bg.softuni.main.contracts.factoryContracts.FragmentFactory;
import bg.softuni.main.contracts.modelContracts.Core;
import bg.softuni.main.contracts.modelContracts.Fragment;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.core.factories.FragmentFactoryImpl;
import bg.softuni.main.models.lambdaSystem.LambdaSystem;
import bg.softuni.main.models.cores.SystemCore;
import bg.softuni.main.models.fragments.NuclearFragment;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttachFragmentCommandTest {

    private static final String CORE_NAME = "TestCore";
    private static final int CORE_DURABILITY = 2000;
    private static final String FRAGMENT_NAME = "TestFragment";
    private static final int FRAGMENT_AFFECTION = 500;

    LambdaCore lambdaCoreSystem;
    Core testCore;
    Fragment testFragment;

    @Before
    public void initFieldsForTests() {
        this.lambdaCoreSystem = new LambdaSystem();
        this.testCore = new SystemCore(CORE_NAME, CORE_DURABILITY);
        this.testFragment = new NuclearFragment(FRAGMENT_NAME, FRAGMENT_AFFECTION);
    }

    //region attachFragment() method
    /**
     * After attaching fragment to current core
     * count of fragments in this core should be different
     */
    @Test
    public void attachCommandTest_attachFragment_shouldIncreaseFragmentsCount() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.selectCore(this.testCore.getCoreName());
        int unexpected = this.lambdaCoreSystem.getCurrentCore().getNumberOfFragments();
        this.lambdaCoreSystem.attachFragmentToCore(testFragment);
        //Act
        int actual = this.lambdaCoreSystem.getCurrentCore().getNumberOfFragments();
        //Assert
        Assert.assertNotEquals(unexpected, actual);
    }

    /**
     * After attaching fragment to current core
     * core durability should be different
     */
    @Test
    public void attachCommandTest_attachFragment_shouldChangeDurability() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.selectCore(this.testCore.getCoreName());
        int unexpected = this.lambdaCoreSystem.getCurrentCore().getDurability();
        this.lambdaCoreSystem.attachFragmentToCore(testFragment);
        //Act
        int actual = this.lambdaCoreSystem.getCurrentCore().getDurability();
        //Assert
        Assert.assertNotEquals(unexpected, actual);
    }

    /**
     * Try to attach fragment to current core in system
     * which is not selected yet (current core is null)
     * @throws UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void attachCommandTest_currentCoreNotSelected_shouldThrowException() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.attachFragmentToCore(testFragment);
    }

    /**
     * Try to attach null to current core in system
     * @throws UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void attachCommandTest_fragmentIsNull_shouldThrowException() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.selectCore(this.testCore.getCoreName());
        this.lambdaCoreSystem.attachFragmentToCore(null);
    }

    /**
     * Before attaching fragment to current core it must be created by FragmentFactory
     * factory checks pressure arg and if it is negative throws exception
     * @throws ReflectiveOperationException
     * @throws InvalidArgumentException
     */
    @Test(expected = InvalidArgumentException.class)
    public void attachCommandTest_fragmentWithNegativePressure_shouldThrowException() throws ReflectiveOperationException, InvalidArgumentException {
        //Arrange
        FragmentFactory factory = new FragmentFactoryImpl();
        String[] fragmentParams = {"", "Cooling", "TestFragmentName", "-100"};
        factory.createFragment(fragmentParams);
    }

    /**
     * Before attaching fragment to current core it must be created by FragmentFactory
     * factory checks fragment type (Cooling or Nuclear)
     * and if it don't match (there isn't class with such name) throws ReflectiveOperationException
     * @throws ReflectiveOperationException
     * @throws InvalidArgumentException
     */
    @Test(expected = ReflectiveOperationException.class)
    public void attachCommandTest_fragmentTypeNoMatch_shouldThrowException() throws ReflectiveOperationException, InvalidArgumentException {
        //Arrange
        FragmentFactory factory = new FragmentFactoryImpl();
        String[] fragmentParams = {"", "NoSuchFragmentClass", "TestFragmentName", "100"};
        factory.createFragment(fragmentParams);
    }
    //endregion
}
