package bg.softuni.main.models.cores;

import bg.softuni.main.models.collection.LStack;
import bg.softuni.main.contracts.collectionContracts.LinkedStack;
import bg.softuni.main.contracts.modelContracts.Core;
import bg.softuni.main.contracts.modelContracts.Fragment;
import bg.softuni.main.enums.CoreState;
import bg.softuni.main.messages.Messages;

import java.util.NoSuchElementException;

public abstract class BaseCore implements Core {

    private String name;
    private int durability;
    private int maxDurability;
    private LinkedStack<Fragment> fragments;
    private CoreState state;

    protected BaseCore(String name) {
        this.name = name;
        this.fragments = new LStack();
    }

    @Override
    public String getCoreName() {
        return this.name;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public int getNumberOfFragments() {
        return this.fragments.size();
    }

    @Override
    public void attachFragment(Fragment fragment) {
        this.fragments.push(fragment);
        this.calculateDurability();
    }

    @Override
    public Fragment detachFragment() {
        if (this.fragments.size() == 0) {
            throw new NoSuchElementException(Messages.FAIL_TO_DETACH_FRAGMENT);
        }
        Fragment detached = this.fragments.pop();
        this.calculateDurability();
        return detached;
    }

    @Override
    public String reportStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("####Durability: %d%n", this.durability));
        builder.append(String.format("####Status: %s%n", this.state));
        return builder.toString();
    }

    protected void setDurability(int durability) {
        this.durability = durability;
        this.setState();
    }

    protected void setMaxDurability(int durability) {
        this.maxDurability = durability;
    }

    private void calculateDurability() {
        int currentDurability = 0;
        for (Fragment fragment : this.fragments) {
            currentDurability += fragment.applyFragmentPressureAffection();
        }
        if ((this.maxDurability + currentDurability) <= 0) {
            this.setDurability(0);
        } else if ((this.maxDurability + currentDurability) >= this.maxDurability) {
            this.setDurability(this.maxDurability);
        } else {
            this.setDurability((this.maxDurability + currentDurability));
        }
    }

    private void setState() {
        if (this.durability < this.maxDurability) {
            this.state = CoreState.CRITICAL;
        } else {
            this.state = CoreState.NORMAL;
        }
    }
}
