package openUni.Events;
import java.util.*;

public class EventManager {
    Map<String, List<IEventListener>> _listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this._listeners.put(operation, new ArrayList<>());
        }
    }
    
    public void addEvent(String... operations){
        for (String operation : operations) {
            this._listeners.put(operation, new ArrayList<>());
        }
    }
    
    /**
     * Notify all subscribers of event being sent
     * @param eventName the name of the event being sent
     * @param eventType the event to be sent
     */
    public void notify(String eventName, IEvent eventType) {
        List<IEventListener> listeners = _listeners.get(eventName);
        listeners.forEach((listener) -> listener.update(eventType));
    }
    
    /**
     * Add listener to be notified when event is sent
     * @param <T> the type of event
     * @param eventName the name of the event
     * @param listener the listener to add
     */
    public <T extends IEvent> void subscribe(String eventName, IEventListener<T> listener) {
        
        // if we don't have the key in our listeners map - add it before we subscribe
        if(!_listeners.containsKey(eventName)){
            addEvent(eventName);
        }
        
        List<IEventListener> users = _listeners.get(eventName);
        users.add(listener);
    }
}
