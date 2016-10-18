package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import recycleStation.models.strategies.BurnableStrategy;
import recycleStation.models.strategies.RecyclableStrategy;
import recycleStation.modelsAnnotations.Burnable;
import wasteDisposal.DefaultStrategyHolder;
import wasteDisposal.annotations.Disposable;
import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.StrategyHolder;

import java.util.Map;


public class TestStrategyHolder {

    StrategyHolder strategyHolder;
    private GarbageDisposalStrategy burnableStrategy;
    private Burnable burnable;

    @Before
    public void initStrategyHolder() {
        this.strategyHolder = new DefaultStrategyHolder();
        this.burnableStrategy = Mockito.mock(BurnableStrategy.class);
        this.burnable = Mockito.mock(Burnable.class);
    }

    @Test
    public void testConstructor_initStrategyHolder_objectShouldBeNotNull() {
        //Assert
        Assert.assertNotNull(this.strategyHolder);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetDisposalStrategiesMethod_returnUnmodifiableMap_shouldThrowException() {
        Map<Class, GarbageDisposalStrategy> testStrategies =
                this.strategyHolder.getDisposalStrategies();
        testStrategies.put(Disposable.class, new RecyclableStrategy());
    }

    //region addStrategy() method
    @Test
    public void testAddStrategyMethod_addNewStrategy_shouldIncreaseMapSize() {
        //Arrange
        this.strategyHolder.addStrategy(this.burnable.getClass(), this.burnableStrategy);
        int expectedSize = 1;
        //Act
        int actualSize = this.strategyHolder.getDisposalStrategies().size();
        //Assert
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddStrategyMethod_tryAddNullStrategy_shouldThrowException() {
        //Arrange
        this.strategyHolder.addStrategy(this.burnable.getClass(), null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddStrategyMethod_tryPutNullKey_shouldThrowException() {
        //Arrange
        this.strategyHolder.addStrategy(null, this.burnableStrategy);
    }
    //endregion

    //region removeStrategy() method
    @Test
    public void testRemoveStrategyMethod_removeStrategy_shouldDecreaseMapSize() {
        //Arrange
        this.strategyHolder.addStrategy(this.burnable.getClass(), this.burnableStrategy);
        this.strategyHolder.removeStrategy(this.burnable.getClass());
        int unexpectedSize = 1;
        //Act
        int actualSize = this.strategyHolder.getDisposalStrategies().size();
        //Assert
        Assert.assertNotEquals(unexpectedSize, actualSize);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveStrategyMethod_tryPassNullKey_shouldThrowException() {
        //Arrange
        this.strategyHolder.removeStrategy(null);
    }
    //endregion
}
