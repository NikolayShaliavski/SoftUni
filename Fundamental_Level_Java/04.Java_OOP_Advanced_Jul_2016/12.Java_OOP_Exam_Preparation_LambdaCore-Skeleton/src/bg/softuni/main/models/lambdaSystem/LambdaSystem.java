package bg.softuni.main.models.lambdaSystem;

import bg.softuni.main.contracts.modelContracts.Core;
import bg.softuni.main.contracts.modelContracts.Fragment;
import bg.softuni.main.contracts.modelContracts.LambdaCore;
import bg.softuni.main.messages.Messages;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class LambdaSystem implements LambdaCore {

    private Map<String, Core> cores;
    private Core currentCore;

    public LambdaSystem() {
        this.cores = new LinkedHashMap<>();
    }

    @Override
    public Core getCurrentCore() {
        return this.currentCore;
    }

    @Override
    public void addCore(Core core) {
        this.cores.put(core.getCoreName(), core);
    }

    @Override
    public void removeCore(String coreName) {
        if (!this.cores.containsKey(coreName)) {
            throw new NoSuchElementException(
                    String.format(Messages.FAIL_TO_REMOVE_CORE, coreName));
        }
        if (this.currentCore.getCoreName().equals(coreName)) {
            this.currentCore = null;
        }
        this.cores.remove(coreName);
    }

    @Override
    public void selectCore(String coreName) {
        if (!this.cores.containsKey(coreName)) {
            throw new NoSuchElementException(
                    String.format(Messages.FAIL_TO_SELECT_CORE, coreName));
        }
        this.currentCore = this.cores.get(coreName);
    }

    @Override
    public void attachFragmentToCore(Fragment fragment) {
        if (fragment == null) {
            throw new UnsupportedOperationException(
                    String.format(Messages.FAIL_TO_ATTACH_FRAGMENT, null));
        }
        if (this.currentCore == null) {
            throw new UnsupportedOperationException(
                    String.format(Messages.FAIL_TO_ATTACH_FRAGMENT, fragment.getName()));
        }
        this.currentCore.attachFragment(fragment);
    }

    @Override
    public Fragment detachFragment() {
        if (this.currentCore == null) {
            throw new UnsupportedOperationException(
                    Messages.FAIL_TO_DETACH_FRAGMENT);
        }
        return this.currentCore.detachFragment();
    }

    @Override
    public String reportStatus() {
        int totalDurability = this.cores.values().stream().
                mapToInt(core -> core.getDurability()).sum();
        int totalFragments = this.cores.values().stream().
                mapToInt(core -> core.getNumberOfFragments()).sum();

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Lambda Core Power Plant Status%n")).
                append(String.format("Total Durability: %d%n", totalDurability)).
                append(String.format("Total Cores: %d%n", this.cores.size())).
                append(String.format("Total Fragments: %d%n", totalFragments));
        for (Core core : cores.values()) {
            builder.append(String.format("Core %s:%n", core.getCoreName())).
                    append(core.reportStatus());
        }
        return builder.toString().trim();
    }
}
