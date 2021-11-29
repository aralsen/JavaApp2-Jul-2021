package org.csystem.util.collection.arraystack;

import org.csystem.collection.ArrayStack;
import org.csystem.util.collection.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RunWith(Parameterized.class)
public class Test_findFirst {
    private final List<String> m_list;
    private ArrayStack<String> m_testStack;
    private final Random m_random = new Random();

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testStack = new ArrayStack<>(m_list.size());

        for (var s : m_list)
            m_testStack.push(s);
    }

    public Test_findFirst(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_findFirst() throws IOException
    {
        var count = m_random.nextInt(20) + 10;

        for (int i = 0; i < count; ++i) {
            var index = m_random.nextInt(m_list.size());
            var s = m_list.get(index);

            Assert.assertEquals(m_testStack.findFirst(str -> str.equals(s)), m_list.stream().filter(str -> str.equals(s)).findFirst());
        }
    }
}
