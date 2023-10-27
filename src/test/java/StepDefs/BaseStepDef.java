package StepDefs;

import util.Randomizer;
import util.ScenarioContext;

public abstract class BaseStepDef {
    protected final ScenarioContext context;
    protected final Randomizer randomizer;

    public BaseStepDef(ScenarioContext context, Randomizer randomizer) {
        this.context = context;
        this.randomizer = randomizer;
    }
}
