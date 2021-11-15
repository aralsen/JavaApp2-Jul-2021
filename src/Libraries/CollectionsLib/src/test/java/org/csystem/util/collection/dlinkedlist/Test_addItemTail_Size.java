package org.csystem.util.collection.dlinkedlist;

import org.csystem.collection.DLinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_addItemTail_Size {
    private final List<String> m_list;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        var list = new ArrayList<List<String>>();

        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe"); add("fatma");}});

        return list;
    }

    public Test_addItemTail_Size(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_addItemTail_Size()
    {
        var list = new DLinkedList<String>();

        for (var str : m_list)
            list.addItemTail(str);

        Assert.assertEquals(m_list.size(), list.size());
    }
}
