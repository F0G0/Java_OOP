package calculator.commands;

import calculator.Context;
import calculator.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AveTest {
    @Test
    public void execute() {

        Context data = new Context();

        Command command = null;
        try {
            command = Factory.getInstance().createCommand("AVG");
        } catch (IOException e) {
            Assert.fail("Cannot create command");
        }
        data.push(999.);
        data.push(1.);
        data.push(-1.);
        data.push(2.);
        data.push(-2.);
        command.execute(data, "4");
        data.push(0.2);
        command.execute(data, "2");

        Assert.assertEquals(new ArrayList<>(List.of(999., 0.1)), data.getStack());


    }
}
