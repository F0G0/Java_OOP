package calculator.commands;

import calculator.Context;
import calculator.Factory;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AbsTest {
    @org.junit.Test
    public void execute() {

        Context data = new Context();

        Command command = null;
        try {
            command = Factory.getInstance().createCommand("ABS");
        } catch (IOException e) {
            Assert.fail("Cannot create command");
        }
        data.push(-1.);
        command.execute(data, "");
        data.push(2.);
        command.execute(data, "");
        Assert.assertEquals(new ArrayList<>(List.of(1., 2.)), data.getStack());

    }
}
