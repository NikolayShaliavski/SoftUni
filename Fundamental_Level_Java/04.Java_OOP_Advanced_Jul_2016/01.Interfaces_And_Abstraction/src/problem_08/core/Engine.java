package problem_08.core;

import problem_08.IO.Reader;
import problem_08.IO.Writer;
import problem_08.duties.MissionImpl;
import problem_08.duties.RepairImpl;
import problem_08.soldiers.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Engine implements Runnable {

    @Override
    public void run() {

        Reader reader = new Reader();
        Writer writer = new Writer();
        List<SoldierImpl> soldiers = new LinkedList<>();

        try {
            String line = reader.readLine();
            while (!line.equals("End")) {
                String[] params = line.split("\\s+");
                tryCreateSoldier(params, soldiers);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        printSoldiers(soldiers, writer);
    }

    private void printSoldiers(List<SoldierImpl> soldiers, Writer writer) {
        for (SoldierImpl soldier : soldiers) {
            writer.write(soldier.toString());
        }
    }

    private void tryCreateSoldier(String[] params, List<SoldierImpl> soldiers) {

        SoldierImpl soldier = null;
        String soldierType = params[0];
        String soldierId = params[1];
        String soldierFirstName = params[2];
        String soldierLastName = params[3];
        try {
            switch (soldierType) {
                case "Private":
                    Double salary = Double.valueOf(params[4]);
                    soldier = new PrivateImpl(soldierFirstName, soldierLastName, soldierId, salary);
                    break;
                case "Spy":
                    String codeNumber = params[4];
                    soldier = new SpyImpl(soldierFirstName, soldierLastName, soldierId, codeNumber);
                    break;
                case "LeutenantGeneral":
                    salary = Double.valueOf(params[4]);
                    soldier = new LeutenantGeneralImpl(soldierFirstName, soldierLastName, soldierId, salary);
                    if (params.length > 5) {
                        addPrivatesToGeneral(soldier, params, soldiers);
                    }
                    break;
                case "Engineer":
                    salary = Double.valueOf(params[4]);
                    String corps = params[5];
                    soldier = new EngineerImpl(soldierFirstName, soldierLastName, soldierId, salary, corps);
                    if (params.length > 6) {
                        addRepairsToEngineer(soldier, params);
                    }
                    break;
                case "Commando":
                    salary = Double.valueOf(params[4]);
                    corps = params[5];
                    soldier = new CommandoImpl(soldierFirstName, soldierLastName, soldierId, salary, corps);
                    if (params.length > 6) {
                        addMissionsToCommando(soldier, params);
                    }
                    break;
            }
        } catch (IllegalArgumentException iaex) {
            return;
        }
        soldiers.add(soldier);
    }

    private void addMissionsToCommando(SoldierImpl soldier, String[] params) {
        MissionImpl mission = null;
        for (int i = 6; i < params.length - 1; i++) {
            String codeName = params[i];
            String state = params[i + 1];
            try {
                mission = new MissionImpl(codeName, state);
                ((CommandoImpl) soldier).addMission(mission);
            } catch (IllegalArgumentException iaex) {
                continue;
            }
        }
    }

    private void addRepairsToEngineer(SoldierImpl soldier, String[] params) {
        RepairImpl repair = null;
        for (int i = 6; i < params.length - 1; i += 2) {
            String partName = params[i];
            Integer hours = Integer.valueOf(params[i + 1]);
            repair = new RepairImpl(partName, hours);
            ((EngineerImpl) soldier).addRepairs(repair);
        }
    }

    private void addPrivatesToGeneral(SoldierImpl soldier, String[] params, List<SoldierImpl> soldiers) {
        for (int i = 5; i < params.length; i++) {
            String id = params[i];
            for (SoldierImpl aPrivate : soldiers) {
                if (aPrivate instanceof PrivateImpl && aPrivate.getId().equals(id)) {
                    ((LeutenantGeneralImpl) soldier).add((PrivateImpl) aPrivate);
                }
            }
        }
    }
}
