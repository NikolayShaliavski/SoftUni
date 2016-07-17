package problem_08.interfaces;

import problem_08.duties.MissionImpl;

public interface Commando extends SpecializedSoldier {

    void addMission(MissionImpl missionImpl);
}
