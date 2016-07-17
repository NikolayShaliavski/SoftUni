package problem_08.soldiers;

import problem_08.duties.MissionImpl;
import problem_08.interfaces.Commando;

import java.util.LinkedHashSet;
import java.util.Set;

public class CommandoImpl extends SpecializedSoldierImpl implements Commando {

    private Set<MissionImpl> missions;

    public CommandoImpl(String firstName,
                 String lastName,
                 String id,
                 Double salary,
                 String corps) {
        super(firstName, lastName, id, salary, corps);
        this.missions = new LinkedHashSet<>();
    }

    @Override
    public void addMission(MissionImpl missionImpl) {
        this.missions.add(missionImpl);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).
                append("Missions:").
                    append(System.lineSeparator());
        for (MissionImpl mission : this.missions) {
            builder.append(mission.toString());
        }
        return builder.toString().trim();
    }
}
