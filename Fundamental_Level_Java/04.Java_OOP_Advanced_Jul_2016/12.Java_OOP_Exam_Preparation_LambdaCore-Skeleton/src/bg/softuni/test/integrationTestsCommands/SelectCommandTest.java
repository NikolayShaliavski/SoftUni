package bg.softuni.test.integrationTestsCommands;

import bg.softuni.main.contracts.modelContracts.Core;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.models.lambdaSystem.LambdaSystem;
import bg.softuni.main.models.cores.SystemCore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class SelectCommandTest {

    private static final String FIRST_CORE_NAME = "A";
    private static final int FIRST_CORE_DURABILITY = 1000;

    private LambdaCore lambdaCoreSystem;

    @Before
    public void initLamdaCoreSystem() {
        this.lambdaCoreSystem = new LambdaSystem();
    }

    //region select command
    @Test
    public void selectCommandTest_selectCore_currentCoreShouldNotBeNull() {
        //Arrange
        Core expectedCore = new SystemCore(FIRST_CORE_NAME, FIRST_CORE_DURABILITY);
        this.lambdaCoreSystem.addCore(expectedCore);
        this.lambdaCoreSystem.selectCore(FIRST_CORE_NAME);
        //Act
        Core actualCore = this.lambdaCoreSystem.getCurrentCore();
        //Assert
        Assert.assertEquals(
                "Current core should be equals to selected core!", expectedCore, actualCore);
    }

    @Test
    public void selectCommandTest_currentCoreIsNotSelected_currentCoreShouldBeNull() {
        //Act
        Core nullCore = this.lambdaCoreSystem.getCurrentCore();
        //Assert
        Assert.assertNull("Current core should be null!", nullCore);
    }

    @Test(expected = NoSuchElementException.class)
    public void selectCommandTest_trySelectNonExistingCore_shouldThrowException() {
        final String nonExistingCoreName = "C";
        //Arrange
        Core core = new SystemCore(FIRST_CORE_NAME, FIRST_CORE_DURABILITY);
        this.lambdaCoreSystem.addCore(core);
        this.lambdaCoreSystem.selectCore(nonExistingCoreName);
    }
    //endregion
}
