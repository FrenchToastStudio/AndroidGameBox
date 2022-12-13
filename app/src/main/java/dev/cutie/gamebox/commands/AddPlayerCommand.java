package dev.cutie.gamebox.commands;

import dev.cutie.androidtoolbox.commands.Command;
import dev.cutie.gamebox.commands.executable.IAddPlayerExecutable;

public class AddPlayerCommand extends Command {

    private IAddPlayerExecutable executable;
    private boolean conditionMet;
    @Override
    public void execute()
    {
        if(this.executable != null) {
            this.executable.addPlayer();
            this.conditionMet = false;
        }
    }

    @Override
    public boolean isConditionMet() {
        return this.conditionMet;
    }

    public void toggle()
    {
        this.conditionMet = true;
    }

    public void setExecutable(IAddPlayerExecutable executable)
    {
        this.executable = executable;
    }
}
