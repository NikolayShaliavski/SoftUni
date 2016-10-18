package problem_08.duties;

import problem_08.interfaces.Mission;

public class MissionImpl implements Mission {

    private static final String STATE_FINISHED = "Finished";
    private static final String STATE_IN_PROGRESS = "inProgress";

    private String codeName;
    private String state;

    public MissionImpl(String codeName, String state) {
        this.setCodeName(codeName);
        this.setState(state);
    }

    @Override
    public String getCodeName() {
        return this.codeName;
    }

    private void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public String getState() {
        return this.state;
    }

    private void setState(String state) {
        if (!state.equals(STATE_FINISHED) && !state.equals(STATE_IN_PROGRESS)) {
            throw new IllegalArgumentException("State don't match to the mission.");
        }
        this.state = state;
//        if (state.equalsIgnoreCase(STATE_FINISHED)) {
//            this.state = STATE_FINISHED;
//        } else if (state.equalsIgnoreCase(STATE_IN_PROGRESS)) {
//            this.state = STATE_IN_PROGRESS;
//        } else {
//            throw new IllegalArgumentException("State don't match to the mission.");
//        }
    }

    @Override
    public void completeMission() {
        this.setState("Finished");
    }

    @Override
    public String toString() {
        return String.format("  Code Name: %s State: %s%n", this.codeName, this.state);
    }
}
