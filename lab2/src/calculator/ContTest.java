package calculator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ContTest {

    @Test
    public void PushPop() {
        Context data = new Context();
        data.push(3.22);
        data.push(-0.001);
        Assert.assertEquals(new ArrayList<>(List.of(3.22, -0.001)), data.getStack());

        data.pop();
        Assert.assertEquals(new ArrayList<>(List.of(3.22)), data.getStack());

        data.pop();
        Assert.assertEquals(new ArrayList<>(), data.getStack());
    }

    @Test
    public void peek() {
        Context data = new Context();
        data.push(10000.);
        data.push(-128.145);
        Assert.assertEquals(-128.145, data.peek(), 0.000001);
    }

    @Test
    public void AddGet() {
        Context data = new Context();
        Assert.assertNull(data.get("Test"));

        data.add("Test", 23.123);
        Assert.assertNotNull(data.get("Test"));
        Assert.assertEquals(23.123, data.get("Test"), 0.00001);
    }

    @Test
    public void SizeEmpty() {
        Context data = new Context();
        Assert.assertTrue(data.empty());

        data.push(9.9);
        Assert.assertFalse(data.empty());
        Assert.assertEquals(1, data.size());

        data.push(3.);
        data.pop();
        data.pop();
        Assert.assertTrue(data.empty());

    }
}
