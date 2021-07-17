package openUni.Events;

public class EventPublisher {
    private final EventManager _eventManager;
    private static EventPublisher _instance;
    
    private  EventPublisher() {
        _eventManager = new EventManager();
    }
    
    public static EventPublisher getInstance(){
        if(_instance == null)
            _instance = new EventPublisher();
        return _instance;
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
    public void notify(String eventName, IEvent eventToPublish) {
        _eventManager.notify(eventName, eventToPublish);
    }
    
    /**
     * Notify all of the subscribers of an event in the system
     * @param eventToPublish The event with data
     */
    public void notify(IEvent eventToPublish) {
        _eventManager.notify(eventToPublish.getName(), eventToPublish);
    }
    
}
