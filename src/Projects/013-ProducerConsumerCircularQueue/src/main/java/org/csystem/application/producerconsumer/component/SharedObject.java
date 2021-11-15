package org.csystem.application.producerconsumer.component;

import org.csystem.collection.CircularQueue;
import org.csystem.util.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;
import java.util.concurrent.Semaphore;

@Component
public class SharedObject {
    private final Semaphore m_producerSemaphore;
    private final Semaphore m_consumerSemaphore;
    private final CircularQueue<Integer> m_queue; //Dikkat kutulama ve kutu açma maliyeti vardır. Ancak örnek özelinde önemsizdir

    public SharedObject(@Qualifier("producerSemaphore") Semaphore producerSemaphore,
                        @Qualifier("consumerSemaphore") Semaphore consumerSemaphore,
                        CircularQueue<Integer> queue)
    {
        m_producerSemaphore = producerSemaphore;
        m_consumerSemaphore = consumerSemaphore;
        m_queue = queue;
    }

    public void setVal(int val)
    {
        ThreadUtil.acquire(m_producerSemaphore, m_queue.size());
        m_queue.putItem(val);
        ThreadUtil.release(m_consumerSemaphore, m_queue.size());
    }

    public OptionalInt getVal()
    {
        ThreadUtil.acquire(m_consumerSemaphore, m_queue.size());
        var opt = m_queue.getItem();
        ThreadUtil.release(m_producerSemaphore, m_queue.size());

        return opt.map(OptionalInt::of).orElseGet(OptionalInt::empty);
    }
}
