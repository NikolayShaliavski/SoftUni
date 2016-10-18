package problem_10;

import problem_10.contracts.Engine;
import problem_10.core.EngineImpl;

public class Main {

    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
