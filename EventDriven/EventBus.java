import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<Event.Type, List<Method>> subscribers = new HashMap<>();

    public void subscribe(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0].equals(Event.class)) {
                    Event.Type eventType = Event.Type.valueOf(method.getName().toUpperCase());
                    subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(method);
                }
            }
        }
    }

    public void publish(Event event) {
        List<Method> handlers = subscribers.get(event.getType());
        if (handlers != null) {
            for (Method handler : handlers) {
                try {
                    handler.invoke(handler.getDeclaringClass().newInstance(), event);    
                    } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
