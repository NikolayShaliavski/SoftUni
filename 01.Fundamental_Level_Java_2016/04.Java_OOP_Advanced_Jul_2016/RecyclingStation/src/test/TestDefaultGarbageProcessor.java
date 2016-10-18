package test;

import org.junit.Before;
import org.junit.Test;
import wasteDisposal.DefaultGarbageProcessor;
import wasteDisposal.contracts.GarbageProcessor;
import wasteDisposal.contracts.Waste;

public class TestDefaultGarbageProcessor {

    private GarbageProcessor garbageProcessor;
    private Waste waste;

    @Before
    public void initGarbageProcessor() {
        this.garbageProcessor = new DefaultGarbageProcessor();
    }

    @Test
    public void testProcessMethod_shouldReturnNotNullProcessDataObject() {

    }
}
