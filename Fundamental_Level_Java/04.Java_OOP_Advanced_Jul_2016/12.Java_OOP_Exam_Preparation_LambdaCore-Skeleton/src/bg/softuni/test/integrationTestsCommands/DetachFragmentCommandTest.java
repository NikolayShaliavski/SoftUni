package bg.softuni.test.integrationTestsCommands;

import bg.softuni.main.contracts.modelContracts.Core;
import bg.softuni.main.contracts.modelContracts.Fragment;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.models.lambdaSystem.LambdaSystem;
import bg.softuni.main.models.cores.SystemCore;
import bg.softuni.main.models.fragments.NuclearFragment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class DetachFragmentCommandTest {

    private static final String CORE_NAME = "TestCore";
    private static final int CORE_DURABILITY = 2000;
    private static final String FRAGMENT_NAME = "DetachedFragment";
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

    //region detachFragment() method
    /**
     * After detaching fragment from current core
     * core durability should be different
     */
    @Test
    public void detachCommandTest_detachFragment_shouldChangeDurability() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.selectCore(this.testCore.getCoreName());
        this.lambdaCoreSystem.attachFragmentToCore(this.testFragment);
        //take core durability after attaching a fragment
        int unexpected = this.lambdaCoreSystem.getCurrentCore().getDurability();
        //Act
        this.lambdaCoreSystem.detachFragment();
        //core durability must change after detaching a fragment
        int actual = this.lambdaCoreSystem.getCurrentCore().getDurability();
        //Assert
        Assert.assertNotEquals(unexpected, actual);
    }

    /**
     * After detaching fragment from current core
     * count of fragments in this core should decrease
     */
    @Test
    public void detachCommandTest_detachFragment_shouldDecreaseFragmentsCount() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.selectCore(this.testCore.getCoreName());
        this.lambdaCoreSystem.attachFragmentToCore(this.testFragment);
        //count of fragments after attaching is 1
        int unexpected = this.lambdaCoreSystem.getCurrentCore().getNumberOfFragments();
        //Act
        this.lambdaCoreSystem.detachFragment();
        //count of fragments after detaching is 0
        int actual = this.lambdaCoreSystem.getCurrentCore().getNumberOfFragments();
        //Assert
        Assert.assertNotEquals(unexpected, actual);
    }

    /**
     * Detach method should return last added fragment
     */
    @Test
    public void detachCommandTest_detachFragment_shouldReturnLastAttached() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.selectCore(this.testCore.getCoreName());
        this.lambdaCoreSystem.attachFragmentToCore(this.testFragment);
        Fragment expectedFragment = this.testFragment;
        //Act
        Fragment actualFragment = this.lambdaCoreSystem.detachFragment();
        //Assert
        Assert.assertEquals(expectedFragment, actualFragment);
    }

    /**
     * Try to detach fragment from current core in system
     * which is not selected yet (current core is null)
     * @throws UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void detachCommandTest_currentCoreNotSelected_shouldThrowException() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.detachFragment();
    }

    /**
     * Try to detach fragment from current core before attaching
     * core hasn't any fragments yet
     * @throws NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void detachCommandTest_noFragmentsAttached_shouldThrowException() {
        //Arrange
        this.lambdaCoreSystem.addCore(this.testCore);
        this.lambdaCoreSystem.selectCore(this.testCore.getCoreName());
        this.lambdaCoreSystem.detachFragment();
    }
    //endregion
}
