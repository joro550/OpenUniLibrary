package openUni.Events;

public class EventPublisher {
    private final EventManager _eventManager;
    
    protected EventPublisher(EventManager eventManager) {
        _eventManager = eventManager;
    }
    
    /**
     * Add a listener via an event name
     * @param <T>
     * @param eventName the name of the event to subscribe to
     * @param listener
     */
    public <T extends IEvent> void subscribe(String eventName, IEventListener<T> listener) {
        _eventManager.subscribe(eventName, listener);
    }
    
    /**
     * Notify all of the subscribers of an event in the system
     * @param eventName The name of the event
     * @param eventToPublish The event with data
     */
    protected void notify(String eventName, IEvent eventToPublish) {
        _eventManager.notify(eventName, eventToPublish);
    }
}
