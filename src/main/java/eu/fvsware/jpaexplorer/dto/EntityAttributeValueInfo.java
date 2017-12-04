package eu.fvsware.jpaexplorer.dto;

public class EntityAttributeValueInfo {
    private boolean lazyLoaded;
    private Object value;
    private Object id;

    public EntityAttributeValueInfo(boolean lazyLoaded, Object value, Object id) {
        this.lazyLoaded = lazyLoaded;
        this.value = value;
        this.id = id;
    }

    public EntityAttributeValueInfo(boolean lazyLoaded) {
        this.lazyLoaded = lazyLoaded;
    }

    public EntityAttributeValueInfo(boolean lazyLoaded, Object value) {
        this.lazyLoaded = lazyLoaded;
        this.value = value;
    }

    public boolean isLazyLoaded() {
        return lazyLoaded;
    }

    public Object getValue() {
        return value;
    }
}
