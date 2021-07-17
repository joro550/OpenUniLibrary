package openUni.Events;

public interface IEventListener<T extends IEvent>
{
    /**
     * Update on the back of an event
     * @param eventType Event that got sent
     */
    void update(T eventType);
}
