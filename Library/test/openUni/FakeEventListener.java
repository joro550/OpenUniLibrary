package openUni;

import java.util.ArrayList;
import openUni.Events.IEvent;
import openUni.Events.IEventListener;

public class FakeEventListener<T extends IEvent> implements IEventListener<T> {
    private final ArrayList<T> eventsCaptured;
    
    public FakeEventListener(){
        eventsCaptured = new ArrayList<>();
    }
    
    @Override
    public void update(T eventType) {
        eventsCaptured.add(eventType);
    }
    
    public ArrayList<T> getEventsCaptured(){
        return eventsCaptured;
    }
}